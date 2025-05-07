package markets2.graphics;

import java.util.Set;
import java.util.List;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import markets2.World;
import markets2.Market;
import markets2.Market.Order;
import markets2.Market.MarketHistoryDataPoint;
import markets2.person.PersonInventory;
import markets2.person.PersonAction;
import markets2.person.Person;
import markets2.Granary;

//
public final class MainPanel extends JPanel {
    private static final @NotNull Color
            BACKGROUND_COLOR = new Color(0, 0, 0, 0),
            TEXT_COLOR = new Color(255, 255, 255),
            TEXT_COLOR_GOOD = new Color(120, 255, 100),
            TEXT_COLOR_NORMAL = new Color(240, 240, 0),
            TEXT_COLOR_BAD = new Color(255, 60, 40);
    private static final int TEXT_HEIGHT = 15;
    private final @NotNull World world;

    //
    MainPanel(@NotNull World world) {
        super();
        setBackground(BACKGROUND_COLOR);
        this.world = world;
    }

    //
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int
                leftOffset = 30,
                topOffset = 30,
                worldInfoLines = 4,
                granaryInfoLines = 5,
                leftSideSize = 530;
        paintWorldInfo(g, leftOffset, topOffset);
        paintGranaryInfo(g, leftOffset, topOffset + worldInfoLines * TEXT_HEIGHT);
        paintMarketInfo(g, leftOffset, topOffset + (worldInfoLines + granaryInfoLines) * TEXT_HEIGHT);
        paintPeopleInfo(g, leftOffset + leftSideSize, topOffset);
    }

    @SuppressWarnings("SameParameterValue")
    private void paintWorldInfo(@NotNull Graphics g, int drawX, int drawY) {
        int columnSeparation = 150;
        g.setColor(TEXT_COLOR);
        int
                yearLength = 40,
                elapsedTime = world.getElapsedTime(),
                years = Math.floorDiv(elapsedTime, yearLength),
                days = elapsedTime - years * yearLength;
        g.drawString("Elapsed time: " + world.getElapsedTime(), drawX, drawY);
        g.drawString("Y: " + years + ", D: " + days, drawX + columnSeparation, drawY);

        double weatherFertility = world.getWeatherFertility();
        g.setColor(determineColor(weatherFertility, 0.2, 0.8));
        g.drawString("Weather fertility: " + doubleToString(weatherFertility, 2), drawX, drawY + TEXT_HEIGHT);
        g.setColor(TEXT_COLOR);
        g.drawString("Land per person: " + doubleToString(world.getLandPerPerson(), 1), drawX + columnSeparation, drawY + TEXT_HEIGHT);
    }

    @SuppressWarnings("SameParameterValue")
    private void paintGranaryInfo(@NotNull Graphics g, int drawX, int drawY) {
        int
                indent = 20,
                columnSeparation = 120;
        double
                colorCriteria_lowDA = Granary.TARGET_DA_RATIO,
                colorCriteria_highDA = Granary.CRITICAL_DA_RATIO,
                colorCriteria_lowLiquidity = Granary.TARGET_LIQUIDITY_LOW,
                colorCriteria_highLiquidity = Granary.TARGET_LIQUIDITY_HIGH;

        @NotNull Granary granary = world.getGranary();
        g.setColor(TEXT_COLOR);
        g.drawString("Granary info:", drawX, drawY);

        @NotNull PersonInventory inventory = granary.getInventory();
        double
                food = inventory.food,
                foodPrice = granary.getReferencePrice(), //instasell value?
                foodValue = food * foodPrice;
        g.drawString("Food: " + doubleToString(food, 1),
                drawX + indent, drawY + TEXT_HEIGHT);
        g.drawString("Value: " + doubleToString(foodValue, 3),
                drawX + indent + columnSeparation, drawY + TEXT_HEIGHT);
        g.drawString("At price: " + doubleToString(foodPrice, 3),
                drawX + indent + columnSeparation * 2, drawY + TEXT_HEIGHT);

        double
                money = inventory.money,
                totalAssets = money + foodValue,
                liquidity = money / totalAssets;
        g.drawString("Money: " + doubleToString(money, 3),
                drawX + indent, drawY + TEXT_HEIGHT * 2);
        g.setColor(determineColor(liquidity, colorCriteria_highLiquidity, colorCriteria_lowLiquidity));
        g.drawString("Liquidity: " + doubleToString(liquidity * 100, 1) + " %",
                drawX + indent + columnSeparation, drawY + TEXT_HEIGHT * 2);

        double
                debt = granary.debt,
                debtToAssetRatio = debt / totalAssets;
        g.setColor(TEXT_COLOR);
        g.drawString("Assets: " + doubleToString(totalAssets, 3),
                drawX + indent, drawY + TEXT_HEIGHT * 3);
        g.drawString("Debt: " + doubleToString(debt, 3),
                drawX + indent + columnSeparation, drawY + TEXT_HEIGHT * 3);
        g.setColor(determineColor(debtToAssetRatio, colorCriteria_highDA, colorCriteria_lowDA));
        g.drawString("D/A: " + doubleToString(debtToAssetRatio * 100, 1) + " %",
                drawX + indent + columnSeparation * 2, drawY + TEXT_HEIGHT * 3);
    }

    @SuppressWarnings("SameParameterValue")
    private void paintMarketInfo(@NotNull Graphics g, int drawX, int drawY) {
        int
                indent = 20,
                indentP = 80,
                indentV = 70,
                columnSeparation = indent + indentP + indentV + 80;

        @NotNull Market market = world.getMarket();
        g.setColor(TEXT_COLOR);
        g.drawString("Market info:", drawX, drawY);

        //TODO: print last day's info here (could throw null pointer on first day)
        @NotNull List<@NotNull MarketHistoryDataPoint> history = market.getHistory();
        g.drawString("Last price: " + doubleToString(history.getLast().endPrice(), 3),
                drawX, drawY + TEXT_HEIGHT);
        g.drawString("Last volume: " + doubleToString(history.getLast().volume(), 1),
                drawX, drawY + TEXT_HEIGHT * 2);
        int startingLineCount = 4;

        //sell orders
        @NotNull List<@NotNull Order> sellOrders = market.getSortedSellOrders();
        g.drawString("Sell:", drawX + indent, drawY + TEXT_HEIGHT * startingLineCount);
        int lineCount = startingLineCount + 1;
        for (@NotNull Order order : sellOrders) {
            g.drawString(order.getTrader().getName(),
                    drawX + indent * 2, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("P: " + doubleToString(order.getPrice(), 3),
                    drawX + indent * 2 + indentP, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("V: " + doubleToString(order.getAmount(), 1),
                    drawX + indent * 2 + indentP + indentV, drawY + TEXT_HEIGHT * lineCount);
            lineCount ++;
        }

        //buy orders
        @NotNull List<@NotNull Order> buyOrders = market.getSortedBuyOrders();
        g.drawString("Buy:", drawX + indent + columnSeparation, drawY + TEXT_HEIGHT * startingLineCount);
        lineCount = startingLineCount + 1;
        for (@NotNull Order order : buyOrders) {
            g.drawString(order.getTrader().getName(),
                    drawX + columnSeparation + indent * 2, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("P: " + doubleToString(order.getPrice(), 3),
                    drawX + columnSeparation + indent * 2 + indentP, drawY + TEXT_HEIGHT * lineCount);
            g.drawString("V: " + doubleToString(order.getAmount(), 1),
                    drawX + columnSeparation + indent * 2 + indentP + indentV, drawY + TEXT_HEIGHT * lineCount);
            lineCount ++;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void paintPeopleInfo(@NotNull Graphics g, int drawX, int drawY) {
        int indent = 20;
        @NotNull Set<@NotNull Person> people = world.getPeople();
        g.setColor(TEXT_COLOR);
        g.drawString("People info: " + people.size() + " people", drawX, drawY);
        int lineIndex = 2;
        for (@NotNull Person person : people) {
            int printedLines = paintPersonInfo(g, drawX + indent, drawY + TEXT_HEIGHT * lineIndex, indent, person);
            lineIndex += printedLines + 1;
        }
    }

    //returns number of lines printed
    private int paintPersonInfo(@NotNull Graphics g, int drawX, int drawY, int commonIndent, @NotNull Person person) {
        int
                columnSeparation = 170,
                personalInfoLines = paintPersonPersonalInfo(g, drawX, drawY, commonIndent, person),
                inventoryLines = paintPersonInventory(g, drawX + columnSeparation, drawY, commonIndent, person),
                qualificationsLines = paintPersonQualifications(g, drawX + columnSeparation * 2, drawY, commonIndent, person);
        return Math.max(personalInfoLines, Math.max(inventoryLines, qualificationsLines));
    }

    @SuppressWarnings("ConstantValue")
    private int paintPersonPersonalInfo(@NotNull Graphics g, int drawX, int drawY, int commonIndent, @NotNull Person person) {
        int lineCount = 0; //for return result
        double
                colorCriteria_lowHealth = 0.3,
                colorCriteria_highHealth = 0.9,
                colorCriteria_lowFoodReserves = 5,
                colorCriteria_highFoodReserves = 40;

        //name
        g.setColor(TEXT_COLOR);
        g.drawString(person.getName(), drawX, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //age
        int
                yearLength = 40,
                age = person.getAge(),
                ageYears = Math.floorDiv(age, yearLength),
                ageDays = age - ageYears * yearLength;
        @NotNull String ageString = "Age: " + ageYears + " Y, " + ageDays + "D";
        g.drawString(ageString, drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //health
        double
                health = person.getHealth(),
                maxHealth = Person.getMaximumHealth(),
                healthFraction = health / maxHealth;
        @NotNull String healthString = "HP: " + doubleToString(health, 1) + "/" + doubleToString(maxHealth, 0);
        g.setColor(determineColor(healthFraction, colorCriteria_lowHealth, colorCriteria_highHealth));
        g.drawString(healthString, drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //food reserves
        double foodReserves = person.maximumFoodReserveDuration;
        @NotNull String foodReservesString = "Food reserves: " + doubleToString(foodReserves, 1);
        g.setColor(determineColor(foodReserves, colorCriteria_lowFoodReserves, colorCriteria_highFoodReserves));
        g.drawString(foodReservesString, drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //action
        @Nullable PersonAction action = person.getAction();
        @NotNull String actionString = "Action: ";
        if (action == null) {
            actionString += "Rest";
        } else {
            actionString += action.getName();
        }
        g.setColor(TEXT_COLOR);
        g.drawString(actionString, drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        return lineCount;
    }

    @SuppressWarnings("ConstantValue")
    private int paintPersonInventory(@NotNull Graphics g, int drawX, int drawY, int commonIndent, @NotNull Person person) {
        int lineCount = 0; //for return result
        double
                colorCriteria_fullInventory = 0.8,
                colorCriteria_emptyInventory = 0.3;

        @NotNull PersonInventory inventory = person.getInventory();
        double
                inventoryFilledCapacity = inventory.getFilledCapacity(),
                inventoryMaxCapacity = inventory.getMaxCapacity();
        @NotNull String inventoryString = "Inventory: " + doubleToString(inventoryFilledCapacity, 1) + "/" + doubleToString(inventoryMaxCapacity, 0);
        g.setColor(determineColor(inventoryFilledCapacity / inventoryMaxCapacity, colorCriteria_fullInventory, colorCriteria_emptyInventory));
        g.drawString(inventoryString, drawX, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        g.setColor(TEXT_COLOR);
        g.drawString("Money: " + doubleToString(inventory.money, 3),
                drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;
        g.drawString("Food: " + doubleToString(inventory.food, 1),
                drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        return lineCount;
    }

    @SuppressWarnings("ConstantValue")
    private int paintPersonQualifications(@NotNull Graphics g, int drawX, int drawY, int commonIndent, @NotNull Person person) {
        int
                lineCount = 0, //for return result
                columnSeparation = 120;
        double
                colorCriteria_lowYield = 1,
                colorCriteria_highYield = 2;

        g.setColor(TEXT_COLOR);
        g.drawString("Qualifications: ", drawX, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //farming
        double
                farmingSkill = person.getBaseFarmingRate(),
                expectedYield = farmingSkill * world.getMaximumFarmingYield();
        @NotNull String
                farmingSkillString = "Farming skill: " + doubleToString(farmingSkill, 1),
                expectedYieldString = "Exp. yield: " + doubleToString(expectedYield, 1);
        g.drawString(farmingSkillString, drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        g.setColor(determineColor(expectedYield, colorCriteria_lowYield, colorCriteria_highYield));
        g.drawString(expectedYieldString, drawX + commonIndent + columnSeparation, drawY + lineCount * TEXT_HEIGHT);
        //TODO: print expected income in terms of money
        lineCount ++;

        //TODO: print other qualifications here

        return lineCount;
    }

    private @NotNull Color determineColor(double value, double badRange, double goodRange) {
        boolean
                isGood = false,
                isBad = false;
        if (badRange < goodRange) { //ascending to good
            if (value >= goodRange) isGood = true;
            if (value <= badRange) isBad = true;
        }
        if (goodRange < badRange) { //descending to good
            if (value <= goodRange) isGood = true;
            if (value >= badRange) isBad = true;
        }

        if (isGood) return TEXT_COLOR_GOOD;
        if (isBad) return TEXT_COLOR_BAD;
        return TEXT_COLOR_NORMAL;
    }
}
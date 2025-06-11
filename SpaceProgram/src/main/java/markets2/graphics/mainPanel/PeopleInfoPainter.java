package markets2.graphics.mainPanel;

import java.util.Set;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import markets2.resources.ParticularResources;
import markets2.resources.containers.Inventory;
import markets2.market.MarketHistoryDataPoint;
import markets2.person.PersonHealth;
import markets2.person.actions.PersonAction;
import markets2.person.Person;
import markets2.World;

//
class PeopleInfoPainter implements TextPainterInterface {
    private final @NotNull World world;

    //
    PeopleInfoPainter(@NotNull World world) {
        this.world = world;
    }

    @SuppressWarnings("SameParameterValue")
    void paint(@NotNull Graphics g, int drawX, int drawY) {
        int indent = 20;
        @NotNull Set<@NotNull Person> people = world.getPeople();
        drawColoredString(g, null,
                "People info: " + people.size() + " people",
                drawX, drawY);
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
                qualificationsLines = paintPersonSkills(g, drawX + columnSeparation * 2, drawY, commonIndent, person);
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
        drawColoredString(g, null, person.getName(), drawX, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        @NotNull PersonHealth health = person.getHealth();

        //age
        int
                yearLength = 40,
                age = health.getAge(),
                ageYears = Math.floorDiv(age, yearLength),
                ageDays = age - ageYears * yearLength;
        drawColoredString(g, null,
                "Age: " + ageYears + " Y, " + ageDays + "D",
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //health
        double
                healthAmount = health.getHealth(),
                maxHealth = health.getMaxHealth(),
                healthFraction = health.getHealthFraction();
        @NotNull String healthString = "HP: " + doubleToString(healthFraction * 100, 0) + " % [" +
                doubleToString(healthAmount, 1) + " / " + doubleToString(maxHealth, 0) + "]";
        drawColoredString(g, determineColor(healthFraction, colorCriteria_lowHealth, colorCriteria_highHealth),
                healthString,
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //food reserves
        double referencePrice_food = 0;
        @Nullable MarketHistoryDataPoint<@NotNull Double>
                lastPositiveVolumeData = world.getMarket().getMarket(ParticularResources.FOOD).getHistory().getLastPositiveVolumeData();
        if (lastPositiveVolumeData != null) {
            referencePrice_food = lastPositiveVolumeData.getPriceVWA();
        } else {
            @Nullable MarketHistoryDataPoint<@NotNull Double>
                    lastData = world.getMarket().getMarket(ParticularResources.FOOD).getHistory().getLast();
            if (lastData != null) {
                referencePrice_food = lastData.getPriceLowestUnfulfilledSell();
            }
        }
        double foodReserves = person.getNutrition().getFoodReserveDuration(
                person.getInventory().getContinuousResourceAmount(ParticularResources.FOOD),
                referencePrice_food,
                person.getWallet().getMoney());
        drawColoredString(g, determineColor(foodReserves, colorCriteria_lowFoodReserves, colorCriteria_highFoodReserves),
                "Food reserves: " + doubleToString(foodReserves, 1),
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //action
        @Nullable PersonAction action = person.getAction();
        @NotNull String actionString = "Action: ";
        if (action == null) {
            actionString += "Idle";
        } else {
            actionString += action.getName();
        }
        drawColoredString(g, null, actionString, drawX + commonIndent, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        return lineCount;
    }

    @SuppressWarnings("ConstantValue")
    private int paintPersonInventory(@NotNull Graphics g, int drawX, int drawY, int commonIndent, @NotNull Person person) {
        int
                lineCount = 0, //for return result
                columnSeparation = 80;
        double
                colorCriteria_fullInventory = 0.8,
                colorCriteria_emptyInventory = 0.3;

        //wallet
        drawColoredString(g, null,
                "Money: " + doubleToString(person.getWallet().getMoney(), 3),
                drawX,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //inventory in general
        @NotNull Inventory inventory = person.getInventory();
        double
                inventoryFilledCapacity = inventory.getFilledCapacity(),
                inventoryMaxCapacity = inventory.getMaximumCapacity(),
                inventoryFullness = inventory.getCapacityFullness();
        @NotNull String inventoryString = "Inventory: " +
                doubleToString(inventoryFullness * 100, 0) + " % [" +
                doubleToString(inventoryFilledCapacity, 1) + " / " +
                doubleToString(inventoryMaxCapacity, 0) + "]";
        drawColoredString(g, determineColor(inventoryFullness, colorCriteria_fullInventory, colorCriteria_emptyInventory),
                inventoryString,
                drawX,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //particular inventory items; TODO: make a cycle
        drawColoredString(g, null,
                "Food: " + doubleToString(inventory.getContinuousResourceAmount(ParticularResources.FOOD), 1),
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        drawColoredString(g, null,
                "Value: ",// + doubleToString(inventory.food, 1),
                drawX + commonIndent + columnSeparation,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        drawColoredString(g, null,
                "Sticks: " + doubleToString(inventory.getContinuousResourceAmount(ParticularResources.STICKS), 1),
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        drawColoredString(g, null,
                "Value: ",// + doubleToString(inventory.food, 1),
                drawX + commonIndent + columnSeparation,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        drawColoredString(g, null,
                "Basket: " + inventory.getDiscreteResourceCount(ParticularResources.BASKET),
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        drawColoredString(g, null,
                "Value: ",// + doubleToString(inventory.food, 1),
                drawX + commonIndent + columnSeparation,
                drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        return lineCount;
    }

    @SuppressWarnings("ConstantValue")
    private int paintPersonSkills(@NotNull Graphics g, int drawX, int drawY, int commonIndent, @NotNull Person person) {
        int
                lineCount = 0, //for return result
                columnSeparation = 120;
        double
                colorCriteria_lowYield = 1,
                colorCriteria_highYield = 2;

        drawColoredString(g, null, "Skills: ", drawX, drawY + lineCount * TEXT_HEIGHT);
        lineCount ++;

        //gathering food
        double
                skill_gatherFood = person.getSkills().getSkill_gatherFood(),
                expectedYield_gatherFood = person.maximumYield_gatherFood;
        drawColoredString(g, null,
                "Gather food: " + doubleToString(skill_gatherFood, 1),
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        drawColoredString(g, determineColor(expectedYield_gatherFood, colorCriteria_lowYield, colorCriteria_highYield),
                "Exp. yield: " + doubleToString(expectedYield_gatherFood, 1),
                drawX + commonIndent + columnSeparation,
                drawY + lineCount * TEXT_HEIGHT);
        //TODO: print expected income in terms of money
        lineCount ++;

        //gathering sticks
        double
                skill_gatherSticks = person.getSkills().getSkill_gatherSticks(),
                expectedYield_gatherSticks = person.maximumYield_gatherSticks;
        drawColoredString(g, null,
                "Gather sticks: " + doubleToString(skill_gatherSticks, 1),
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        drawColoredString(g, determineColor(expectedYield_gatherSticks, 1, 5),
                "Exp. yield: " + doubleToString(expectedYield_gatherSticks, 1),
                drawX + commonIndent + columnSeparation,
                drawY + lineCount * TEXT_HEIGHT);
        //TODO: print expected income in terms of money
        lineCount ++;

        //crafting baskets
        double
                skill_craftBasket = person.getSkills().getSkill_craftBasket(),
                minimumConsumption_sticks_craftBasket = person.minimumConsumption_sticks_craftBasket;
        drawColoredString(g, null,
                "Crafting baskets: " + doubleToString(skill_craftBasket, 1),
                drawX + commonIndent,
                drawY + lineCount * TEXT_HEIGHT);
        drawColoredString(g, determineColor(minimumConsumption_sticks_craftBasket, 5, 1),
                "Sticks use: " + doubleToString(minimumConsumption_sticks_craftBasket, 1),
                drawX + commonIndent + columnSeparation,
                drawY + lineCount * TEXT_HEIGHT);
        //TODO: print expected income in terms of money
        lineCount ++;

        //TODO: print other skills here; generally, make a cycle to print all skills

        return lineCount;
    }
}
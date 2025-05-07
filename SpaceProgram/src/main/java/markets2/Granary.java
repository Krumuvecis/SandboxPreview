package markets2;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import markets2.Market.Order;
import markets2.Market.MarketHistoryDataPoint;
import markets2.person.PersonInventory;

//
public class Granary implements TraderInterface {
    public static final double
            TARGET_DA_RATIO = 0.5,
            CRITICAL_DA_RATIO = 0.8,
            TARGET_LIQUIDITY_LOW = 0.2,
            TARGET_LIQUIDITY_HIGH = 0.8,
            PRICE_MARGIN_NORMAL = 0.02,
            PRICE_MARGIN_PREFERENTIAL = 0.05,
            INITIAL_PRICE = 1;
    private final @NotNull Market market;
    private final @NotNull PersonInventory inventory;
    public double debt; //standing debt to bank
    private double referencePrice;

    //
    public Granary(@NotNull Market market) {
        this.market = market;
        inventory = new PersonInventory();
        debt = 0;
        referencePrice = INITIAL_PRICE;

        inventory.food += 10; //starting capital
    }

    //
    @Override
    public final @NotNull String getName() {
        return "Granary";
    }

    //
    @Override
    public final @NotNull PersonInventory getInventory() {
        return inventory;
    }

    //
    public final void update() {
        referencePrice = calculateReferencePrice();

        double foodAmount = inventory.food;
        double foodValue = foodAmount * getReferencePrice();
        double money = inventory.money;
        double totalAssets = money + foodValue;
        double liquidity = money / totalAssets;
        double debtToAssetRatio = getDebtToAssetRatio();

        double
                buyPrice = getReferencePrice(),
                sellPrice = getReferencePrice(),
                buyVolume,
                sellVolume;

        if (debtToAssetRatio < TARGET_DA_RATIO) {
            issueNewMoney(money, totalAssets);
        } else if (debtToAssetRatio > CRITICAL_DA_RATIO && liquidity > TARGET_LIQUIDITY_LOW) {
            repayDebt(money, totalAssets);
        }
        money = inventory.money; //refresh after debt creation/repayment

        if (liquidity < TARGET_LIQUIDITY_LOW) { //has low money at hand, preferential sell regime, lowers prices
            //buyPrice *= (1 - PRICE_MARGIN_PREFERENTIAL);// - PRICE_MARGIN_NORMAL);
            //sellPrice *= (1 - PRICE_MARGIN_PREFERENTIAL);

            sellPrice *= (1 + PRICE_MARGIN_PREFERENTIAL);
            buyPrice *= (1 + PRICE_MARGIN_NORMAL);
        } else if (liquidity > TARGET_LIQUIDITY_HIGH) { //has plenty of money at hand, preferential buy regime, increases prices
            //buyPrice *= (1 + PRICE_MARGIN_PREFERENTIAL);
            //sellPrice *= (1 + PRICE_MARGIN_PREFERENTIAL);// + PRICE_MARGIN_NORMAL);

            buyPrice *= (1 - PRICE_MARGIN_PREFERENTIAL);
            //sellPrice *= (1 - PRICE_MARGIN_NORMAL);
        } else { //medium liquidity, follow market at margin/2
            buyPrice *= (1 - PRICE_MARGIN_NORMAL);
            sellPrice *= (1 + PRICE_MARGIN_NORMAL / 2);

            //buyPrice *= (1 - PRICE_MARGIN_NORMAL);
        }

        buyVolume  = money / buyPrice; //buy all-in
        sellVolume = foodAmount; //put all to sell
        market.placeBuyOrder(new Market.Order(this, buyPrice, buyVolume));
        market.placeSellOrder(new Market.Order(this, sellPrice, sellVolume));
    }

    private double calculateReferencePrice() {
        @NotNull List<@NotNull MarketHistoryDataPoint> reversedHistory = market.getHistory().reversed();
        for (@NotNull MarketHistoryDataPoint dataPoint : reversedHistory) { //looking for last historical price
            if (dataPoint.volume() > 0) {
                return dataPoint.VWAPrice();
            }
        }
        return INITIAL_PRICE; //no prior trades, setting first price
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    private void issueNewMoney(double money, double totalAssets) {
        double maxNewDebt_DA = (TARGET_DA_RATIO * totalAssets - debt) / (1 - TARGET_DA_RATIO);

        //TODO: see if liquidity's limit is applicable
        //double maxNewDebt_liquidity = (TARGET_LIQUIDITY * totalAssets - money) / (1 - TARGET_LIQUIDITY);
        //double maxNewDebt = Math.min(maxNewDebt_DA, maxNewDebt_liquidity);

        double maxNewDebt = maxNewDebt_DA;
        if (maxNewDebt > 0) {
            inventory.money += maxNewDebt;
            debt += maxNewDebt;
        }
    }

    private void repayDebt(double money, double totalAssets) {
        double repayment = (debt - totalAssets * CRITICAL_DA_RATIO) / (1 - CRITICAL_DA_RATIO);
        double possibleRepayment = Math.min(money, repayment);
        if (possibleRepayment > 0) {
            inventory.money -= possibleRepayment;
            debt -= possibleRepayment;
        }
    }

    //for asset value calculation
    public double getReferencePrice() {
        return referencePrice;
    }

    //
    public double getDebtToAssetRatio() {
        double referencePrice = getReferencePrice();
        double foodValue = inventory.food * referencePrice;
        double money = inventory.money;
        double totalAssets = money + foodValue;
        return debt / totalAssets;
    }
}
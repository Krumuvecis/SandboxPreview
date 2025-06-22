package markets2.granary;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import markets2.resources.ResourceInterface;
import markets2.resources.UnrecognizedResourceType;
import markets2.resources.ParticularResources;
import markets2.resources.ResourceAmount;
import markets2.Wallet;
import markets2.TraderInterface;
import markets2.market.MarketHistoryDataPoint;
import markets2.market.MarketOrder;
import markets2.market.MarketOrder.MarketOrderContinuous;
import markets2.market.SingleResourceMarket.SingleResourceMarketContinuous;

//
public class Granary implements TraderInterface {
    //TODO: rework and privatize constants below
    public static final double
            TARGET_DA_RATIO = 0.9,
            CRITICAL_DA_RATIO = 0.95,
            TARGET_LIQUIDITY_LOW = 0.75,//0.1,
            TARGET_LIQUIDITY_HIGH = 0.75,
            PRICE_MARGIN_NORMAL = 0.02,
            PRICE_MARGIN_PREFERENTIAL = 0.2,
            INITIAL_PRICE = 1,
            INITIAL_FOOD_AMOUNT = 25;
    private final @NotNull SingleResourceMarketContinuous foodMarket;
    private final @NotNull Wallet wallet;
    private final @NotNull GranaryInventory inventory;
    private double debt; //standing debt to bank
    private double
            referencePrice,
            totalAssets;
    private final @NotNull GranaryPriceCalculator granaryPriceCalculator;
    private @Nullable MarketOrderContinuous
            sellOrder = null,
            buyOrder = null;
    private final @NotNull GranaryGraphicalAdapter graphicalAdapter; //for output purposes

    //
    public Granary(@NotNull SingleResourceMarketContinuous foodMarket) {
        this.foodMarket = foodMarket;
        wallet = new Wallet();
        inventory = new GranaryInventory();
        debt = 0;
        referencePrice = INITIAL_PRICE;
        totalAssets = 0;
        granaryPriceCalculator = new GranaryPriceCalculator(this);
        graphicalAdapter = new GranaryGraphicalAdapter(this);

        try {
            inventory.add(new ResourceAmount.ContinuousResourceAmount(ParticularResources.FOOD, INITIAL_FOOD_AMOUNT)); //starting capital
        } catch (@NotNull UnrecognizedResourceType e) {
            throw new RuntimeException(e);
        }
    }

    //
    @Override
    public final @NotNull String getName() {
        return "Granary";
    }

    //
    @Override
    public final @NotNull Wallet getWallet() {
        return wallet;
    }

    //
    @Override
    public final @NotNull GranaryInventory getInventory() {
        return inventory;
    }

    //
    @Override
    public final void removeCompletedSellOrder(@NotNull ResourceInterface resource,
                                               @NotNull MarketOrder<? extends @NotNull Number> order) {
        if (resource == ParticularResources.FOOD && sellOrder != null) {
            sellOrder = null;
        }
    }

    //
    @Override
    public final void removeCompletedBuyOrder(@NotNull ResourceInterface resource,
                                              @NotNull MarketOrder<? extends @NotNull Number> order) {
        if (resource == ParticularResources.FOOD && buyOrder != null) {
            buyOrder = null;
        }
    }

    //
    public final void update() {
        clearSellOrders();
        clearBuyOrders();

        referencePrice = calculateReferencePrice();
        totalAssets = calculateTotalAssets();
        manageDebt();
        totalAssets = calculateTotalAssets(); //recalculate after debt creation/repayment
        granaryPriceCalculator.update();
        placeNewOrders();
    }

    private void clearSellOrders() {
        if (sellOrder != null) {
            foodMarket.retractSellOrder(sellOrder);
            sellOrder = null;
        }
    }

    private void clearBuyOrders() {
        if (buyOrder != null) {
            foodMarket.retractBuyOrder(buyOrder);
            buyOrder = null;
        }
    }

    private double calculateReferencePrice() {
        @Nullable MarketHistoryDataPoint<@NotNull Double>
                lastPositiveVolumeData = foodMarket.getHistory().getLastPositiveVolumeData();
        if (lastPositiveVolumeData == null) {
            return INITIAL_PRICE; //no prior trades, setting first price
        } else {
            return lastPositiveVolumeData.getPriceVWA();
        }
    }

    private double calculateTotalAssets() {
        double
                money = wallet.getMoney(),
                totalAssets = money;

        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                foodAmount = inventory.get(ParticularResources.FOOD);
        if (foodAmount != null) {
            totalAssets += foodAmount.getMass() * referencePrice;
        }

        return totalAssets;
    }

    private void manageDebt() {
        double
                liquidity = getLiquidity(),
                debtToAssetRatio = getDebtToAssetRatio();
        if (debtToAssetRatio < TARGET_DA_RATIO && liquidity < TARGET_LIQUIDITY_HIGH) {
            issueNewMoney(totalAssets);
        } else if (debtToAssetRatio > TARGET_DA_RATIO && liquidity > TARGET_LIQUIDITY_LOW) {
            repayDebt(totalAssets);
        }
    }

    private void issueNewMoney(double totalAssets) {
        double
                money = wallet.getMoney(),
                maxNewDebt_DA = Math.max(0, (totalAssets * TARGET_DA_RATIO - debt) / (1 - TARGET_DA_RATIO)),
                maxNewDebt_liquidity = Math.max(0, (totalAssets * TARGET_LIQUIDITY_HIGH - money) / (1 - TARGET_LIQUIDITY_HIGH)),
                maxNewDebt = Math.min(maxNewDebt_DA, maxNewDebt_liquidity);
        if (maxNewDebt > 0) {
            wallet.addMoney(maxNewDebt);
            debt += maxNewDebt;
        }
    }

    private void repayDebt(double totalAssets) {
        double
                money = wallet.getMoney(),
                maxRepayment_DA = Math.max(0, (debt - totalAssets * TARGET_DA_RATIO) / (1 - TARGET_DA_RATIO)),
                maxRepayment_liquidity = Math.max(0, (money - totalAssets * TARGET_LIQUIDITY_LOW) / (1 - TARGET_LIQUIDITY_LOW)),
                maxRepayment = Math.min(maxRepayment_DA, maxRepayment_liquidity),
                possibleRepayment = Math.min(money, maxRepayment);
        if (possibleRepayment > 0) {
            wallet.addMoney(-possibleRepayment);
            debt -= possibleRepayment;
        }
    }

    private void placeNewOrders() {
        @NotNull MarketOrderContinuous
                buyOrder = new MarketOrderContinuous(this,
                        granaryPriceCalculator.getBuyPrice(),
                        granaryPriceCalculator.getBuyVolume()),
                sellOrder = new MarketOrderContinuous(this,
                        granaryPriceCalculator.getSellPrice(),
                        granaryPriceCalculator.getSellVolume());
        this.buyOrder = buyOrder;
        this.sellOrder = sellOrder;
        foodMarket.placeBuyOrder(buyOrder);
        foodMarket.placeSellOrder(sellOrder);
    }

    //for asset value calculation
    public double getReferencePrice() {
        return referencePrice;
    }

    //
    public double getTotalAssets() {
        return totalAssets;
    }

    //
    public double getLiquidity() {
        return wallet.getMoney() / totalAssets;
    }

    //
    public double getDebt() {
        return debt;
    }

    //
    public double getDebtToAssetRatio() {
        return debt / totalAssets;
    }
}
package markets2.granary;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ParticularResources;

//calculates the prices and volumes for granary's trade orders
final class GranaryPriceCalculator {
    private final @NotNull Granary granary;
    private double
            buyPrice,
            sellPrice,
            buyVolume = 0,
            sellVolume = 0;

    //
    GranaryPriceCalculator(@NotNull Granary granary) {
        this.granary = granary;
        double referencePrice = granary.getReferencePrice();
        buyPrice = referencePrice;
        sellPrice = referencePrice;
    }

    //
    void update() {
        calculatePrices();
        calculateVolumes();
    }

    private void calculatePrices() {
        double referencePrice = granary.getReferencePrice();
        buyPrice = referencePrice;
        sellPrice = referencePrice;

        //TODO: finish this

        double liquidity = granary.getLiquidity();
        if (liquidity < Granary.TARGET_LIQUIDITY_LOW) { //has low money at hand, preferential sell regime, naturally lowers prices
            setPrices_increased();
        } else if (liquidity > Granary.TARGET_LIQUIDITY_HIGH) { //has plenty of money at hand, preferential buy regime, increases prices
            setPrices_decreased();
        } else { //medium liquidity, follow market at margin/2
            setPrices_normal();
        }
    }

    private void setPrices_normal() {
        buyPrice *= (1 - Granary.PRICE_MARGIN_NORMAL / 2);
        sellPrice *= (1 + Granary.PRICE_MARGIN_NORMAL / 2);

        //buyPrice *= (1 - PRICE_MARGIN_NORMAL);
    }

    private void setPrices_increased() {
        sellPrice *= (1 + Granary.PRICE_MARGIN_PREFERENTIAL);
        buyPrice *= (1 + (Granary.PRICE_MARGIN_PREFERENTIAL - Granary.PRICE_MARGIN_NORMAL));

        //sellPrice *= (1 + PRICE_MARGIN_NORMAL);
    }

    private void setPrices_decreased() {
        buyPrice *= (1 - Granary.PRICE_MARGIN_PREFERENTIAL);
        sellPrice *= (1 - (Granary.PRICE_MARGIN_PREFERENTIAL - Granary.PRICE_MARGIN_NORMAL));

        //buyPrice *= (1 - PRICE_MARGIN_NORMAL);
    }

    private void calculateVolumes() {
        double maxBuyFraction = 1.0 / 3;
        double money = granary.getWallet().getMoney();
        double foodAmount = granary.getInventory().getContinuousResourceAmount(ParticularResources.FOOD);

        buyVolume = money / buyPrice * maxBuyFraction; //buy a fraction of all-in, so some liquidity remains for next purchase
        sellVolume = foodAmount; //put all to sell
    }

    //
    double getBuyPrice() {
        return buyPrice;
    }

    //
    double getSellPrice() {
        return sellPrice;
    }

    //
    double getBuyVolume() {
        return buyVolume;
    }

    //
    double getSellVolume() {
        return sellVolume;
    }
}
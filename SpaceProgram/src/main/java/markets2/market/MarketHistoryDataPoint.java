package markets2.market;

import org.jetbrains.annotations.NotNull;

//
public final class MarketHistoryDataPoint<V extends @NotNull Number> {
    private final int time;
    private final V volume;
    private final double
            priceVWA,
            priceLowestUnfulfilledSell,
            priceHighestUnfulfilledBuy;

    //
    MarketHistoryDataPoint(int time, V volume, double priceVWA,
                           double priceLowestUnfulfilledSell, double priceHighestUnfulfilledBuy) {
        this.time = time;
        this.volume = volume;
        this.priceVWA = priceVWA;
        this.priceLowestUnfulfilledSell = priceLowestUnfulfilledSell;
        this.priceHighestUnfulfilledBuy = priceHighestUnfulfilledBuy;
    }

    //
    public int getTime() {
        return time;
    }

    //
    public V getVolume() {
        return volume;
    }

    //
    public double getPriceVWA() {
        return priceVWA;
    }

    //
    public double getPriceLowestUnfulfilledSell() {
        return priceLowestUnfulfilledSell;
    }

    //
    public double getPriceHighestUnfulfilledBuy() {
        return priceHighestUnfulfilledBuy;
    }
}
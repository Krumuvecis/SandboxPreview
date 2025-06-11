package markets2.market;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//
public abstract class MarketHistory<V extends @NotNull Number> {
    private final @NotNull List<@NotNull MarketHistoryDataPoint<V>> history;

    //
    MarketHistory() {
        history = new ArrayList<>();
    }

    //
    public final @NotNull List<@NotNull MarketHistoryDataPoint<V>> getAll() {
        return history;
    }

    //
    public final @Nullable MarketHistoryDataPoint<V> getLast() {
        if (history.isEmpty()) {
            return null;
        } else {
            return history.getLast();
        }
    }

    //
    public final @Nullable MarketHistoryDataPoint<V> getLastPositiveVolumeData() {
        if (history.isEmpty()) {
            return null;
        } else {
            @Nullable MarketHistoryDataPoint<V> result = null;
            for (@NotNull MarketHistoryDataPoint<V> dataPoint : history.reversed()) {
                if (isPositive(dataPoint.getVolume())) {
                    result = dataPoint;
                    break;
                }
            }
            return result;
        }
    }

    //
    final void addDataPoint(int time, V totalVolume, double valueVW,
                      @Nullable MarketOrder<V> lowestUnfulfilledSellOrder,
                      @Nullable MarketOrder<V> highestUnfulfilledBuyOrder) {
        history.add(new MarketHistoryDataPoint<V>(time, totalVolume, calculatePriceVWA(totalVolume, valueVW),
                getOrderPrice(lowestUnfulfilledSellOrder, Double.POSITIVE_INFINITY),
                getOrderPrice(highestUnfulfilledBuyOrder, 0)));
    }

    private double calculatePriceVWA(V totalVolume, double valueVW) {
        double priceVWA;
        if (isPositive(totalVolume)) {
            priceVWA = divideDouble(valueVW, totalVolume);
        } else { //no trades happened, use last historical price
            @Nullable MarketHistoryDataPoint<V> lastHistoricalData = getLast();
            if (lastHistoricalData == null) { //first data point
                priceVWA = 0;
            } else {
                priceVWA = lastHistoricalData.getPriceVWA();
            }
        }
        return priceVWA;
    }

    private double getOrderPrice(@Nullable MarketOrder<V> order, double nonNullPrice) {
        if (order == null) {
            return nonNullPrice;
        } else {
            return order.getPrice();
        }
    }

    //
    abstract boolean isPositive(V value);

    //
    abstract double divideDouble(double dividend, V divisor);
}
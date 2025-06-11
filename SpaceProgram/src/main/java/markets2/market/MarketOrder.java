package markets2.market;

import org.jetbrains.annotations.NotNull;

import markets2.TraderInterface;

//
public abstract class MarketOrder<V extends @NotNull Number> {
    private final @NotNull TraderInterface trader;
    private final double price;
    private V volume;

    MarketOrder(@NotNull TraderInterface trader, double price, V volume) {
        this.trader = trader;
        this.price = price;
        this.volume = volume;
    }

    //
    public final @NotNull TraderInterface getTrader() {
        return trader;
    }

    //
    public final double getPrice() {
        return price;
    }

    //
    public final V getVolume() {
        return volume;
    }

    //
    public final void decreaseVolume(V delta) {
        volume = subtract(volume, delta);
    }

    //
    public final boolean isComplete() {
        return isNonPositive(volume);
    }

    //
    abstract V subtract(V minuend, V subtrahend);

    //
    abstract boolean isNonPositive(V value);

    //
    public static final class MarketOrderContinuous extends MarketOrder<@NotNull Double> {
        //
        public MarketOrderContinuous(@NotNull TraderInterface trader, double price, @NotNull Double volume) {
            super(trader, price, volume);
        }

        //
        @Override
        @NotNull Double subtract(@NotNull Double minuend, @NotNull Double subtrahend) {
            return minuend - subtrahend;
        }

        //
        @Override
        boolean isNonPositive(@NotNull Double value) {
            return value <= 0;
        }
    }

    //
    public static final class MarketOrderDiscrete extends MarketOrder<@NotNull Integer> {
        //
        public MarketOrderDiscrete(@NotNull TraderInterface trader, double price, @NotNull Integer volume) {
            super(trader, price, volume);
        }

        //
        @Override
        @NotNull Integer subtract(@NotNull Integer minuend, @NotNull Integer subtrahend) {
            return minuend - subtrahend;
        }

        //
        @Override
        boolean isNonPositive(@NotNull Integer value) {
            return value <= 0;
        }
    }
}
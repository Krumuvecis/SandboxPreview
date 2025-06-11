package markets2.market;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import markets2.UpdatableInterface;

//
public interface SingleResourceMarketInterface<V extends @NotNull Number, T extends MarketOrder<V>>
        extends UpdatableInterface {
    //
    @NotNull MarketHistory<V> getHistory();

    //current unfulfilled sell orders
    @NotNull @Unmodifiable List<@NotNull T> getSortedSellOrders();

    //current unfulfilled buy orders
    @NotNull @Unmodifiable List<@NotNull T> getSortedBuyOrders();

    //
    void placeSellOrder(@NotNull T order);

    //
    void placeBuyOrder(@NotNull T order);

    //
    void retractSellOrder(@NotNull T order);

    //
    void retractBuyOrder(@NotNull T order);
}
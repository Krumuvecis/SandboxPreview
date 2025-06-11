package markets2.market;

import java.util.Set;
import java.util.HashSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

//
abstract class QueuedSingleResourceMarket<V extends @NotNull Number, T extends MarketOrder<V>>
        implements SingleResourceMarketInterface<V, T> {
    private final @NotNull Set<@NotNull T> //new order queues
            sellOrderQueue = new HashSet<>(),
            buyOrderQueue = new HashSet<>();

    //
    QueuedSingleResourceMarket() {}

    //
    final @NotNull @Unmodifiable Set<@NotNull T> getSellOrderQueue() {
        return Set.copyOf(sellOrderQueue);
    }

    //
    final @NotNull @Unmodifiable Set<@NotNull T> getBuyOrderQueue() {
        return Set.copyOf(buyOrderQueue);
    }

    //
    abstract void transferQueues();

    //
    final void clearQueues() {
        sellOrderQueue.clear();
        buyOrderQueue.clear();
    }

    //
    @Override
    public final void placeSellOrder(@NotNull T order) {
        if (positiveOrderCheck(order)) sellOrderQueue.add(order);
    }

    //
    @Override
    public final void placeBuyOrder(@NotNull T order) {
        if (positiveOrderCheck(order)) buyOrderQueue.add(order);
    }

    private boolean positiveOrderCheck(@NotNull T order) {
        return order.getPrice() > 0 && isPositive(order.getVolume());
    }

    //
    abstract boolean isPositive(V value);
}
package markets2.market;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.UpdatableInterface;

//
abstract class MarketOrderContainer<T extends MarketOrder<? extends @NotNull Number>> implements UpdatableInterface {
    private final @NotNull Set<@NotNull T> orderSet = new HashSet<>(); //actual unfulfilled orders; unsorted, retains orders from cycle to cycle
    private @NotNull List<@NotNull T> sortedOrders = new ArrayList<>(); //actual unfulfilled orders; sorted, gets re-generated every cycle

    //
    MarketOrderContainer() {}

    //used for sorting
    final @NotNull @Unmodifiable Set<@NotNull T> getUnsorted() {
        return Set.copyOf(orderSet);
    }

    //
    public final @NotNull @Unmodifiable List<@NotNull T> getSorted() {
        return List.copyOf(sortedOrders);
    }

    //
    public final @Nullable T getFirst() {
        try {
            return getSorted().getFirst();
        } catch (@NotNull NoSuchElementException ignored) {
            return null;
        }
    }

    //
    @Override
    public final void update() {
        sort();
    }

    //
    abstract void sort();

    //used for sorting
    final void setSortedOrders(@NotNull List<@NotNull T> sortedOrders) {
        this.sortedOrders = sortedOrders;
    }

    //
    final void add(@NotNull Set<@NotNull T> orders) {
        orderSet.addAll(orders);
    }

    //
    final void remove(@NotNull T order) {
        orderSet.remove(order);
        sortedOrders.remove(order);
    }

    //
    static final class SellOrderContainer<T extends MarketOrder<? extends @NotNull Number>>
            extends MarketOrderContainer<T> {
        //
        SellOrderContainer() {
            super();
        }

        //
        @Override
        void sort() {
            @NotNull Set<@NotNull T> sortableOrders = new HashSet<>(getUnsorted());
            @NotNull List<@NotNull T> sortedOrders = new ArrayList<>();
            while (!sortableOrders.isEmpty()) {
                double lowestSellPrice = Double.MAX_VALUE;
                @Nullable T lowestSellOrder = null;
                for (@NotNull T order : sortableOrders) {
                    double price = order.getPrice();
                    if (price < lowestSellPrice) {
                        lowestSellPrice = price;
                        lowestSellOrder = order;
                    }
                }
                if (lowestSellOrder == null) {
                    break;
                } else {
                    sortableOrders.remove(lowestSellOrder);
                    sortedOrders.add(lowestSellOrder);
                }
            }
            setSortedOrders(sortedOrders);
        }
    }

    //
    static final class BuyOrderContainer<T extends MarketOrder<? extends @NotNull Number>>
            extends MarketOrderContainer<T> {
        //
        BuyOrderContainer() {
            super();
        }

        //
        @Override
        void sort() {
            @NotNull Set<@NotNull T> sortableOrders = new HashSet<>(getUnsorted());
            @NotNull List<@NotNull T> sortedOrders = new ArrayList<>();
            while (!sortableOrders.isEmpty()) {
                double highestBuyPrice = 0;
                @Nullable T highestBuyOrder = null;
                for (@NotNull T order : sortableOrders) {
                    double price = order.getPrice();
                    if (price > highestBuyPrice) {
                        highestBuyPrice = price;
                        highestBuyOrder = order;
                    }
                }
                if (highestBuyOrder == null) {
                    break;
                } else {
                    sortableOrders.remove(highestBuyOrder);
                    sortedOrders.add(highestBuyOrder);
                }
            }
            setSortedOrders(sortedOrders);
        }
    }
}
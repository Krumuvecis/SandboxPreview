package markets2;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//
public final class Market {
    private final @NotNull World world; //reference
    private final @NotNull List<@NotNull MarketHistoryDataPoint> history;
    private final @NotNull Set<@NotNull Order>
            sellOrders,
            buyOrders;
    private @NotNull List<@NotNull Order>
            sortedSellOrders,
            sortedBuyOrders;

    //
    public Market(@NotNull World world) {
        this.world = world;
        history = new ArrayList<>();
        sellOrders = new HashSet<>();
        buyOrders = new HashSet<>();
        sortedSellOrders = sortSellOrders();
        sortedBuyOrders = sortBuyOrders();
    }

    //
    public @NotNull List<@NotNull MarketHistoryDataPoint> getHistory() {
        return history;
    }

    private @NotNull List<@NotNull Order> sortSellOrders() {
        @NotNull List<@NotNull Order> unsortedOrders = new ArrayList<>(List.copyOf(sellOrders));
        @NotNull List<@NotNull Order> sortedOrders = new ArrayList<>();
        while (!unsortedOrders.isEmpty()) {
            double lowestSellPrice = Double.MAX_VALUE;
            @Nullable Order lowestSellOrder = null;
            for (@NotNull Order order : unsortedOrders) {
                double price = order.getPrice();
                if (price < lowestSellPrice) {
                    lowestSellPrice = price;
                    lowestSellOrder = order;
                }
            }
            if (lowestSellOrder != null) {
                sortedOrders.add(lowestSellOrder);
                unsortedOrders.remove(lowestSellOrder);
            } else {
                break;
            }
        }
        return sortedOrders;
    }

    private @NotNull List<@NotNull Order> sortBuyOrders() {
        @NotNull List<@NotNull Order> unsortedOrders = new ArrayList<>(List.copyOf(buyOrders));
        @NotNull List<@NotNull Order> sortedOrders = new ArrayList<>();
        while (!unsortedOrders.isEmpty()) {
            double highestBuyPrice = 0;
            @Nullable Order highestBuyOrder = null;
            for (@NotNull Order order : unsortedOrders) {
                double price = order.price;
                if (price > highestBuyPrice) {
                    highestBuyPrice = price;
                    highestBuyOrder = order;
                }
            }
            if (highestBuyOrder != null) {
                sortedOrders.add(highestBuyOrder);
                unsortedOrders.remove(highestBuyOrder);
            } else {
                break;
            }
        }
        return sortedOrders;
    }

    //
    public @NotNull List<@NotNull Order> getSortedSellOrders() {
        return sortedSellOrders;
    }

    //
    public @Nullable Order getLowestSellOrder() {
        if (sortedSellOrders.isEmpty()) {
            return null;
        } else {
            return sortedSellOrders.get(0);
        }
    }

    //
    public @NotNull List<@NotNull Order> getSortedBuyOrders() {
        return sortedBuyOrders;
    }

    //
    public @Nullable Order getHighestBuyOrder() {
        if (sortedBuyOrders.isEmpty()) {
            return null;
        } else {
            return sortedBuyOrders.get(0);
        }
    }

    //
    public void placeSellOrder(@NotNull Order order) {
        if (order.price > 0 && order.amount > 0) {
            sellOrders.add(order);
        }
    }

    //
    public void placeBuyOrder(@NotNull Order order) {
        if (order.price > 0 && order.amount > 0) {
            buyOrders.add(order);
        }
    }

    //
    public void update() {
        //sort newly placed orders
        sortedSellOrders = sortSellOrders();
        sortedBuyOrders = sortBuyOrders();

        int time = world.getElapsedTime();

        double startPrice = 0;
        if (time > 0) {
            startPrice = history.get(time - 1).endPrice();
        }
        double
                endPrice = startPrice,
                totalVolume = 0,
                VWPrice = 0;

        //fulfill orders
        while (true) {
            @Nullable Order
                    lowestSellOrder = getLowestSellOrder(),
                    highestBuyOrder = getHighestBuyOrder();
            if (lowestSellOrder == null || highestBuyOrder == null) {
                break;
            } else {
                double spread = highestBuyOrder.getPrice() - lowestSellOrder.getPrice();
                if (spread < 0) { //prices don't meet, deal doesn't happen
                    break;
                } else { //prices overlap, a deal happens
                    double
                            price = lowestSellOrder.getPrice() + spread / 2,
                            volume = Math.min(lowestSellOrder.getAmount(), highestBuyOrder.getAmount());

                    @NotNull TraderInterface
                            seller = lowestSellOrder.getTrader(),
                            buyer = highestBuyOrder.getTrader();

                    seller.getInventory().money += volume * price;
                    seller.getInventory().food -= volume;
                    lowestSellOrder.decreaseAmount(volume);
                    if (lowestSellOrder.isComplete()) {
                        sellOrders.remove(lowestSellOrder);
                        sortedSellOrders.remove(lowestSellOrder);
                    }

                    buyer.getInventory().money -= volume * price;
                    buyer.getInventory().food += volume;
                    highestBuyOrder.decreaseAmount(volume);
                    if (highestBuyOrder.isComplete()) {
                        buyOrders.remove(highestBuyOrder);
                        sortedBuyOrders.remove(highestBuyOrder);
                    }

                    //TODO: somehow notify the people about their orders, so that they don't make new ones
                    //remove fulfilled orders

                    endPrice = price;
                    totalVolume += volume;
                    VWPrice += volume * price;
                }
            }
        }

        //update history
        double VWAPrice = startPrice;
        if (totalVolume > 0) {
            VWAPrice = VWPrice / totalVolume;
        }
        history.add(new MarketHistoryDataPoint(time, startPrice, endPrice, totalVolume, VWAPrice));

        sellOrders.clear();
        buyOrders.clear();
    }

    //
    public static class Order {
        private final @NotNull TraderInterface trader;
        private final double price;
        private double amount;

        public Order(@NotNull TraderInterface trader, double price, double amount) {
            this.trader = trader;
            this.price = price;
            this.amount = amount;
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
        public final double getAmount() {
            return amount;
        }

        //
        public final void decreaseAmount(double delta) {
            amount -= delta;
        }

        //
        public final boolean isComplete() {
            return amount <= 0;
        }
    }

    //
    public record MarketHistoryDataPoint(int time, double startPrice, double endPrice, double volume, double VWAPrice) {}
}
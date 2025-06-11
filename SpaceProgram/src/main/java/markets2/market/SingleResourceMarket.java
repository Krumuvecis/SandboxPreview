package markets2.market;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ResourceInterface;
import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.market.MarketOrder.MarketOrderContinuous;
import markets2.market.MarketOrder.MarketOrderDiscrete;
import markets2.TraderInterface;
import markets2.World;

//
public abstract class SingleResourceMarket<K extends @NotNull ResourceInterface, V extends @NotNull Number, T extends MarketOrder<V>>
        extends QueuedSingleResourceMarket<V, T> {
    private final @NotNull World world; //reference
    private final K resource; //reference
    private final @NotNull MarketHistory<V> history;
    private final @NotNull MarketOrderContainer<@NotNull T>
            sellOrders = new MarketOrderContainer.SellOrderContainer<>(),
            buyOrders = new MarketOrderContainer.BuyOrderContainer<>();

    //
    SingleResourceMarket(@NotNull World world, K resource) {
        this.world = world;
        this.resource = resource;
        history = newHistory();
    }

    //
    abstract @NotNull MarketHistory<V> newHistory();

    //for internal use
    K getResource() {
        return resource;
    }

    //
    @Override
    public final @NotNull MarketHistory<V> getHistory() {
        return history;
    }

    //
    @Override
    public final @NotNull @Unmodifiable List<@NotNull T> getSortedSellOrders() {
        return sellOrders.getSorted();
    }

    //
    @Override
    public final @NotNull @Unmodifiable List<@NotNull T> getSortedBuyOrders() {
        return buyOrders.getSorted();
    }

    //
    private @Nullable T getLowestSellOrder() {
        return sellOrders.getFirst();
    }

    //
    private @Nullable T getHighestBuyOrder() {
        return buyOrders.getFirst();
    }

    //
    @Override
    public void retractSellOrder(@NotNull T order) {
        sellOrders.remove(order);
    }

    //
    @Override
    public void retractBuyOrder(@NotNull T order) {
        buyOrders.remove(order);
    }

    //transfers new orders from queues to containers, clears queues afterwards
    @Override
    final void transferQueues() {
        sellOrders.add(getSellOrderQueue());
        buyOrders.add(getBuyOrderQueue());
        clearQueues();
    }

    //
    @Override
    public final void update() {
        transferQueues();
        sellOrders.update();
        buyOrders.update();
        fulfillOrders();
    }

    private void fulfillOrders() {
        V totalVolume = getZero();
        double valueVW = 0;
        @Nullable T
                lowestSellOrder,
                highestBuyOrder;
        while (true) {
            lowestSellOrder = getLowestSellOrder();
            highestBuyOrder = getHighestBuyOrder();

            if (lowestSellOrder == null || highestBuyOrder == null) {
                break;
            } else { //both sell and buy orders exist
                double
                        priceSell = lowestSellOrder.getPrice(),
                        priceBuy = highestBuyOrder.getPrice();
                if (priceBuy < priceSell) { //prices don't meet, deal doesn't happen
                    break;
                } else { //prices overlap, a deal happens
                    double price = (priceSell + priceBuy) / 2;
                    V volume = min(lowestSellOrder.getVolume(), highestBuyOrder.getVolume());
                    performSale(lowestSellOrder, price, volume);
                    performPurchase(highestBuyOrder, price, volume);

                    totalVolume = sum(totalVolume, volume);
                    valueVW += multiplyPrice(price, volume);
                }
            }
        }
        history.addDataPoint(world.getElapsedTime(), totalVolume, valueVW, lowestSellOrder, highestBuyOrder);
    }

    private void performSale(@NotNull T sellOrder, double price, V volume) {
        @NotNull TraderInterface seller = sellOrder.getTrader();
        seller.getWallet().addMoney(multiplyPrice(price, volume));
        sellerSubtractResource(seller, volume);
        sellOrder.decreaseVolume(volume);
        if (sellOrder.isComplete()) {
            sellOrders.remove(sellOrder);
            seller.removeCompletedSellOrder(getResource(), sellOrder);
        }
    }

    private void performPurchase(@NotNull T buyOrder, double price, V volume) {
        @NotNull TraderInterface buyer = buyOrder.getTrader();
        buyer.getWallet().addMoney(multiplyPrice(-price, volume));
        buyerAddResource(buyer, volume);
        buyOrder.decreaseVolume(volume);
        if (buyOrder.isComplete()) {
            buyOrders.remove(buyOrder);
            buyer.removeCompletedBuyOrder(getResource(), buyOrder);
        }
    }

    //for internal use
    abstract V getZero();

    //for internal use
    abstract V sum(V augend, V addend);

    //for internal use
    abstract V min(V var1, V var2);

    //for internal use
    abstract double multiplyPrice(double price, V volume);

    //for internal use
    abstract void sellerSubtractResource(@NotNull TraderInterface seller, V volume);

    //for internal use
    abstract void buyerAddResource(@NotNull TraderInterface buyer, V volume);

    //
    public static final class SingleResourceMarketContinuous
            extends SingleResourceMarket<@NotNull ContinuousResource, @NotNull Double, @NotNull MarketOrderContinuous> {
        SingleResourceMarketContinuous(@NotNull World world, @NotNull ContinuousResource resource) {
            super(world, resource);
        }

        //
        @Override
        @NotNull MarketHistory<@NotNull Double> newHistory() {
            return new MarketHistory<@NotNull Double>() {
                //
                @Override
                boolean isPositive(@NotNull Double value) {
                    return value > 0;
                }

                //
                @Override
                double divideDouble(double dividend, @NotNull Double divisor) {
                    return dividend / divisor;
                }
            };
        }

        //
        @Override
        boolean isPositive(@NotNull Double value) {
            return value > 0;
        }

        //
        @Override
        @NotNull Double getZero() {
            return (double) 0;
        }

        //
        @Override
        @NotNull Double sum(@NotNull Double augend, @NotNull Double addend) {
            return augend + addend;
        }

        //
        @Override
        @NotNull Double min(@NotNull Double var1, @NotNull Double var2) {
            return Math.min(var1, var2);
        }

        //
        @Override
        double multiplyPrice(double price, @NotNull Double volume) {
            return price * volume;
        }

        //
        @Override
        void sellerSubtractResource(@NotNull TraderInterface seller, @NotNull Double volume) {
            seller.getInventory().subtractContinuousResource(getResource(), volume);
        }

        //
        @Override
        void buyerAddResource(@NotNull TraderInterface buyer, @NotNull Double volume) {
            buyer.getInventory().addContinuousResource(getResource(), volume);
        }
    }

    //
    public static final class SingleResourceMarketDiscrete
            extends SingleResourceMarket<@NotNull DiscreteResource, @NotNull Integer, @NotNull MarketOrderDiscrete> {
        //
        SingleResourceMarketDiscrete(@NotNull World world, @NotNull DiscreteResource resource) {
            super(world, resource);
        }

        //
        @Override
        @NotNull MarketHistory<@NotNull Integer> newHistory() {
            return new MarketHistory<@NotNull Integer>() {
                //
                @Override
                boolean isPositive(@NotNull Integer value) {
                    return value > 0;
                }

                //
                @Override
                double divideDouble(double dividend, @NotNull Integer divisor) {
                    return dividend / divisor;
                }
            };
        }

        //
        @Override
        boolean isPositive(@NotNull Integer value) {
            return value > 0;
        }

        //
        @Override
        @NotNull Integer getZero() {
            return 0;
        }

        //
        @Override
        @NotNull Integer sum(@NotNull Integer augend, @NotNull Integer addend) {
            return augend + addend;
        }

        //
        @Override
        @NotNull Integer min(@NotNull Integer var1, @NotNull Integer var2) {
            return Math.min(var1, var2);
        }

        //
        @Override
        double multiplyPrice(double price, @NotNull Integer volume) {
            return price * volume;
        }

        //
        @Override
        void sellerSubtractResource(@NotNull TraderInterface seller, @NotNull Integer volume) {
            seller.getInventory().subtractDiscreteResource(getResource(), volume);
        }

        //
        @Override
        void buyerAddResource(@NotNull TraderInterface buyer, @NotNull Integer volume) {
            buyer.getInventory().addDiscreteResource(getResource(), volume);
        }
    }
}
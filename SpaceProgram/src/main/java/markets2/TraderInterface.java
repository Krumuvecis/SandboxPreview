package markets2;

import markets2.resources.containers.ResourceContainerInterface;
import org.jetbrains.annotations.NotNull;

import common.NamedInterface;
import markets2.resources.ResourceInterface;
import markets2.market.MarketOrder;

//
public interface TraderInterface extends NamedInterface {
    //
    @NotNull Wallet getWallet();

    //
    @NotNull ResourceContainerInterface getInventory();

    //
    void removeCompletedSellOrder(@NotNull ResourceInterface resource,
                                  @NotNull MarketOrder<? extends @NotNull Number> order);

    //
    void removeCompletedBuyOrder(@NotNull ResourceInterface resource,
                                 @NotNull MarketOrder<? extends @NotNull Number> order);
}
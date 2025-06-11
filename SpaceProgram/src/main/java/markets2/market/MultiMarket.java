package markets2.market;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ResourceInterface;
import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.market.SingleResourceMarket.SingleResourceMarketContinuous;
import markets2.market.SingleResourceMarket.SingleResourceMarketDiscrete;
import markets2.UpdatableInterface;
import markets2.World;

//
public class MultiMarket implements UpdatableInterface {
    private final @NotNull World world; //reference
    private final @NotNull Map<@NotNull ContinuousResource, @NotNull SingleResourceMarketContinuous>
            continuousResourceMarkets;
    private final @NotNull Map<@NotNull DiscreteResource, @NotNull SingleResourceMarketDiscrete>
            discreteResourceMarkets;

    //
    public MultiMarket(@NotNull World world) {
        this.world = world;
        continuousResourceMarkets = new HashMap<>();
        discreteResourceMarkets = new HashMap<>();
    }

    //continuous; if none found, creates a new single-resource market
    public final @NotNull SingleResourceMarketContinuous getMarket(@NotNull ContinuousResource resource) {
        @Nullable SingleResourceMarketContinuous market = continuousResourceMarkets.get(resource);
        if (market == null) {
            @NotNull SingleResourceMarketContinuous newMarket = new SingleResourceMarketContinuous(world, resource);
            continuousResourceMarkets.put(resource, newMarket);
            return newMarket;
        } else {
            return market;
        }
    }

    //discrete; if none found, creates a new single-resource market
    public final @NotNull SingleResourceMarketDiscrete getMarket(@NotNull DiscreteResource resource) {
        @Nullable SingleResourceMarketDiscrete market = discreteResourceMarkets.get(resource);
        if (market == null) {
            @NotNull SingleResourceMarketDiscrete newMarket = new SingleResourceMarketDiscrete(world, resource);
            discreteResourceMarkets.put(resource, newMarket);
            return newMarket;
        } else {
            return market;
        }
    }

    //for update and output purposes
    public final @NotNull @Unmodifiable Map<
            @NotNull ResourceInterface,
            @NotNull SingleResourceMarket<
                    ? extends @NotNull ResourceInterface,
                    ? extends @NotNull Number,
                    ? extends @NotNull MarketOrder<? extends @NotNull Number>>> getAllMarkets() {
        return Collections.unmodifiableMap(new HashMap<>() {{
            putAll(continuousResourceMarkets);
            putAll(discreteResourceMarkets);
        }});
    }

    //
    @Override
    public final void update() {
        @NotNull @Unmodifiable Map<
                @NotNull ResourceInterface,
                @NotNull SingleResourceMarket<
                        ? extends @NotNull ResourceInterface,
                        ? extends @NotNull Number,
                        ? extends @NotNull MarketOrder<? extends @NotNull Number>>> allMarkets = getAllMarkets();
        for (@NotNull ResourceInterface resource : allMarkets.keySet()) {
            allMarkets.get(resource).update();
        }
    }
}
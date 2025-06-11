package markets2.resources.containers;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ResourceInterface;
import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;

//
public class Inventory implements ContinuousResourceContainerInterface, DiscreteResourceContainerInterface {
    private static final double DEFAULT_MAXIMUM_CAPACITY = 20;
    private final double maximumCapacity;
    private final @NotNull Map<@NotNull ContinuousResource, @NotNull Double> continuousResources;
    private final @NotNull Map<@NotNull DiscreteResource, @NotNull Integer> discreteResources;

    //custom maximum capacity
    public Inventory(double maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        continuousResources = new HashMap<>();
        discreteResources = new HashMap<>();
    }

    //default maximum capacity
    public Inventory() {
        this(DEFAULT_MAXIMUM_CAPACITY);
    }

    //absolute amount
    @Override
    public final double getMaximumCapacity() {
        return maximumCapacity;
    }

    //absolute amount
    @Override
    public final double getFilledCapacity() {
        return getContinuousResourcesTotalAmount() + getDiscreteResourcesTotalAmount();
    }

    //
    @Override
    public final @NotNull @Unmodifiable Map<@NotNull ContinuousResource, @NotNull Double> getContinuousResources() {
        return Map.copyOf(continuousResources);
    }

    //
    @Override
    public final @NotNull @Unmodifiable Map<@NotNull DiscreteResource, @NotNull Integer> getDiscreteResources() {
        return Map.copyOf(discreteResources);
    }

    //for output purposes
    @Override
    public final @NotNull @Unmodifiable Map<@NotNull ResourceInterface, @NotNull Double> getAllResourceAmounts() {
        return Collections.unmodifiableMap(new HashMap<>() {{
            putAll(getContinuousResources());
            putAll(getDiscreteResourceAmounts());
        }});
    }

    //For internal use. Use either add() or subtract() instead.
    @Deprecated
    @Override
    public final void putContinuousResource(@NotNull ContinuousResource resource, double amount) {
        continuousResources.put(resource, amount);
    }

    //For internal use. Use either add() or subtract() instead.
    @Deprecated
    @Override
    public final void removeContinuousResource(@NotNull ContinuousResource resource) {
        continuousResources.remove(resource);
    }

    //For internal use. Use either add() or subtract() instead.
    @Deprecated
    @Override
    public final void putDiscreteResource(@NotNull DiscreteResource resource, int count) {
        discreteResources.put(resource, count);
    }

    //For internal use. Use either add() or subtract() instead.
    @Deprecated
    @Override
    public final void removeDiscreteResource(@NotNull DiscreteResource resource) {
        discreteResources.remove(resource);
    }
}
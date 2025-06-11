package markets2.resources.containers;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ResourceInterface;

//
public interface ResourceContainerInterface {
    //absolute amount
    double getMaximumCapacity();

    //absolute amount
    double getFilledCapacity();

    //absolute amount
    default double getRemainingCapacity() {
        return Math.max(0, getMaximumCapacity() - getFilledCapacity());
    }

    //fraction of maximum
    default double getCapacityFullness() {
        return getFilledCapacity() / getMaximumCapacity();
    }

    //for output purposes
    @NotNull @Unmodifiable Map<@NotNull ResourceInterface, @NotNull Double> getAllResourceAmounts();
}
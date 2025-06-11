package markets2.resources.containers;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.DiscreteResource;

//
public interface DiscreteResourceContainerInterface extends ResourceContainerInterface {
    //
    @NotNull @Unmodifiable Map<@NotNull DiscreteResource, @NotNull Integer> getDiscreteResources();

    //For internal use. Use either add() or subtract() instead.
    void putDiscreteResource(@NotNull DiscreteResource resource, int count);

    //For internal use. Use either add() or subtract() instead.
    void removeDiscreteResource(@NotNull DiscreteResource resource);

    //takes single-item size into account
    default @NotNull @Unmodifiable Map<@NotNull DiscreteResource, @NotNull Double> getDiscreteResourceAmounts() {
        return Collections.unmodifiableMap(new HashMap<>() {{
            @NotNull @Unmodifiable Map<@NotNull DiscreteResource, @NotNull Integer> resources = getDiscreteResources();
            for (@NotNull DiscreteResource resource : resources.keySet()) {
                put(resource, resources.get(resource) * resource.getSize());
            }
        }});
    }

    //
    default int getDiscreteResourceCount(@NotNull DiscreteResource resource) {
        return getDiscreteResources().getOrDefault(resource, 0);
    }

    //takes single-item size into account
    default double getDiscreteResourceAmount(@NotNull DiscreteResource resource) {
        return getDiscreteResourceCount(resource) * resource.getSize();
    }

    //takes single-item size into account
    default double getDiscreteResourcesTotalAmount() {
        @NotNull @Unmodifiable Map<@NotNull DiscreteResource, @NotNull Integer> resources = getDiscreteResources();
        double sum = 0;
        for (@NotNull DiscreteResource resource : resources.keySet()) {
            double amount = resources.get(resource) * resource.getSize();
            sum += amount;
        }
        return sum;
    }

    //
    default int getDiscreteResourcesTotalCount() {
        @NotNull @Unmodifiable Map<@NotNull DiscreteResource, @NotNull Integer> resources = getDiscreteResources();
        int sum = 0;
        for (@NotNull DiscreteResource resource : resources.keySet()) {
            int amount = resources.get(resource);
            sum += amount;
        }
        return sum;
    }

    //returns excess count, if exceeding maximum (takes single-item size into account)
    @SuppressWarnings("UnusedReturnValue")
    default int addDiscreteResource(@NotNull DiscreteResource resource, int count) {
        if (count > 0) {
            int addableCount = calculateDiscreteResourceAddableCount(resource, count);
            if (getDiscreteResources().containsKey(resource)) { //resource already contained
                putDiscreteResource(resource, getDiscreteResourceCount(resource) + addableCount);
            } else { //new resource
                putDiscreteResource(resource, addableCount);
            }
            return count - addableCount; //excess count
        } else { //adding non-positive count
            return count;
        }
    }

    private int calculateDiscreteResourceAddableCount(@NotNull DiscreteResource resource, int count) {
        double size = resource.getSize(); //single-item size
        if (size > 0) {
            return Math.min(count, (int) Math.floor(getRemainingCapacity() / size));
        } else { //zero-size resource, infinite maximum addable count
            return count;
        }
    }

    //returns remainder (count), if subtracting more than available (takes single-item size into account)
    @SuppressWarnings("UnusedReturnValue")
    default int subtractDiscreteResource(@NotNull DiscreteResource resource, int count) {
        if (count > 0) {
            int
                    availableCount = getDiscreteResourceCount(resource), //old count
                    subtractableCount = Math.min(count, availableCount), //delta
                    newCount = availableCount - subtractableCount;
            if (newCount > 0) {
                putDiscreteResource(resource, newCount);
            } else {
                removeDiscreteResource(resource);
            }
            return availableCount - subtractableCount; //non-subtractable remainder count
        } else { //subtracting non-positive count
            return count;
        }
    }
}
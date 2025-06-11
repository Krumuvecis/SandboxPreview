package markets2.resources.containers;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ContinuousResource;

//
public interface ContinuousResourceContainerInterface extends ResourceContainerInterface {
    //
    @NotNull @Unmodifiable Map<@NotNull ContinuousResource, @NotNull Double> getContinuousResources();

    //For internal use. Use either add() or subtract() instead.
    void putContinuousResource(@NotNull ContinuousResource resource, double amount);

    //For internal use. Use either add() or subtract() instead.
    void removeContinuousResource(@NotNull ContinuousResource resource);

    //
    default double getContinuousResourceAmount(@NotNull ContinuousResource resource) {
        return getContinuousResources().getOrDefault(resource, (double) 0);
    }

    default double getContinuousResourcesTotalAmount() {
        @NotNull @Unmodifiable Map<@NotNull ContinuousResource, @NotNull Double> resources = getContinuousResources();
        double sum = 0;
        for (@NotNull ContinuousResource resource : resources.keySet()) {
            double amount = resources.get(resource);
            sum += amount;
        }
        return sum;
    }

    //returns excess amount, if exceeding maximum
    @SuppressWarnings("UnusedReturnValue")
    default double addContinuousResource(@NotNull ContinuousResource resource, double amount) {
        if (amount > 0) {
            double addableAmount = calculateContinuousResourceAddableAmount(resource, amount);
            if (getContinuousResources().containsKey(resource)) { //resource already contained
                putContinuousResource(resource, getContinuousResourceAmount(resource) + addableAmount);
            } else { //new resource
                putContinuousResource(resource, addableAmount);
            }
            return amount - addableAmount; //excess amount
        } else { //adding non-positive amount
            return amount;
        }
    }

    private double calculateContinuousResourceAddableAmount(@NotNull ContinuousResource resource, double amount) {
        return Math.min(amount, getRemainingCapacity());
    }

    //returns remainder (amount), if subtracting more than available
    @SuppressWarnings("UnusedReturnValue")
    default double subtractContinuousResource(@NotNull ContinuousResource resource, double amount) {
        if (amount > 0) {
            double
                    availableAmount = getContinuousResourceAmount(resource), //old amount
                    subtractableAmount = Math.min(amount, availableAmount), //delta
                    newAmount = availableAmount - subtractableAmount;
            if (newAmount > 0) {
                putContinuousResource(resource, newAmount);
            } else {
                removeContinuousResource(resource);
            }
            return amount - subtractableAmount; //non-subtractable remainder amount
        } else { //subtracting non-positive amount
            return amount;
        }
    }
}
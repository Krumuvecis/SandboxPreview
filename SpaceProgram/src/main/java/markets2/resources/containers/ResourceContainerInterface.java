package markets2.resources.containers;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import markets2.resources.ResourceInterface;
import markets2.resources.DiscreteResource;
import markets2.resources.ContinuousResource;
import markets2.resources.UnrecognizedResourceType;
import markets2.resources.ResourceAmount;
import markets2.resources.ResourceAmount.DiscreteResourceAmount;
import markets2.resources.ResourceAmount.ContinuousResourceAmount;
import markets2.resources.ResourceCollection;

//
public interface ResourceContainerInterface extends ResourceCollection {
    //returns remainder
    @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> add(
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> addend)
            throws UnrecognizedResourceType;

    //subtracts, as much as possible; returns unsubtracted remainder of the subtrahend, if insufficient
    default @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> subtract(
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> subtrahend)
            throws UnrecognizedResourceType {
        @NotNull ResourceInterface resource = subtrahend.getResource();
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                minuend = get(resource);
        if (minuend == null) {
            return subtrahend.copy();
        } else {
            if (subtrahend instanceof @NotNull DiscreteResourceAmount discreteSubtrahend &&
                    minuend instanceof @NotNull DiscreteResourceAmount discreteMinuend) {
                return new DiscreteResourceAmount(
                        (DiscreteResource) resource,
                        discreteMinuend.subtract(discreteSubtrahend.getCount()));
            } else if (subtrahend instanceof @NotNull ContinuousResourceAmount continuousSubtrahend
                    && minuend instanceof @NotNull ContinuousResourceAmount continuousMinuend) {
                return new ContinuousResourceAmount(
                        (ContinuousResource) resource,
                        continuousMinuend.subtract(continuousSubtrahend.getMass()));
            } else {
                throw new UnrecognizedResourceType();
            }
        }
    }
}
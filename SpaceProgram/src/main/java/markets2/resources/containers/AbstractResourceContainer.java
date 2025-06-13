package markets2.resources.containers;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ResourceInterface;
import markets2.resources.DiscreteResource;
import markets2.resources.ContinuousResource;
import markets2.resources.UnrecognizedResourceType;
import markets2.resources.ResourceAmount;
import markets2.resources.ResourceAmount.DiscreteResourceAmount;
import markets2.resources.ResourceAmount.ContinuousResourceAmount;
import markets2.resources.ResourceCollection;

//
public abstract class AbstractResourceContainer implements ResourceCollection {
    //
    private final @NotNull Set<@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>>
            resourceAmounts = new HashSet<>();

    //
    AbstractResourceContainer() {}


    //
    @Override
    public final @NotNull @Unmodifiable Set<@NotNull ResourceAmount<
            ? extends @NotNull ResourceInterface, ? extends @NotNull Number>> getAll() {
        return Collections.unmodifiableSet(resourceAmounts);
    }

    //adds to set, if not contained; otherwise, ...
    @Override
    public final void put(@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> amount) {
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                current = get(amount.getResource());
        if (current == null) {
            resourceAmounts.add(amount);
        } else {
            //TODO: finish this
            throw new RuntimeException("Resource amount already contained...");
        }
    }

    //removes, if contained; otherwise does nothing
    @Override
    public final void remove(@NotNull ResourceInterface resource) {
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                amount = get(resource);
        if (amount != null) {
            resourceAmounts.remove(amount);
        }
    }

    //
    public abstract @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> add(
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> addend)
            throws UnrecognizedResourceType;

    //subtracts, as much as possible; returns unsubtracted remainder of the subtrahend, if insufficient
    public final @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> subtract(
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
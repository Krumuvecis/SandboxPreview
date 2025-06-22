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

//
public interface UnlimitedResourceContainer extends ResourceContainerInterface {
    //returns remainder; remainder always 0 for unlimited container
    @Override
    default @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> add(
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> addend)
            throws UnrecognizedResourceType {
        @NotNull ResourceInterface resource = addend.getResource();
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> augend = get(resource);
        if (addend instanceof @NotNull DiscreteResourceAmount discreteAddend) {
            addDiscrete(augend, discreteAddend);
            return new DiscreteResourceAmount((DiscreteResource) resource, 0);
        } else if (addend instanceof @NotNull ContinuousResourceAmount continuousAddend) {
            addContinuous(augend, continuousAddend);
            return new ContinuousResourceAmount((ContinuousResource) resource, (double) 0);
        } else {
            throw new UnrecognizedResourceType();
        }
    }

    private void addDiscrete(
            @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> augend,
            @NotNull DiscreteResourceAmount addend) throws UnrecognizedResourceType {
        if (!putIfNull(augend, addend) && augend instanceof @NotNull DiscreteResourceAmount discreteAugend) {
            discreteAugend.add(addend.getCount());
        } else {
            throw new UnrecognizedResourceType();
        }
    }

    private void addContinuous(
            @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> augend,
            @NotNull ContinuousResourceAmount addend) throws UnrecognizedResourceType {
        if (!putIfNull(augend, addend) && augend instanceof @NotNull ContinuousResourceAmount continuousAugend) {
            continuousAugend.add(addend.getMass());
        } else {
            throw new UnrecognizedResourceType();
        }
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean putIfNull(
            @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> augend,
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> addend) {
        if (augend == null) {
            put(addend);
            return true;
        } else {
            return false;
        }
    }
}
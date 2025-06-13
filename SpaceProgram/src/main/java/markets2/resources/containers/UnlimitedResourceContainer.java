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
public class UnlimitedResourceContainer extends AbstractResourceContainer {
    public UnlimitedResourceContainer() {
        super();
    }

    //returns remainder; remainder always 0 for unlimited container
    @Override
    public final @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> add(
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> addend)
            throws UnrecognizedResourceType {
        @NotNull ResourceInterface resource = addend.getResource();
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> augend = get(resource);

        if (addend instanceof @NotNull DiscreteResourceAmount discreteAddend) {
            if (augend == null) {
                put(addend);
            } else if (augend instanceof @NotNull DiscreteResourceAmount discreteAugend) {
                discreteAugend.add(discreteAddend.getCount());
            } else {
                throw new UnrecognizedResourceType();
            }
            return new DiscreteResourceAmount((DiscreteResource) resource, 0);
        } else if (addend instanceof @NotNull ContinuousResourceAmount continuousAddend) {
            if (augend == null) {
                put(addend);
            } else if (augend instanceof @NotNull ContinuousResourceAmount continuousAugend) {
                continuousAugend.add(continuousAddend.getMass());
            } else {
                throw new UnrecognizedResourceType();
            }
            return new ContinuousResourceAmount((ContinuousResource) resource, (double) 0);
        } else {
            throw new UnrecognizedResourceType();
        }
    }
}
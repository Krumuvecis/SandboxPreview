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
public interface LimitedResourceContainer extends ResourceContainerInterface, LimitedMassInterface, LimitedVolumeInterface {
    //
    @Override
    default double getTakenMass() {
        return getTotalMass();
    }

    //
    @Override
    default double getTakenVolume() {
        return getTotalVolume();
    }

    //returns remainder
    @Override
    default @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> add(
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> addend)
            throws UnrecognizedResourceType {
        @NotNull ResourceInterface resource = addend.getResource();

        if (addend instanceof @NotNull DiscreteResourceAmount discreteAddend){
            return new DiscreteResourceAmount((DiscreteResource) resource, addDiscrete(discreteAddend));
        } else if (addend instanceof @NotNull ContinuousResourceAmount continuousAddend) {
            return new ContinuousResourceAmount((ContinuousResource) resource, addContinuous(continuousAddend));
        } else {
            throw new UnrecognizedResourceType();
        }
    }

    private int addDiscrete(@NotNull DiscreteResourceAmount addend) throws UnrecognizedResourceType {
        double
                remainingMass = getRemaining_mass(),
                remainingVolume = getRemaining_volume(),
                addendMass = addend.getMass(),
                addendVolume = addend.getVolume(),
                satisfaction_min = 0;
        if (remainingMass >= 0 && remainingVolume >= 0) {
            satisfaction_min = Math.max(0, Math.min(
                    getMaxSatisfaction_perParameter(remainingMass, addendMass),
                    getMaxSatisfaction_perParameter(remainingVolume, addendVolume)));
        }

        int
                addendCount = addend.getCount(),
                cappedAddendCount = (int) Math.floor(addendCount * satisfaction_min);
        @NotNull DiscreteResource resource = addend.getResource();
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> augend = get(resource);
        if (augend == null) {
            put(new DiscreteResourceAmount(resource, cappedAddendCount));
        } else if (augend instanceof @NotNull DiscreteResourceAmount discreteAugend) {
            discreteAugend.add(cappedAddendCount);
        } else {
            throw new UnrecognizedResourceType();
        }
        return addendCount - cappedAddendCount;
    }

    private double addContinuous(@NotNull ContinuousResourceAmount addend) throws UnrecognizedResourceType {
        double
                remainingMass = getRemaining_mass(),
                remainingVolume = getRemaining_volume(),
                addendMass = addend.getMass(),
                addendVolume = addend.getVolume(),
                satisfaction_min = 0;
        if (remainingMass >= 0 && remainingVolume >= 0) {
            satisfaction_min = Math.max(0, Math.min(
                    getMaxSatisfaction_perParameter(remainingMass, addendMass),
                    getMaxSatisfaction_perParameter(remainingVolume, addendVolume)));
        }

        double cappedAddendMass = addendMass * satisfaction_min;
        @NotNull ContinuousResource resource = addend.getResource();
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> augend = get(resource);
        if (augend == null) {
            put(new ContinuousResourceAmount(resource, cappedAddendMass));
        } else if (augend instanceof @NotNull ContinuousResourceAmount continuousAugend) {
            continuousAugend.add(cappedAddendMass);
        } else {
            throw new UnrecognizedResourceType();
        }
        return addendMass - cappedAddendMass;
    }

    private double getMaxSatisfaction_perParameter(double remainingParameterLimit, double addendParameter) {
        if (addendParameter > 0) {
            double cappedAddendParameter = Math.max(0, Math.min(remainingParameterLimit, addendParameter));
            return cappedAddendParameter / addendParameter;
        } else {
            return 1;
        }
    }
}
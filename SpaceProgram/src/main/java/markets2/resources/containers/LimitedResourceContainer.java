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
public class LimitedResourceContainer extends AbstractResourceContainer implements LimitedMassInterface, LimitedVolumeInterface {
    private final double
            massLimit,
            volumeLimit;

    //
    public LimitedResourceContainer(double massLimit, double volumeLimit) {
        super();
        this.massLimit = massLimit;
        this.volumeLimit = volumeLimit;
    }

    //
    @Override
    public final double getTakenMass() {
        return getTotalMass();
    }

    //
    @Override
    public final double getTakenVolume() {
        return getTotalVolume();
    }

    //
    @Override
    public final double getLimit_mass() {
        return massLimit;
    }

    //
    @Override
    public final double getLimit_volume() {
        return volumeLimit;
    }

    //returns remainder
    public final @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> add(
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
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                augend = get(addend.getResource());

        //TODO: finish this
        //calculate available sizes here

        double
                remainingMass = getRemaining_mass(),
                remainingVolume = getRemaining_volume();
        if (remainingMass >= 0 && remainingVolume >= 0) {
            double
                    desiredAddableMass = addend.getMass(),
                    desiredAddableVolume = addend.getVolume(),
                    cappedAddableMass = Math.max(0, Math.min(remainingMass, desiredAddableMass)),
                    cappedAddableVolume = Math.max(0, Math.min(remainingVolume, desiredAddableVolume)),
                    massSatisfaction = 1,
                    volumeSatisfaction = 1;
            if (desiredAddableMass > 0) {
                massSatisfaction = cappedAddableMass / desiredAddableMass;
            }
            if (desiredAddableVolume > 0) {
                volumeSatisfaction = cappedAddableVolume / desiredAddableVolume;
            }
            double satisfaction = Math.min(massSatisfaction, volumeSatisfaction);

            //for discrete: amount = floor(amount * satisfaction)
        } else {
            //no room at all
            //TODO: return original parameter as remainder
        }

        if (augend == null) {
            //TODO: finish this
            //put(addend);
            return;
        } else if (augend instanceof @NotNull DiscreteResourceAmount discreteAugend) {
            //TODO: finish this
            //augend + addend
            return;
        } else {
            throw new UnrecognizedResourceType();
        }
    }

    private double addContinuous(@NotNull ContinuousResourceAmount addend) throws UnrecognizedResourceType {
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                augend = get(addend.getResource());

        //TODO: finish this
        //calculate available sizes here

        double
                remainingMass = getRemaining_mass(),
                remainingVolume = getRemaining_volume();
        if (remainingMass >= 0 && remainingVolume >= 0) {
            double
                    desiredAddableMass = addend.getMass(),
                    desiredAddableVolume = addend.getVolume(),
                    cappedAddableMass = Math.max(0, Math.min(remainingMass, desiredAddableMass)),
                    cappedAddableVolume = Math.max(0, Math.min(remainingVolume, desiredAddableVolume)),
                    massSatisfaction = 1,
                    volumeSatisfaction = 1;
            if (desiredAddableMass > 0) {
                massSatisfaction = cappedAddableMass / desiredAddableMass;
            }
            if (desiredAddableVolume > 0) {
                volumeSatisfaction = cappedAddableVolume / desiredAddableVolume;
            }
            double satisfaction = Math.min(massSatisfaction, volumeSatisfaction);

            //for continuous: amount *= satisfaction
        } else {
            //no room at all
            //TODO: return original parameter as remainder
        }

        if (augend == null) {
            //TODO: finish this
            //put(addend);
            return;
        } else if (augend instanceof @NotNull ContinuousResourceAmount continuousAugend) {
            //TODO: finish this
            //augend + addend
            return;
        } else {
            throw new UnrecognizedResourceType();
        }
    }
}
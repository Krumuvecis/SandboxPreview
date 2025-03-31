package rotors.modularHabitat.habitatSection;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import utils.Copyable;
import dimensions.mass.Mass;
import basicParts.MassivePart;
import basicParts.cables.Cable;

//
public class SectionRigging implements MassivePart, Copyable<@NotNull SectionRigging> {
    private final @NotNull List<@Nullable Cable @NotNull []> cablePairs;
    private final @NotNull Mass additionalRiggingMass;

    //
    SectionRigging(@NotNull List<@Nullable Cable @NotNull []> cablePairs, @NotNull Mass additionalRiggingMass) {
        this.cablePairs = cablePairs;
        this.additionalRiggingMass = additionalRiggingMass;
    }

    //
    public @NotNull List<@Nullable Cable @NotNull []> getCablePairs() {
        return cablePairs;
    }

    //
    public @NotNull Mass getCarryableMass(double acceleration) {
        @NotNull Mass sum = new Mass(0);
        for (@Nullable Cable @NotNull [] pair : cablePairs) {
            for (@Nullable Cable cable : pair) {
                if (cable != null) {
                    sum.sum(cable.getCarryableMass(acceleration));
                }
            }
        }
        return sum;
    }

    //
    @Override
    public @NotNull Mass getMass() {
        @NotNull Mass sum = additionalRiggingMass.copy();
        for (@Nullable Cable @NotNull [] pair : cablePairs) {
            for (@Nullable Cable cable : pair) {
                if (cable != null) {
                    sum.sum(cable.getMass());
                }
            }
        }
        return sum;
    }

    //
    @Override
    public final @NotNull SectionRigging copy() {
        @NotNull List<@Nullable Cable @NotNull []>
                originalCablePairs = getCablePairs(),
                newCablePairs = new ArrayList<>();
        for (@Nullable Cable @NotNull [] originalPair : originalCablePairs) {
            @Nullable Cable @NotNull [] newPair = new Cable[originalPair.length];
            for (int i = 0; i < originalPair.length; i++) {
                @Nullable Cable originalCable = originalPair[i];
                if (originalCable == null) {
                    newPair[i] = null;
                } else {
                    newPair[i] = originalCable.copy();
                }
            }
            newCablePairs.add(newPair);
        }
        return new SectionRigging(newCablePairs, additionalRiggingMass.copy());
    }
}
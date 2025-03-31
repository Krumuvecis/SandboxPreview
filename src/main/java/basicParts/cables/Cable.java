package basicParts.cables;

import org.jetbrains.annotations.NotNull;

import utils.Copyable;
import rotation.RotationFormulae;
import dimensions.distance.Distance;
import dimensions.mass.Mass;
import basicParts.MassivePart;

//
public class Cable implements MassivePart, Copyable<@NotNull Cable> {
    private final @NotNull CableMaterial material;
    private final @NotNull Distance
            diameter,
            length;
    private final double maxStrength; //newtons
    private final @NotNull Mass mass;

    //
    public Cable(@NotNull CableMaterial material, @NotNull Distance diameter, @NotNull Distance length) {
        this.material = material;
        this.diameter = diameter;
        double crossSection = calculateCrossSection(diameter);
        maxStrength = calculateMaxStrength(material.getMaxStress(), crossSection);
        this.length = length;
        mass = calculateMass(material.getDensity(), crossSection, length);
    }

    private static double calculateCrossSection(@NotNull Distance diameter) { //in m2
        return RotationFormulae.getCircleArea(diameter.getMultiplied(0.5));
    }

    private static double calculateMaxStrength(double maxStress, double crossSection) { //SI
        return maxStress * crossSection;
    }

    private static @NotNull Mass calculateMass(double density, double crossSection, @NotNull Distance length) {
        return new Mass(density * crossSection * length.getInBase());
    }

    //
    public final @NotNull CableMaterial getMaterial() {
        return material;
    }

    //
    public final @NotNull Distance getDiameter() {
        return diameter;
    }

    //
    public final @NotNull Distance getLength() {
        return length;
    }

    //self mass
    @Override
    public final @NotNull Mass getMass() {
        return mass;
    }

    //carryable mass, can be negative
    public final @NotNull Mass getCarryableMass(double acceleration) {
        return new Mass(maxStrength / acceleration - mass.getInBase());
    }

    //
    @Override
    public @NotNull Cable copy() {
        return new Cable(getMaterial(), getDiameter().copy(), getLength().copy());
    }
}
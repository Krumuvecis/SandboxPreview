package basicParts.beams;

import basicParts.MassivePart;
import dimensions.mass.Mass;
import org.jetbrains.annotations.NotNull;

//
public class StructuralBeam implements MassivePart {
    private final int length; //in number of habitat cells
    private final @NotNull Mass
            carryMass, //assumes 1g
            selfMass;

    //
    public StructuralBeam(int length, @NotNull Mass carryMass,
                          double selfMass_carryMass_ratio, double lengthwiseMassComponent) {
        this.length = length;
        this.carryMass = carryMass;
        selfMass = calculateSelfMass(selfMass_carryMass_ratio, lengthwiseMassComponent);
    }

    private @NotNull Mass calculateSelfMass(double selfMass_carryMass_ratio, double lengthwiseMassComponent) {
        return new Mass(carryMass.getSI() * selfMass_carryMass_ratio + length * lengthwiseMassComponent);
    }

    //in number of habitat cells
    public final int getLength() {
        return length;
    }

    //
    public final @NotNull Mass getCarryMass() {
        return carryMass;
    }

    //self mass
    @Override
    public final @NotNull Mass getMass() {
        return selfMass;
    }
}
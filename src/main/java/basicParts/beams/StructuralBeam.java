package basicParts.beams;

import basicParts.MassivePart;

//
public class StructuralBeam implements MassivePart {
    private final int length; //in number of habitat cells
    private final double
            carryMass, //assumes 1g
            selfMass;

    //
    public StructuralBeam(int length, double carryMass,
                          double selfMass_carryMass_ratio, double lengthwiseMassComponent) {
        this.length = length;
        this.carryMass = carryMass;
        selfMass = calculateSelfMass(selfMass_carryMass_ratio, lengthwiseMassComponent);
    }

    private double calculateSelfMass(double selfMass_carryMass_ratio, double lengthwiseMassComponent) {
        return carryMass * selfMass_carryMass_ratio + length * lengthwiseMassComponent;
    }

    //in number of habitat cells
    public final int getLength() {
        return length;
    }

    //
    public final double getCarryMass() {
        return carryMass;
    }

    //self mass
    @Override
    public final double getMass() {
        return selfMass;
    }
}
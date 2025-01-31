package basicParts.beams;

//
public class LightShortBeam extends StructuralBeam {
    private static final double
            SELF_MASS_CARRY_MASS_RATIO = 0.01,
            LENGTHWISE_MASS_COMPONENT = 100; // kg/cell

    //
    public LightShortBeam(int length, double carryMass) {
        super(length, carryMass, SELF_MASS_CARRY_MASS_RATIO, LENGTHWISE_MASS_COMPONENT);
    }
}
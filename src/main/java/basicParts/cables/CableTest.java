package basicParts.cables;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
public class CableTest {
    private static final @NotNull String INDENT = "  ";
    private static final @NotNull Distance DIAMETER = new Distance(0.01, DistanceUnit.M);
    private static final double ACCELERATION = 9.8;

    //
    public static void main(String[] args) {
        //unfeasible
        new CableTest(CableMaterial.STEEL_COMMON);

        //poor
        new CableTest(CableMaterial.STEEL_HIGH_STRENGTH);
        new CableTest(CableMaterial.ALUMINIUM_ALLOY);

        //ok
        new CableTest(CableMaterial.STEEL_MARAGING);

        //good
        new CableTest(CableMaterial.NYLON);
        new CableTest(CableMaterial.FIBERGLASS_S_CLASS);

        //excellent
        new CableTest(CableMaterial.ZYLON);
        new CableTest(CableMaterial.CARBON_FIBER);
        new CableTest(CableMaterial.KEVLAR);
    }

    private CableTest(@NotNull CableMaterial material) {
        printLine("Testing a " + material.getName() + " cable, " + DIAMETER.getValueAndShortUnit() + " in diameter:");
        testLength(material, new Distance(1, DistanceUnit.KM));
        testLength(material, new Distance(3, DistanceUnit.KM));
        testLength(material, new Distance(5, DistanceUnit.KM));
        testLength(material, new Distance(10, DistanceUnit.KM));
        printLine("");
    }

    private void testLength(@NotNull CableMaterial material, @NotNull Distance length) {
        @NotNull Cable cable = new Cable(material, DIAMETER, length);

        @NotNull Mass
                selfMass = cable.getMass(),
                carryableMass = cable.getCarryableMass(ACCELERATION);
        double massRatio = carryableMass.getInBase() / selfMass.getInBase();
        printLine(INDENT +
                "Length: " + length.getValueAndShortUnit(DistanceUnit.KM) + ", " +
                "self mass: " + selfMass.getValueAndShortUnit(MassUnit.T) + ", " +
                "carryable mass: " + carryableMass.getValueAndShortUnit(MassUnit.T) + ", " +
                "mass ratio: " + massRatio);
    }
}
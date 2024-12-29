package rotorTests;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import human.rotationalLimits.RotationalLimits;
import human.rotationalLimits.particularRotationalLimits.*;
import space.orbitalBodies.particularMajors.earth.Earth;

//
public class RotationalLimitsTest {
    /*
    private static final double
            DEFAULT_MAX_RELATIVE_CORIOLIS = 0.05, //arbitrary
            DEFAULT_MAX_CORIOLIS = new Earth(null, null).planetaryParameters.getSurfaceGravity() * DEFAULT_MAX_RELATIVE_CORIOLIS; // m/s^2
    */
    private static final double CORIOLIS_REFERENCE_TRANSLATIONAL_VELOCITY = 100 / 9.58; //Usain Bolt's 100m sprint world record, in m/s
    private static final @NotNull String INDENT = "  ";
    private final @NotNull RotationalLimits rotationalLimits;

    //
    public static void main(String[] args) {
        double earthAccelerationGradient = new Earth(null, null).planetaryParameters.getGravityGradient();
        printLine("Earth's gravity gradient: " +  earthAccelerationGradient + " 1/m");
        printLine("");

        new RotationalLimitsTest(new IdealRotationalLimits(), "ideal");
        new RotationalLimitsTest(new LuxuryRotationalLimits(), "luxury");
        new RotationalLimitsTest(new CivilianRotationalLimits(), "civilian");
        new RotationalLimitsTest(new MilitaryRotationalLimits(), "military");
    }

    private RotationalLimitsTest(@NotNull RotationalLimits rotationalLimits, @NotNull String limitTypeName) {
        this.rotationalLimits = rotationalLimits;
        printLine("Testing " + limitTypeName + " rotational limits.");

        printGradientLimitInfo();
        printCoriolisLimitInfo();
        printTotalLimitInfo();
        printFinalAnalysis();
        printLine("");
    }

    private void printGradientLimitInfo() {
        printLimitInfo("Gradient imposed", rotationalLimits.getMaxAngularVelocity_fromGradient(), rotationalLimits.getMinRadius_fromGradient());
    }

    private void printCoriolisLimitInfo() {
        printLimitInfo("Coriolis imposed", rotationalLimits.getMaxAngularVelocity_fromCoriolis(), rotationalLimits.getMinRadius_fromCoriolis());
    }

    private void printTotalLimitInfo() {
        printLimitInfo("Total", rotationalLimits.getAngularVelocity(), rotationalLimits.getRadius());
    }

    private void printLimitInfo(@NotNull String limitTitle, double angularVelocity, @NotNull Distance radius) {
        @NotNull DistanceUnit radiusUnit = DistanceUnit.KM;
        printLine(INDENT + limitTitle + " limits:");
        @NotNull String
                angularVelocityString = angularVelocity + " rad/s",
                radiusString = radius.getValueAndShortUnit(radiusUnit);
        printLine(INDENT.repeat(2) + "Maximum angular velocity: " + angularVelocityString);
        printLine(INDENT.repeat(2) + "Minimum radius: " + radiusString);
    }

    private void printFinalAnalysis() {
        @NotNull String
                gradientString = rotationalLimits.getAccelerationGradient() + " 1/m",
                relativeGradientString = (rotationalLimits.getRelativeAccelerationGradient() * 100) + " % above normal",
                coriolisString = rotationalLimits.getCoriolisAcceleration(CORIOLIS_REFERENCE_TRANSLATIONAL_VELOCITY) + " m/s^2";
        printLine(INDENT.repeat(2) + "Acceleration gradient: " + gradientString + ", " + relativeGradientString);
        printLine(INDENT.repeat(2) + "Coriolis acceleration: " + coriolisString);
    }
}
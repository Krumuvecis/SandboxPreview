package human.rotationalLimits;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;
import space.orbitalBodies.Planetoid;
import space.orbitalBodies.particularMajors.earth.Earth;

//
public class RotationalLimits implements RotationalLimitInterface {
    private static final @NotNull Planetoid DEFAULT_PLANETOID = new Earth(null, null);
    private final @NotNull Planetoid referencePlanetoid;
    private final @NotNull GradientLimits gradientLimits;
    private final @NotNull CoriolisLimits coriolisLimits;
    private final double targetAcceleration;
    private final double angularVelocity;
    private final @NotNull Distance radius;

    //custom limits
    public RotationalLimits(@NotNull Planetoid referencePlanetoid,
                            double accelerationGradient, @NotNull CoriolisLimits coriolisLimits) {
        this.referencePlanetoid = referencePlanetoid;
        this.gradientLimits = new GradientLimits(accelerationGradient);
        this.coriolisLimits = coriolisLimits;
        targetAcceleration = getTargetAcceleration();
        angularVelocity = calculateAngularVelocity();
        radius = calculateRadius(targetAcceleration, angularVelocity);
    }

    //default planetoid
    public RotationalLimits(double accelerationGradientDeviation, @NotNull CoriolisLimits coriolisLimits) {
        this(DEFAULT_PLANETOID, accelerationGradientDeviation, coriolisLimits);
    }

    private double getTargetAcceleration() {
        return referencePlanetoid.planetaryParameters.getSurfaceGravity();
    }

    // rad/s, for testing purposes
    public double getMaxAngularVelocity_fromGradient() {
        return gradientLimits.getAngularVelocity();
    }

    // rad/s, for testing purposes
    public double getMaxAngularVelocity_fromCoriolis() {
        return coriolisLimits.getAngularVelocity();
    }

    private double calculateAngularVelocity() {
        return Math.min(getMaxAngularVelocity_fromGradient(), getMaxAngularVelocity_fromCoriolis());
    }

    // rad/s
    @Override
    public double getAngularVelocity() {
        return angularVelocity;
    }

    private static @NotNull Distance calculateRadius(double acceleration, double angularVelocity) {
        //a_ct = v^2/R
        //T = 2 * pi * R / v
        //omega = 2 * pi / T = v / R
        //v = omega * R
        //a_ct = omega^2 * R
        //R = a_ct / omega^2
        return new Distance(acceleration / Math.pow(angularVelocity, 2));
    }

    //
    public @NotNull Distance getRadius() {
        return radius;
    }

    //for testing purposes
    public @NotNull Distance getMinRadius_fromGradient() {
        return calculateRadius(targetAcceleration, getMaxAngularVelocity_fromGradient());
    }

    //for testing purposes
    public @NotNull Distance getMinRadius_fromCoriolis() {
        return calculateRadius(targetAcceleration, getMaxAngularVelocity_fromCoriolis());
    }

    // 1/m
    public double getAccelerationGradient() {
        // r_a / h = ( a_ct(R) - a_ct(R-h) ) / a_ct(R) / h = (omega^2 * R - omega^2 * (R - h)) / omega^2 / R / h
        // r_a / h = (R - R + h) / R / h
        // r_a / h = 1 / R
        return 1 / radius.getSI();
    }

    //
    public double getRelativeAccelerationGradient() {
        double
                normalGradient = DEFAULT_PLANETOID.planetaryParameters.getGravityGradient(),
                gradient = getAccelerationGradient();
        return (gradient - normalGradient) / normalGradient;
    }

    //
    public double getCoriolisAcceleration(double relativeTranslationalVelocity) {
        //a_coriolis = 2 * omega * v_relative
        return 2 * getAngularVelocity() / relativeTranslationalVelocity;
    }
}
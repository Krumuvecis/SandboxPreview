package human.rotationalLimits;

//
public class GradientLimits implements RotationalLimitInterface {
    private final double angularVelocity;

    //acceleration gradient in 1/m
    public GradientLimits(double accelerationGradient) {
        angularVelocity = calculateAngularVelocity(accelerationGradient);
    }

    private static double calculateAngularVelocity(double accelerationGradient) {
        // delta_a / h = ( a_ct(R) - a_ct(R-h) ) / h = (omega^2 * R - omega^2 * (R - h)) / h
        // delta_a / h = omega^2 / h * (R - R + h) = omega^2
        // omega = sqrt(delta_a)
        return Math.sqrt(accelerationGradient);
    }

    //
    @Override
    public double getAngularVelocity() {
        return angularVelocity;
    }
}
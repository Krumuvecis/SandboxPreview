package mathUtils;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;
import dimensions.time.TimeUnit;
import dimensions.time.Time;

//
public class RotationFormulae {
    public static final double FULL_ANGLE = 2 * Math.PI;
    private static final double RPM_TO_ANGULAR_VELOCITY; //conversion from RPM to rad/s
    static {
        // 2 * pi * rpm / min_to_s = rad/s
        // conversion = 2 * pi / min_to_s
        double min_to_s = new Time(1, TimeUnit.MIN).get(TimeUnit.S);
        RPM_TO_ANGULAR_VELOCITY = FULL_ANGLE / min_to_s;
    }

    //preserves units
    public static @NotNull Distance getArcLength(@NotNull Distance radius, double angle) {
        return new Distance(angle * radius.getValue(), radius.getUnit());
    }

    //preserves units
    public static @NotNull Distance getCircleCircumference(@NotNull Distance radius) {
        return getArcLength(radius, FULL_ANGLE);
    }

    //
    public static double getArcAngle(Distance radius, Distance arcLength) {
        return FULL_ANGLE * getCircleCircumference(radius).getSI() / arcLength.getSI();
    }

    //angularVelocity in rad/s
    public static @NotNull Time getPeriod_fromAngularVelocity(double angularVelocity) {
        return new Time(FULL_ANGLE / angularVelocity);
    }

    //in rad/s
    public static double getAngularVelocity_fromPeriod(@NotNull Time period) {
        return FULL_ANGLE / period.getSI();
    }

    //in rad/s
    public static double getAngularVelocity_fromRPM(double rpm) {
        return rpm * RPM_TO_ANGULAR_VELOCITY;
    }

    //angularVelocity in rad/s
    public static double getRPM_fromAngularVelocity(double angularVelocity) {
        return angularVelocity / RPM_TO_ANGULAR_VELOCITY;
    }
}
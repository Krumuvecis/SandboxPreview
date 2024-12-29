package human.rotationalLimits.particularRotationalLimits;

import org.jetbrains.annotations.NotNull;

import dimensions.time.Time;
import mathUtils.RotationFormulae;
import space.orbitalBodies.particularMajors.earth.Earth;
import human.rotationalLimits.CoriolisLimits;
import human.rotationalLimits.RotationalLimits;

//just like on Earth
public final class IdealRotationalLimits extends RotationalLimits {
    private static final double
            ACCELERATION_GRADIENT = 0.001, // 1/m
            EARTH_ANGULAR_VELOCITY;
    static {
        @NotNull Time earthRotationPeriod = new Earth(null, null).planetaryParameters.getRotationPeriod();
        EARTH_ANGULAR_VELOCITY = RotationFormulae.getAngularVelocity_fromPeriod(earthRotationPeriod);
    }

    //
    public IdealRotationalLimits() {
        super(ACCELERATION_GRADIENT, new CoriolisLimits(EARTH_ANGULAR_VELOCITY));
    }
}
package human.rotationalLimits.particularRotationalLimits;

import human.rotationalLimits.CoriolisLimits;
import human.rotationalLimits.RotationalLimits;

//
public final class CivilianRotationalLimits extends RotationalLimits {
    private static final double
            ACCELERATION_GRADIENT = 0.005, // 1/m
            MAX_RPM = 0.5;

    //
    public CivilianRotationalLimits() {
        super(ACCELERATION_GRADIENT, new CoriolisLimits.CoriolisLimits_fromRPM(MAX_RPM));
    }
}
package human.rotationalLimits.particularRotationalLimits;

import human.rotationalLimits.CoriolisLimits;
import human.rotationalLimits.RotationalLimits;

//
public final class MilitaryRotationalLimits extends RotationalLimits {
    private static final double
            ACCELERATION_GRADIENT = 0.1, // 1/m
            MAX_RPM = 1;

    //
    public MilitaryRotationalLimits() {
        super(ACCELERATION_GRADIENT, new CoriolisLimits.CoriolisLimits_fromRPM(MAX_RPM));
    }
}
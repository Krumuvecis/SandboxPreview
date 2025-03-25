package humanRequirements.rotationalLimits.particularRotationalLimits;

import humanRequirements.rotationalLimits.CoriolisLimits;
import humanRequirements.rotationalLimits.RotationalLimits;

//
public final class LuxuryRotationalLimits extends RotationalLimits {
    private static final double
            ACCELERATION_GRADIENT = 0.002, // 1/m
            MAX_RPM = 0.3;

    //
    public LuxuryRotationalLimits() {
        super(ACCELERATION_GRADIENT, new CoriolisLimits.CoriolisLimits_fromRPM(MAX_RPM));
    }
}
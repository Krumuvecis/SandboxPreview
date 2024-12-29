package human.rotationalLimits;

import mathUtils.RotationFormulae;

//
public class CoriolisLimits implements RotationalLimitInterface {
    private final double angularVelocity;

    //from angular velocity in rad/s
    public CoriolisLimits(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    //
    @Override
    public final double getAngularVelocity() {
        return angularVelocity;
    }

    //
    public static final class CoriolisLimits_fromRPM extends CoriolisLimits {
        //
        public CoriolisLimits_fromRPM(double rpm) {
            super(RotationFormulae.getAngularVelocity_fromRPM(rpm));
        }
    }

    //
    public static final class CoriolisLimits_fromAcceleration extends CoriolisLimits {
        //
        public CoriolisLimits_fromAcceleration(double coriolisAcceleration, double referenceTranslationalVelocity) {
            super(calculateAngularVelocity(coriolisAcceleration, referenceTranslationalVelocity));
        }

        private static double calculateAngularVelocity(double coriolisAcceleration, double relativeTranslationalVelocity) {
            //coriolis acceleration at 90 degrees relative motion = 2 * omega * v_relative
            //max omega = coriolis / 2 / v_relative
            return coriolisAcceleration / 2 / relativeTranslationalVelocity;
        }
    }
}
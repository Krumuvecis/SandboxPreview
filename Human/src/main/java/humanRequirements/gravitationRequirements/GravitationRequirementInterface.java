package humanRequirements.gravitationRequirements;

import dimensions.time.Time;
import org.jetbrains.annotations.NotNull;

//
public interface GravitationRequirementInterface {
    //
    double getAcceleration();

    //
    default @NotNull Time getRecoveryTime(double missionAcceleration, @NotNull Time missionTime,
                                          double recoveryAcceleration) {
        //optimalA = (recoveryT * recoveryA + missionT * missionA) / (missionT + recoveryT)
        //recoveryT = missionT * (optimalA - missionA) / (recoveryA - optimalA)

        double optimalAcceleration = getAcceleration();
        return missionTime.getMultiplied(
                (optimalAcceleration - missionAcceleration) / (recoveryAcceleration - optimalAcceleration));
    }

    default @NotNull Time getMissionTime(double missionAcceleration,
                                         double recoveryAcceleration, @NotNull Time recoveryTime) {
        //optimalA = (recoveryT * recoveryA + missionT * missionA) / (missionT + recoveryT)
        //missionT = recoveryT * (recoveryA - optimalA) / (optimalA - missionA)

        double optimalAcceleration = getAcceleration();
        return recoveryTime.getMultiplied(
                (recoveryAcceleration - optimalAcceleration) / (optimalAcceleration - missionAcceleration));
    }
}
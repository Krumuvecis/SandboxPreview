package humanRequirements.gravitationRequirements;

import dimensions.mass.Mass;
import org.jetbrains.annotations.NotNull;

//
@SuppressWarnings("ClassCanBeRecord")
public class GravitationRequirements implements GravitationRequirementInterface {
    private final double acceleration;

    //
    public GravitationRequirements(double acceleration) {
        this.acceleration = acceleration;
    }

    //
    @Override
    public double getAcceleration() {
        return acceleration;
    }
}
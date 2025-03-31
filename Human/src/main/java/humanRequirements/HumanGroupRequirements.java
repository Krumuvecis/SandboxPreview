package humanRequirements;

import org.jetbrains.annotations.NotNull;

import humanRequirements.rotationalLimits.RotationalLimits;
import humanRequirements.aerationRequirements.AerationRequirements;
import humanRequirements.hydrationRequirements.HydrationRequirements;
import humanRequirements.accommodationRequirements.AccommodationRequirements;
import humanRequirements.sanitationRequirements.SanitationRequirements;
import humanGroup.HumanGroup;

//TODO: finish this
public class HumanGroupRequirements implements HumanRequirementInterface {
    private final @NotNull HumanGroup humanGroup;

    //
    public HumanGroupRequirements(@NotNull HumanGroup humanGroup) {
        this.humanGroup = humanGroup;
    }

    //
    @Override
    public @NotNull RotationalLimits getRotationalLimits() {
        return null;
    }

    //
    @Override
    public @NotNull AerationRequirements getAerationRequirements() {
        return null;
    }

    //
    @Override
    public @NotNull HydrationRequirements getHydrationRequirements() {
        return null;
    }

    //
    /*@Override
    public @NotNull NutritionRequirements getNutritionRequirements() {
        return null;
    }*/

    //
    @Override
    public @NotNull AccommodationRequirements getAccommodationRequirements() {
        return null;
    }

    //
    @Override
    public @NotNull SanitationRequirements getSanitationRequirements() {
        return null;
    }
}
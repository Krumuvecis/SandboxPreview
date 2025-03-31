package humanRequirements;

import org.jetbrains.annotations.NotNull;

import humanRequirements.rotationalLimits.RotationalLimits;
import humanRequirements.aerationRequirements.AerationRequirements;
import humanRequirements.hydrationRequirements.HydrationRequirements;
import humanRequirements.accommodationRequirements.AccommodationRequirements;
import humanRequirements.sanitationRequirements.SanitationRequirements;

//
public interface HumanRequirementInterface {
    //
    @NotNull RotationalLimits getRotationalLimits();

    //
    @NotNull AerationRequirements getAerationRequirements();

    //
    @NotNull HydrationRequirements getHydrationRequirements();

    //
    //@NotNull NutritionRequirements getNutritionRequirements();

    //
    @NotNull AccommodationRequirements getAccommodationRequirements();

    //
    @NotNull SanitationRequirements getSanitationRequirements();
}
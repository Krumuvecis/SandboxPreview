package humanRequirements.accommodationRequirements;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
public interface AccommodationRequirementInterface {
    //
    double getPrivateArea();

    //
    double getCommonArea();

    //
    @NotNull Mass getLuggage();
}
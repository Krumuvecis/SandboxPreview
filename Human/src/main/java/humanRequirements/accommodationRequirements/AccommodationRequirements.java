package humanRequirements.accommodationRequirements;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
@SuppressWarnings("ClassCanBeRecord")
public class AccommodationRequirements implements AccommodationRequirementInterface {
    private final double
            privateArea,
            commonArea;
    private final @NotNull Mass luggage;

    //
    public AccommodationRequirements(double privateArea, double commonArea, @NotNull Mass luggage) {
        this.privateArea = privateArea;
        this.commonArea = commonArea;
        this.luggage = luggage;
    }

    //
    @Override
    public double getPrivateArea() {
        return privateArea;
    }

    //
    @Override
    public double getCommonArea() {
        return commonArea;
    }

    //
    @Override
    public @NotNull Mass getLuggage() {
        return luggage;
    }
}
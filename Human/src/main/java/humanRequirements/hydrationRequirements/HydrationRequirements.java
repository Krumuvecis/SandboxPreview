package humanRequirements.hydrationRequirements;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
@SuppressWarnings("ClassCanBeRecord")
public class HydrationRequirements implements HydrationRequirementInterface {
    private final @NotNull Mass dailyWaterConsumption;

    //
    public HydrationRequirements(@NotNull Mass dailyWaterConsumption) {
        this.dailyWaterConsumption = dailyWaterConsumption;
    }

    //
    @Override
    public @NotNull Mass getDailyWaterConsumption() {
        return dailyWaterConsumption;
    }
}
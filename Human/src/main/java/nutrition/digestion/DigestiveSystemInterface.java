package nutrition.digestion;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
public interface DigestiveSystemInterface extends NutrientSufficiencyInterface {
    //
    double getDailyCalories();

    //
    @NotNull Diet getDiet();

    //
    void updateDiet();

    //
    @NotNull Mass getMaximumDailyFoodThroughput();

    //
    void setDailyCalories(double dailyCalories);

    //
    void setDiet(@NotNull Diet diet);

    //
    void setMaximumDailyFoodThroughput(@NotNull Mass maximumDailyFoodThroughput);
}
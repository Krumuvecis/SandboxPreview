package nutrition.digestion;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;

//
public class HumanDigestiveSystem implements DigestiveSystemInterface {
    private double dailyCalories;
    private final @NotNull ParticularDiet particularDiet;
    private @NotNull Mass maximumDailyFoodThroughput;

    //
    public HumanDigestiveSystem(@NotNull MassiveHumanInterface human, double dailyCalories, @NotNull Diet diet,
                                @NotNull Mass maximumDailyFoodThroughput) {
        this.dailyCalories = dailyCalories;
        particularDiet = new ParticularDiet(human, this, diet);
        this.maximumDailyFoodThroughput = maximumDailyFoodThroughput;
    }

    //
    @Override
    public final double getDailyCalories() {
        return dailyCalories;
    }

    //
    @Override
    public final @NotNull Diet getDiet() {
        return particularDiet;
    }

    //
    @Override
    public final void updateDiet() {
        particularDiet.update();
    }

    //
    @Override
    public final @NotNull Mass getMaximumDailyFoodThroughput() {
        return maximumDailyFoodThroughput;
    }

    //
    @Override
    public final @NotNull NutritionalValue getDailyNutrients() {
        return particularDiet.getDailyNutrients();
    }

    //
    @Override
    public final void setDailyCalories(double dailyCalories) {
        this.dailyCalories = dailyCalories;
        updateDiet();
    }

    //
    @Override
    public final void setDiet(@NotNull Diet diet) {
        particularDiet.set(diet);
    }

    //
    @Override
    public final void setMaximumDailyFoodThroughput(@NotNull Mass maximumDailyFoodThroughput) {
        this.maximumDailyFoodThroughput = maximumDailyFoodThroughput;
    }
}
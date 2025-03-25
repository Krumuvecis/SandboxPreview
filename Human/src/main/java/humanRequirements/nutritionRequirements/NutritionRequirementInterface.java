package humanRequirements.nutritionRequirements;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import food.NutrientEnum;
import food.NutritionalValue;

//
public interface NutritionRequirementInterface extends DietInterface{
    //
    @NotNull Mass getBodyWeight();

    //
    double getDailyCalories();

    //
    @NotNull Diet getDiet();

    //
    @NotNull NutritionalValue getDailyNutrients();

    //
    void setBodyWeight(@NotNull Mass bodyWeight);

    //
    void setDailyCalories(double dailyCalories);

    //
    default double compare(@NotNull NutritionalValue nutritionalValue, @NotNull NutrientEnum nutrient) {
        return nutritionalValue.get(nutrient).getInBase() / getDailyNutrients().get(nutrient).getInBase();
    }
}
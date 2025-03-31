package nutrition.digestion;

import org.jetbrains.annotations.NotNull;

import nutrition.nutrients.NutritionalValue;

//
public interface DailyNutritionalValueInterface {
    //
    @NotNull NutritionalValue getDailyNutrients();
}
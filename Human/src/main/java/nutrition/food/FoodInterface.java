package nutrition.food;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;

//
public interface FoodInterface {
    //
    @NotNull String getName();

    //per 1kg
    @NotNull NutritionalValue getBaseNutritionalValue();

    //base nutrients multiplied by some mass
    default @NotNull NutritionalValue getNutritionalValue(@NotNull Mass mass) {
        return getBaseNutritionalValue().getMultiplied(mass.getInBase());
    }
}
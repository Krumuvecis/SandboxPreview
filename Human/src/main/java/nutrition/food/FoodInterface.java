package nutrition.food;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;

//
public interface FoodInterface {
    //
    @NotNull String getName();

    //for discrete foods; null - divisible
    @Nullable Mass getUnitMass();

    //base, per 1kg
    @NotNull NutritionalValue getNutritionalValue();

    //scaled per mass
    default @NotNull NutritionalValue getNutritionalValue(@NotNull Mass mass) {
        return getNutritionalValue().getMultiplied(mass.getInBase());
    }
}
package nutrition.food.foodTypes;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;
import nutrition.food.FoodInterface;

//
public interface DiscreteFoodInterface extends FoodInterface {
    //
    @NotNull Mass getUnitMass();

    //
    default @NotNull NutritionalValue getUnitNutritionalValue() {
        return getBaseNutritionalValue().getMultiplied(getUnitMass().getInBase());
    }
}
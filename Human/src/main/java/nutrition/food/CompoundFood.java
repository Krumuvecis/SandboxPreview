package nutrition.food;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;

import java.util.Map;

//
public class CompoundFood implements FoodInterface {
    private final @NotNull String name;
    private final @Nullable Mass unitMass; // mass per unit for discrete foods
    private final @NotNull NutritionalValue nutritionalValue; //base, per 1kg

    //
    public CompoundFood(@NotNull String name,
                        @Nullable Map<@NotNull FoodInterface, @NotNull Integer> discreteIngredients,
                        @Nullable Map<@NotNull FoodInterface, @NotNull Mass> divisibleIngredients) {
        this.name = name;
        boolean
                noDivisibleIngredients = divisibleIngredients == null || divisibleIngredients.isEmpty(),
                noDiscreteIngredients = discreteIngredients == null || discreteIngredients.isEmpty();
        if (noDiscreteIngredients && noDivisibleIngredients) {
            //
        } else {


            if (noDivisibleIngredients) {
                //discrete ingredients only

                //calculate total mass and set unit mass
                //calculate and set base nutrients

            } else if (noDiscreteIngredients) {
                //divisible ingredients only

                unitMass = null; // produces a divisible food
                //calculate and set base nutrients

            } else {
                //both discrete and divisible ingredients

                //calculate total mass and set unit mass
                //calculate and set base nutrients

            }
        }



        this.unitMass = unitMass;
        this.nutritionalValue = nutritionalValue;
    }

    //
    @Override
    public @NotNull String getName() {
        return name;
    }

    //for discrete foods; null - divisible
    @Override
    public @Nullable Mass getUnitMass() {
        return unitMass;
    }

    //base, per 1kg
    @Override
    public @NotNull NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }
}
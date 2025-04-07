package nutrition.food.foodTypes;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;

//
public class CompoundDiscreteFood extends AbstractFood implements DiscreteFoodInterface, CompoundFoodInterface {
    private final @Nullable Map<? extends @NotNull LooseFoodInterface, @NotNull Mass> looseIngredients; //<food, mass>
    private final @Nullable Map<? extends @NotNull DiscreteFoodInterface, @NotNull Integer> discreteIngredients; //<food, count>
    private @NotNull Mass unitMass; //cached
    private @NotNull NutritionalValue baseNutritionalValue; //cached

    //
    public CompoundDiscreteFood(@NotNull String name,
                                @Nullable Map<? extends @NotNull LooseFoodInterface, @NotNull Mass> looseIngredients,
                                @Nullable Map<? extends @NotNull DiscreteFoodInterface, @NotNull Integer> discreteIngredients) {
        super(name);
        this.looseIngredients = looseIngredients;
        this.discreteIngredients = discreteIngredients;
        @NotNull BaseNutritionalValueAndUnitMassCalculator
                calculator = new BaseNutritionalValueAndUnitMassCalculator(getLooseIngredients(), getDiscreteIngredients());
        unitMass = calculator.getUnitMass();
        baseNutritionalValue = calculator.getBaseNutritionalValue();
    }

    //recalculates base nutritional value and unit mass
    @Override
    public final void update() {
        @NotNull BaseNutritionalValueAndUnitMassCalculator
                calculator = new BaseNutritionalValueAndUnitMassCalculator(getLooseIngredients(), getDiscreteIngredients());
        unitMass = calculator.getUnitMass();
        baseNutritionalValue = calculator.getBaseNutritionalValue();
    }

    //
    @Override
    public final @NotNull Mass getUnitMass() {
        return unitMass;
    }

    //
    @Override
    public final @NotNull NutritionalValue getBaseNutritionalValue() {
        return baseNutritionalValue;
    }

    //
    public final @Nullable Map<? extends @NotNull LooseFoodInterface, @NotNull Mass> getLooseIngredients() {
        return looseIngredients;
    }

    //
    public final @Nullable Map<? extends @NotNull DiscreteFoodInterface, @NotNull Integer> getDiscreteIngredients() {
        return discreteIngredients;
    }

    //utility/helper class
    private static final class BaseNutritionalValueAndUnitMassCalculator {
        private final @NotNull Mass unitMass; //cached
        private final @NotNull NutritionalValue baseNutritionalValue; //cached

        //
        BaseNutritionalValueAndUnitMassCalculator(
                @Nullable Map<? extends @NotNull LooseFoodInterface, @NotNull Mass> looseIngredients,
                @Nullable Map<? extends @NotNull DiscreteFoodInterface, @NotNull Integer> discreteIngredients) {
            unitMass = new Mass(0);
            baseNutritionalValue = new NutritionalValue();
            if (looseIngredients != null) {
                for (@NotNull LooseFoodInterface ingredient : looseIngredients.keySet()) {
                    @NotNull Mass ingredientMass = looseIngredients.get(ingredient);
                    unitMass.sum(ingredientMass);
                    @NotNull NutritionalValue ingredientNutrients = ingredient.getNutritionalValue(ingredientMass);
                    baseNutritionalValue.sum(ingredientNutrients);
                }
            }
            if (discreteIngredients != null) {
                for (@NotNull DiscreteFoodInterface ingredient : discreteIngredients.keySet()) {
                    int count = discreteIngredients.get(ingredient);
                    @NotNull Mass ingredientMass = ingredient.getUnitMass().getMultiplied(count);
                    unitMass.sum(ingredientMass);
                    @NotNull NutritionalValue ingredientNutrients = ingredient.getUnitNutritionalValue().getMultiplied(count);
                    baseNutritionalValue.sum(ingredientNutrients);
                }
            }
            baseNutritionalValue.multiply(1 / unitMass.getInBase()); //normalize after summation
        }

        //
        @NotNull Mass getUnitMass() {
            return unitMass;
        }

        //
        @NotNull NutritionalValue getBaseNutritionalValue() {
            return baseNutritionalValue;
        }
    }
}
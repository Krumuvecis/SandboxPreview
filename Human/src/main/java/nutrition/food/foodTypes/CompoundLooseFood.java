package nutrition.food.foodTypes;

import java.util.Map;

import org.jetbrains.annotations.NotNull;

import nutrition.nutrients.NutritionalValue;

//
public class CompoundLooseFood extends AbstractFood implements LooseFoodInterface, CompoundFoodInterface {
    private final @NotNull Map<? extends @NotNull LooseFoodInterface, @NotNull Integer> ingredientWeights;
    private @NotNull NutritionalValue baseNutritionalValue; //cached

    //
    public CompoundLooseFood(@NotNull String name,
                             @NotNull Map<? extends @NotNull LooseFoodInterface, @NotNull Integer> ingredientWeights) {
        super(name);
        this.ingredientWeights = ingredientWeights;
        baseNutritionalValue = calculateBaseNutritionalValue();
    }

    private @NotNull NutritionalValue calculateBaseNutritionalValue() {
        int weightSum = 0;
        @NotNull NutritionalValue nutritionalValueSum = new NutritionalValue();
        for (@NotNull LooseFoodInterface ingredient : ingredientWeights.keySet()) {
            int weight = ingredientWeights.get(ingredient);
            weightSum += weight;
            nutritionalValueSum.sum(ingredient.getBaseNutritionalValue().getMultiplied(weight));
        }
        nutritionalValueSum.multiply(1.0 / weightSum);
        return nutritionalValueSum;
    }

    //recalculates base nutritional value
    @Override
    public final void update() {
        baseNutritionalValue = calculateBaseNutritionalValue();
    }

    //
    @Override
    public final @NotNull NutritionalValue getBaseNutritionalValue() {
        return baseNutritionalValue;
    }

    //
    public final @NotNull Map<? extends @NotNull LooseFoodInterface, @NotNull Integer> getIngredientWeights() {
        return ingredientWeights;
    }
}
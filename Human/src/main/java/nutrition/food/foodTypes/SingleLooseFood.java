package nutrition.food.foodTypes;

import org.jetbrains.annotations.NotNull;

import nutrition.nutrients.NutritionalValue;

//
public class SingleLooseFood extends AbstractFood implements LooseFoodInterface, SingleFoodInterface {
    private final @NotNull NutritionalValue baseNutritionalValue; //per 1kg

    //
    public SingleLooseFood(@NotNull String name, @NotNull NutritionalValue baseNutritionalValue) {
        super(name);
        this.baseNutritionalValue = baseNutritionalValue;
    }

    //
    @Override
    public final @NotNull NutritionalValue getBaseNutritionalValue() {
        return baseNutritionalValue;
    }
}
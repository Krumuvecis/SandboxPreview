package nutrition.food.foodTypes;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;

//
public class SingleDiscreteFood extends AbstractFood implements DiscreteFoodInterface, SingleFoodInterface {
    private final @NotNull Mass unitMass;
    private final @NotNull NutritionalValue baseNutritionalValue; //per 1kg

    //
    public SingleDiscreteFood(@NotNull String name, @NotNull Mass unitMass,
                              @NotNull NutritionalValue baseNutritionalValue) {
        super(name);
        this.unitMass = unitMass;
        this.baseNutritionalValue = baseNutritionalValue;
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
}
package nutrition.food;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;

//
public class SimpleFood implements FoodInterface {
    private final @NotNull String name;
    private final @Nullable Mass unitMass; // mass per unit for discrete foods
    private final @NotNull NutritionalValue nutritionalValue; //base, per 1kg

    //for discrete foods
    public SimpleFood(@NotNull String name, @Nullable Mass unitMass, @NotNull NutritionalValue nutritionalValue) {
        this.name = name;
        this.unitMass = unitMass;
        this.nutritionalValue = nutritionalValue;
    }

    //for divisible foods
    public SimpleFood(@NotNull String name, @NotNull NutritionalValue nutritionalValue) {
        this(name, null, nutritionalValue);
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
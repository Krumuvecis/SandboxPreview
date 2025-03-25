package food;

import java.util.Map;
import java.util.Objects;
import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
public class NutritionalValue {
    private final @NotNull Map<@NotNull NutrientEnum, @NotNull Mass> nutrients;

    //
    public NutritionalValue(@NotNull Map<@NotNull NutrientEnum, @NotNull Mass> nutrients) {
        this.nutrients = nutrients;
    }

    //
    public NutritionalValue(@NotNull Mass proteins, @NotNull Mass fats, @NotNull Mass carbs,
                            @Nullable Map<@NotNull NutrientEnum, @NotNull Mass> additionalNutrients) {
        this(new EnumMap<>(NutrientEnum.class) {{
            put(NutrientEnum.PROTEIN, proteins);
            put(NutrientEnum.FAT, fats);
            put(NutrientEnum.CARB, carbs);

            if (additionalNutrients != null) {
                for (@NotNull NutrientEnum additionalNutrient : additionalNutrients.keySet()) {
                    putIfAbsent(additionalNutrient, additionalNutrients.get(additionalNutrient));
                }
            }
        }});
    }

    //
    public NutritionalValue(@NotNull Mass proteins, @NotNull Mass fats, @NotNull Mass carbs) {
        this(proteins, fats, carbs, null);
    }

    //for easier use; values in g/100g
    public NutritionalValue(double proteinGramsPer100g, double fatsGramsPer100g, double carbsGramsPer100g) {
        this(
                convert_gramsPer100g_to_kgPerKg(proteinGramsPer100g),
                convert_gramsPer100g_to_kgPerKg(fatsGramsPer100g),
                convert_gramsPer100g_to_kgPerKg(carbsGramsPer100g));
    }

    private static @NotNull Mass convert_gramsPer100g_to_kgPerKg(double gramsPer100g) {
        return new Mass(gramsPer100g / new Mass(100, MassUnit.G).getInBase(), MassUnit.G);
    }

    //
    public @NotNull Mass get(@NotNull NutrientEnum nutrient) {
        return Objects.requireNonNullElse(nutrients.get(nutrient), new Mass(0));
    }
}
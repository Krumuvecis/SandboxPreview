package nutrition.nutrients;

import java.util.Objects;
import java.util.Map;
import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
public class NutritionalValue implements NutritionalValueInterface<@NotNull NutritionalValue> {
    private final @NotNull Map<@NotNull MacroNutrient, @Nullable Mass> macroNutrients;
    private final @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> microNutrients;

    //
    public NutritionalValue(@NotNull Map<@NotNull MacroNutrient, @Nullable Mass> macroNutrients,
                            @Nullable Map<@NotNull MicroNutrient, @Nullable Mass> microNutrients) {
        this.macroNutrients = macroNutrients;
        this.microNutrients = Objects.requireNonNullElse(microNutrients, new EnumMap<>(MicroNutrient.class));
    }

    //macronutrients separately
    public NutritionalValue(@Nullable Mass proteins, @Nullable Mass fats, @Nullable Mass carbs,
                            @Nullable Map<@NotNull MicroNutrient, @Nullable Mass> microNutrients) {
        this(new EnumMap<>(MacroNutrient.class) {{
            put(MacroNutrient.PROTEIN, proteins);
            put(MacroNutrient.FAT, fats);
            put(MacroNutrient.CARB, carbs);
        }}, microNutrients);
    }

    //for easier use; values in g/100g
    public NutritionalValue(double proteinGramsPer100g, double fatsGramsPer100g, double carbsGramsPer100g,
                            @Nullable Map<@NotNull MicroNutrient, @Nullable Mass> microNutrientsPer100g) {
        this(
                convert_gramsPer100g_to_kgPerKg(proteinGramsPer100g),
                convert_gramsPer100g_to_kgPerKg(fatsGramsPer100g),
                convert_gramsPer100g_to_kgPerKg(carbsGramsPer100g),
                getMultipliedMicrosMap(microNutrientsPer100g, 10));
    }

    //
    @SuppressWarnings("SameParameterValue")
    private static @NotNull Map<@NotNull MicroNutrient, @NotNull Mass> getMultipliedMicrosMap(
            @Nullable Map<@NotNull MicroNutrient, @Nullable Mass> baseMasses, double multiplier) {
        return new EnumMap<>(MicroNutrient.class) {{
            if (baseMasses != null) {
                for (@NotNull MicroNutrient nutrient : baseMasses.keySet()) {
                    @Nullable Mass baseMass = baseMasses.get(nutrient);
                    if (baseMass != null) {
                        put(nutrient, baseMass.getMultiplied(multiplier));
                    }
                }
            }
        }};
    }

    //without micronutrients
    public NutritionalValue(@Nullable Mass proteins, @Nullable Mass fats, @Nullable Mass carbs) {
        this(proteins, fats, carbs, null);
    }

    //for easier use, without micronutrients; values in g/100g
    public NutritionalValue(double proteinGramsPer100g, double fatsGramsPer100g, double carbsGramsPer100g) {
        this(proteinGramsPer100g, fatsGramsPer100g, carbsGramsPer100g, null);
    }

    private static @NotNull Mass convert_gramsPer100g_to_kgPerKg(double gramsPer100g) {
        return new Mass(gramsPer100g / new Mass(100, MassUnit.G).getInBase(), MassUnit.G);
    }

    //
    @Override
    public @NotNull Map<@NotNull MacroNutrient, @Nullable Mass> getMacroNutrients() {
        return macroNutrients;
    }

    //
    @Override
    public @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> getMicroNutrients() {
        return microNutrients;
    }

    //
    @Override
    public final @NotNull NutritionalValue copy() {
        return new NutritionalValue(getMacroNutrients(), getMicroNutrients());
    }
}
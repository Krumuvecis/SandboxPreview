package nutrition.nutrients;

import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;

//
public interface NutritionalValueInterface<T extends @NotNull NutritionalValueInterface<T>>
        extends MacroNutrientContainer<T>, MicroNutrientContainer<T> {
    //
    default @NotNull Map<@NotNull NutrientInterface, @Nullable Mass> getAllNutrients() {
        return new HashMap<>() {{
            putAll(getMacroNutrients());
            putAll(getMicroNutrients());
        }};
    }

    //
    default @Nullable Mass getAnyNutrient(@NotNull NutrientInterface nutrient) {
        if (nutrient instanceof MacroNutrient macroNutrient) {
            return getMacroNutrient(macroNutrient);
        } else if (nutrient instanceof MicroNutrient microNutrient) {
            return getMicroNutrient(microNutrient);
        } else {
            throw new RuntimeException("Unrecognized nutrient type.");
            //return getAllNutrients().get(nutrient);
        }
    }

    //
    @Override
    default void sum(T addend) {
        sumNutrientsByType(addend.getMacroNutrients());
        sumNutrientsByType(addend.getMicroNutrients());
    }

    private <K extends @NotNull NutrientInterface> void sumNutrientsByType(@NotNull Map<K, @Nullable Mass> addendNutrients) {
        for (K nutrient : addendNutrients.keySet()) {
            @Nullable Mass addendMass = addendNutrients.get(nutrient);
            if (addendMass != null) {
                @Nullable Mass baseMass = getAnyNutrient(nutrient); //checks type
                if (baseMass != null) {
                    baseMass.sum(addendMass);
                } else {
                    //checks type again
                    if (nutrient instanceof MacroNutrient macroNutrient) {
                        getMacroNutrients().put(macroNutrient, addendMass.copy());
                    } else if (nutrient instanceof MicroNutrient microNutrient) {
                        getMicroNutrients().put(microNutrient, addendMass.copy());
                    } else {
                        throw new RuntimeException("Unrecognized nutrient type.");
                    }
                }
            }
        }
    }

    //
    @Override
    default void multiply(double multiplier) {
        multiplyNutrientsByType(getMacroNutrients(), multiplier);
        multiplyNutrientsByType(getMicroNutrients(), multiplier);
    }

    private static <K extends @NotNull NutrientInterface> void multiplyNutrientsByType(
            @NotNull Map<K, @Nullable Mass> nutrients, double multiplier) {
        for (K nutrient : nutrients.keySet()) {
            @Nullable Mass mass = nutrients.get(nutrient);
            if (mass != null) {
                mass.multiply(multiplier);
            }
        }
    }
}
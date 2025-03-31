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

    //TODO: finish this
    @Override
    default void sum(T addend) {
        //all macros of addend
        @NotNull Map<@NotNull MacroNutrient, @Nullable Mass> macroNutrients = addend.getMacroNutrients();
        for (@NotNull MacroNutrient nutrient : macroNutrients.keySet()) {
            @Nullable Mass addendMass = macroNutrients.get(nutrient);
            if (addendMass != null) {
                @Nullable Mass baseMass = getMacroNutrient(nutrient);
                if (baseMass != null) {
                    baseMass.sum(addendMass);
                } else {
                    getMacroNutrients().put(nutrient, addendMass);
                }
            }
        }
        //all micros of addend
        @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> microNutrients = addend.getMicroNutrients();
        for (@NotNull MicroNutrient nutrient : microNutrients.keySet()) {
            @Nullable Mass addendMass = microNutrients.get(nutrient);
            if (addendMass != null) {
                @Nullable Mass baseMass = getMicroNutrient(nutrient);
                if (baseMass != null) {
                    baseMass.sum(addendMass);
                } else {
                    getMicroNutrients().put(nutrient, addendMass);
                }
            }
        }
    }

    //TODO: abstract this
    @Override
    default void multiply(double multiplier) {
        //multiply all macros
        @NotNull Map<@NotNull MacroNutrient, @Nullable Mass> macroNutrients = getMacroNutrients();
        for (@NotNull MacroNutrient nutrient : macroNutrients.keySet()) {
            @Nullable Mass mass = macroNutrients.get(nutrient);
            if (mass != null) {
                mass.multiply(multiplier);
            }
        }
        //multiply all micros
        @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> microNutrients = getMicroNutrients();
        for (@NotNull MicroNutrient nutrient : microNutrients.keySet()) {
            @Nullable Mass mass = microNutrients.get(nutrient);
            if (mass != null) {
                mass.multiply(multiplier);
            }
        }
    }
}
package nutrition.digestion;

import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.NutrientInterface;
import nutrition.nutrients.MacroNutrient;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;

//
public interface NutrientSufficiencyInterface extends DailyNutritionalValueInterface {
    //
    default double compareNutrient(@NotNull NutritionalValue nutritionalValue, @NotNull NutrientInterface nutrient)
            throws NutrientNotNeededException {
        if (nutrient instanceof @NotNull MacroNutrient macroNutrient) {
            return compareMacroNutrient(nutritionalValue, macroNutrient);
        } else if (nutrient instanceof @NotNull MicroNutrient microNutrient) {
            return compareMicroNutrient(nutritionalValue, microNutrient);
        } else {
            throw new RuntimeException("Unrecognized nutrient type.");
        }
    }

    //
    default double compareMacroNutrient(@NotNull NutritionalValue nutritionalValue, @NotNull MacroNutrient nutrient)
            throws NutrientNotNeededException {
        return compareNutrientMasses(
                nutritionalValue.getMacroNutrient(nutrient),
                getDailyNutrients().getMacroNutrient(nutrient));
    }

    //
    default double compareMicroNutrient(@NotNull NutritionalValue nutritionalValue, @NotNull MicroNutrient nutrient)
            throws NutrientNotNeededException {
        return compareNutrientMasses(
                nutritionalValue.getMicroNutrient(nutrient),
                getDailyNutrients().getMicroNutrient(nutrient));
    }

    private static double compareNutrientMasses(@Nullable Mass comparable, @Nullable Mass reference)
            throws NutrientNotNeededException {
        if (reference == null) {
            throw new NutrientNotNeededException();
        } else {
            if (comparable == null) {
                return 0;
            } else {
                return comparable.getInBase() / reference.getInBase();
            }
        }
    }

    //
    default @NotNull SufficiencyAnalysis getNutrientSufficiency(@NotNull NutritionalValue nutritionalValue) {
        double lowestSufficiency = Double.MAX_VALUE;
        @Nullable NutrientInterface leastSufficientNutrient = null;
        @NotNull Map<@NotNull MacroNutrient, @NotNull Double> macroSufficiencyMap = new EnumMap<>(MacroNutrient.class);
        for (@NotNull MacroNutrient nutrient : getDailyNutrients().getMacroNutrients().keySet()) {
            try {
                double sufficiency = compareMacroNutrient(nutritionalValue, nutrient);
                macroSufficiencyMap.put(nutrient, sufficiency);
                if (sufficiency < lowestSufficiency) {
                    lowestSufficiency = sufficiency;
                    leastSufficientNutrient = nutrient;
                }
            } catch (@NotNull NutrientNotNeededException ignored) {}
        }
        @NotNull Map<@NotNull MicroNutrient, @NotNull Double> microSufficiencyMap = new EnumMap<>(MicroNutrient.class);
        for (@NotNull MicroNutrient nutrient : getDailyNutrients().getMicroNutrients().keySet()) {
            try {
                double sufficiency = compareMicroNutrient(nutritionalValue, nutrient);
                microSufficiencyMap.put(nutrient, sufficiency);
                if (sufficiency < lowestSufficiency) {
                    lowestSufficiency = sufficiency;
                    leastSufficientNutrient = nutrient;
                }
            } catch (@NotNull NutrientNotNeededException ignored) {}
        }
        @NotNull Map<NutrientInterface, Double> sufficiencyMap = new HashMap<>() {{
            putAll(macroSufficiencyMap);
            putAll(microSufficiencyMap);
        }};
        return new SufficiencyAnalysis(sufficiencyMap, leastSufficientNutrient);
    }

    //
    final class SufficiencyAnalysis {
        private final @NotNull Map<@NotNull NutrientInterface, @NotNull Double> sufficiencyMap;
        private final @Nullable NutrientInterface leastSufficientNutrient;

        //
        SufficiencyAnalysis(@NotNull Map<@NotNull NutrientInterface, @NotNull Double> sufficiencyMap,
                            @Nullable NutrientInterface leastSufficientNutrient) {
            this.sufficiencyMap = sufficiencyMap;
            this.leastSufficientNutrient = leastSufficientNutrient;
        }

        //
        public @NotNull Map<@NotNull NutrientInterface, @NotNull Double> getSufficiencyMap() {
            return sufficiencyMap;
        }

        //
        public @Nullable NutrientInterface getLeastSufficientNutrient() {
            return leastSufficientNutrient;
        }

        //
        public double getLowestSufficiency() throws NullPointerException {
            return sufficiencyMap.get(leastSufficientNutrient);
        }

        //
        public @Nullable Mass getMinimumMass() {
            @Nullable Mass mass = null;
            try {
                mass = new Mass(1 / getLowestSufficiency());
            } catch (@NotNull NullPointerException ignored) {}
            return mass;
        }
    }

    //
    final class NutrientNotNeededException extends Exception {
        NutrientNotNeededException() {
            super("Nutrient not needed.");
        }
    }
}
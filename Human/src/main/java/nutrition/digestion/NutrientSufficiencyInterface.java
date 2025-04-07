package nutrition.digestion;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
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
    default @NotNull SufficiencyAnalysis getNutrientSufficiency(@NotNull NutritionalValue nutritionalValue,
                                                                @Nullable Mass referenceMass) {
        @NotNull Mass nonNullReferenceMass = Objects.requireNonNullElse(referenceMass, new Mass(1));
        double
                lowestSufficiency = Double.MAX_VALUE,
                highestSufficiency = 0;
        @Nullable NutrientInterface
                leastSufficientNutrient = null,
                mostSufficientNutrient = null;
        @NotNull Map<NutrientInterface, Double> sufficiencyMap = new HashMap<>();

        //check all macros
        for (@NotNull MacroNutrient nutrient : getDailyNutrients().getMacroNutrients().keySet()) {
            try {
                double sufficiency = compareMacroNutrient(nutritionalValue, nutrient);
                sufficiencyMap.put(nutrient, sufficiency);
                if (sufficiency < lowestSufficiency) {
                    lowestSufficiency = sufficiency;
                    leastSufficientNutrient = nutrient;
                }
                if (sufficiency > highestSufficiency) {
                    highestSufficiency = sufficiency;
                    mostSufficientNutrient = nutrient;
                }
            } catch (@NotNull NutrientNotNeededException ignored) {}
        }

        //check all micros
        for (@NotNull MicroNutrient nutrient : getDailyNutrients().getMicroNutrients().keySet()) {
            try {
                double sufficiency = compareMicroNutrient(nutritionalValue, nutrient);
                sufficiencyMap.put(nutrient, sufficiency);
                if (sufficiency < lowestSufficiency) {
                    lowestSufficiency = sufficiency;
                    leastSufficientNutrient = nutrient;
                }
                if (sufficiency > highestSufficiency) {
                    highestSufficiency = sufficiency;
                    mostSufficientNutrient = nutrient;
                }
            } catch (@NotNull NutrientNotNeededException ignored) {}
        }

        return new SufficiencyAnalysis(nutritionalValue, nonNullReferenceMass,
                sufficiencyMap, leastSufficientNutrient, mostSufficientNutrient);
    }

    //
    final class SufficiencyAnalysis {
        private final @NotNull NutritionalValue nutritionalValue;
        private final @NotNull Mass referenceMass;
        private final @NotNull Map<@NotNull NutrientInterface, @NotNull Double> sufficiencyMap;
        private final @Nullable NutrientInterface
                leastSufficientNutrient,
                mostSufficientNutrient;
        private @Nullable List<@NotNull SingleNutrientSufficiencyData> sortedSufficiencies;

        //
        SufficiencyAnalysis(@NotNull NutritionalValue nutritionalValue, @NotNull Mass referenceMass,
                            @NotNull Map<@NotNull NutrientInterface, @NotNull Double> sufficiencyMap,
                            @Nullable NutrientInterface leastSufficientNutrient,
                            @Nullable NutrientInterface mostSufficientNutrient) {
            this.nutritionalValue = nutritionalValue;
            this.referenceMass = referenceMass;
            this.sufficiencyMap = sufficiencyMap;
            this.leastSufficientNutrient = leastSufficientNutrient;
            this.mostSufficientNutrient = mostSufficientNutrient;
            sortedSufficiencies = null;
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
        public @Nullable NutrientInterface getMostSufficientNutrient() {
            return mostSufficientNutrient;
        }

        //
        public double getLowestSufficiency() throws NullPointerException {
            return sufficiencyMap.get(leastSufficientNutrient);
        }

        //
        public double getHighestSufficiency() throws NullPointerException {
            return sufficiencyMap.get(mostSufficientNutrient);
        }

        //
        public @Nullable Mass getMinimumMass() {
            try {
                return getDividedReferenceMass(getLowestSufficiency());
            } catch (@NotNull NullPointerException ignored) {}
            return null;
        }

        //
        public @Nullable Mass getMaximumMass() {
            try {
                return getDividedReferenceMass(getHighestSufficiency());
            } catch (@NotNull NullPointerException ignored) {}
            return null;
        }

        private @NotNull Mass getDividedReferenceMass(double divisor) {
            return referenceMass.getMultiplied(1 / divisor);
        }

        //
        public @NotNull List<@NotNull SingleNutrientSufficiencyData> getSortedSufficiencies() {
            if (sortedSufficiencies == null) {
                sortSufficiencies();
            }
            return sortedSufficiencies;
        }

        private void sortSufficiencies() {
            @NotNull List<@NotNull NutrientInterface> unsortedNutrients = new ArrayList<>() {{
                addAll(sufficiencyMap.keySet());
            }};
            sortedSufficiencies = new ArrayList<>();
            while (!unsortedNutrients.isEmpty()) {
                double highestSufficiency = 0;
                @Nullable NutrientInterface mostSufficientNutrient = null;
                for (@NotNull NutrientInterface nutrient : unsortedNutrients) {
                    double sufficiency = sufficiencyMap.get(nutrient);
                    if (sufficiency >= highestSufficiency) {
                        highestSufficiency = sufficiency;
                        mostSufficientNutrient = nutrient;
                    }
                }
                if (mostSufficientNutrient == null) {
                    throw new RuntimeException("Could not find the most sufficient nutrient, unable to sort sufficiencies.");
                } else {
                    sortedSufficiencies.add(new SingleNutrientSufficiencyData(
                            mostSufficientNutrient,
                            nutritionalValue.getAnyNutrient(mostSufficientNutrient),
                            highestSufficiency));
                    unsortedNutrients.remove(mostSufficientNutrient);
                }
            }
        }
    }

    //
    final class SingleNutrientSufficiencyData {
        private final @NotNull NutrientInterface nutrient;
        private final @Nullable Mass mass;
        private final double sufficiency;

        //
        SingleNutrientSufficiencyData(@NotNull NutrientInterface nutrient, @Nullable Mass mass, double sufficiency) {
            this.nutrient = nutrient;
            this.mass = mass;
            this.sufficiency = sufficiency;
        }

        //
        public @NotNull NutrientInterface getNutrient() {
            return nutrient;
        }

        //
        public @Nullable Mass getMass() {
            return mass;
        }

        //
        public double getSufficiency() {
            return sufficiency;
        }
    }

    //
    final class NutrientNotNeededException extends Exception {
        NutrientNotNeededException() {
            super("Nutrient not needed.");
        }
    }
}
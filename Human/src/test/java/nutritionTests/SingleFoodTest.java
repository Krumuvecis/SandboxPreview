package nutritionTests;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.NutrientInterface;
import nutrition.nutrients.MacroNutrient;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;
import static nutrition.digestion.NutrientSufficiencyInterface.*;
import nutrition.digestion.DigestiveSystemInterface;
import nutrition.food.FoodInterface;
import nutrition.food.foodTypes.LooseFoodInterface;
import nutrition.food.foodTypes.DiscreteFoodInterface;
import static nutrition.food.particularFoods.ParticularLooseFoods.*;
import static nutrition.food.particularFoods.ParticularDiscreteFoods.*;

import static nutritionTests.NutritionTestConstants.*;
import static nutritionTests.NutritionTestConstants.printIndentedLine;

//
@SuppressWarnings("SameParameterValue")
public class SingleFoodTest extends AbstractNutritionTest {
    private static final @NotNull MassUnit
            MACROS_MASS_DISPLAY_UNIT = MassUnit.G,
            MICROS_MASS_DISPLAY_UNIT = MassUnit.MG;
    private static final int
            MACROS_MASS_DECIMAL_PLACES = 1,
            MICROS_MASS_DECIMAL_PLACES = 3,
            SUFFICIENCY_DECIMAL_PLACES = 1;
    private final @NotNull NutritionalValue baseNutrients;

    //
    public static void main(String[] args) {
        printLine("Single food test.");
        printLine(null);
        @NotNull String separator = "-".repeat(20);
        printLine(separator);
        printLine(null);

        new SingleFoodTest(BROCCOLI, true);
        new SingleFoodTest(SPINACH);
        new SingleFoodTest(LOOSE_SALAD_1);
        new SingleFoodTest(CARROTS);
        new SingleFoodTest(DISCRETE_SALAD_1);

        printLine(separator);
        printLine(null);

        new SingleFoodTest(TOMATO);
        new SingleFoodTest(CUCUMBER);
        new SingleFoodTest(LOOSE_SALAD_2);
        new SingleFoodTest(DISCRETE_SALAD_2);

        printLine(separator);
        printLine(null);

        new SingleFoodTest(OATS);
        new SingleFoodTest(SUGAR);
        new SingleFoodTest(DISCRETE_SALAD_3);

        printLine(separator);
        printLine(null);

        new SingleFoodTest(POTATO_BOILED);
        new SingleFoodTest(PEANUTS_ROASTED_UNSALTED);
        new SingleFoodTest(ALMONDS);
        new SingleFoodTest(HONEY);
        new SingleFoodTest(EGG_BOILED);
        new SingleFoodTest(MEAT_TUNA_CANNED);
        new SingleFoodTest(FISH_OIL_COD);
        //new SingleFoodTest(ENERGY_DRINK_MONSTER);
        new SingleFoodTest(DISCRETE_SALAD_4);
    }

    private SingleFoodTest(@NotNull FoodInterface food, boolean printRequirements) {
        super();
        if (printRequirements) {
            printLine("Reference nutritional requirements:");
            printNutritionRequirements(1);
            printLine(null);
        }
        baseNutrients = food.getBaseNutritionalValue();
        if (food instanceof @NotNull LooseFoodInterface looseFood) {
            analyzeLooseFood(1, looseFood);
        } else if (food instanceof @NotNull DiscreteFoodInterface discreteFood) {
            analyzeDiscreteFood(1, discreteFood);
        } else {
            throw new RuntimeException("Unrecognized food type.");
        }
    }

    private SingleFoodTest(@NotNull FoodInterface food) {
        this(food, false);
    }

    private void analyzeLooseFood(int indent, @NotNull LooseFoodInterface food) {
        printLine("Analyzing loose food: " + food.getName());
        @NotNull DigestiveSystemInterface digestiveSystem = getHuman().getDigestiveSystem();
        @NotNull Mass
                baseReferenceMass = new Mass(1),
                maximumDailyThroughput = digestiveSystem.getMaximumDailyFoodThroughput();
        @NotNull SufficiencyAnalysis baseNutrientSufficiencyAnalysis = digestiveSystem.getNutrientSufficiency(
                baseNutrients, new Mass(1));
        /*printNutritionalValueAndSufficiency(indent, "Base",
                baseReferenceMass, baseNutrientSufficiencyAnalysis, true);*/

        //minimum mass for sufficiency
        //  so all nutrients reach 100%
        //  (capped at throughput)
        @Nullable Mass minimumRequiredMass_asAnalyzed = baseNutrientSufficiencyAnalysis.getMinimumMass();
        @NotNull Mass minimumRequiredMass;
        if (minimumRequiredMass_asAnalyzed == null) {
            minimumRequiredMass = new Mass(0);
        } else if (minimumRequiredMass_asAnalyzed.getInBase() > maximumDailyThroughput.getInBase()) {
            minimumRequiredMass = maximumDailyThroughput;
        } else {
            minimumRequiredMass = minimumRequiredMass_asAnalyzed;
        }

        //maximum permissible mass
        //  so no nutrient go over 100%
        //  (capped at throughput)
        @Nullable Mass maximumPermissibleMass_asAnalyzed = baseNutrientSufficiencyAnalysis.getMaximumMass();
        @NotNull Mass maximumPermissibleMass;
        if (maximumPermissibleMass_asAnalyzed == null ||
                maximumPermissibleMass_asAnalyzed.getInBase() > maximumDailyThroughput.getInBase()) {
            maximumPermissibleMass = maximumDailyThroughput;
        } else {
            maximumPermissibleMass = maximumPermissibleMass_asAnalyzed;
        }

        //resulting optimal mass = min(minimum_required, maximum_permissible)
        @NotNull Mass optimalMass;
        if (minimumRequiredMass.getInBase() > maximumPermissibleMass.getInBase()) {
            optimalMass = maximumPermissibleMass;
        } else {
            optimalMass = minimumRequiredMass;
        }

        //nutrient sufficiency at optimal mass
        @NotNull SufficiencyAnalysis optimalSufficiencyAnalysis = digestiveSystem.getNutrientSufficiency(
                baseNutrients.getMultiplied(optimalMass.getInBase()), optimalMass);
        printNutritionalValueAndSufficiency(indent, "Maximally optimal",
                optimalMass, optimalSufficiencyAnalysis, true);

        printLine(null);
    }

    private void analyzeDiscreteFood(int indent, @NotNull DiscreteFoodInterface food) {
        printLine("Analyzing discrete food: " + food.getName());
        @NotNull DigestiveSystemInterface digestiveSystem = getHuman().getDigestiveSystem();
        @NotNull Mass
                baseReferenceMass = new Mass(1),
                unitMass = food.getUnitMass(),
                maximumDailyThroughput = digestiveSystem.getMaximumDailyFoodThroughput();
        int maxThroughputUnits = (int) Math.floor(maximumDailyThroughput.getInBase() / unitMass.getInBase());
        @NotNull SufficiencyAnalysis
                baseNutrientSufficiencyAnalysis = digestiveSystem.getNutrientSufficiency(baseNutrients, baseReferenceMass),
                unitNutrientSufficiencyAnalysis = digestiveSystem.getNutrientSufficiency(food.getUnitNutritionalValue(), unitMass);
        /*printNutritionalValueAndSufficiency(indent, "Base",
                baseReferenceMass, baseNutrientSufficiencyAnalysis, true);*/
        printNutritionalValueAndSufficiency(indent, "Unit",
                unitMass, unitNutrientSufficiencyAnalysis, true);

        //minimum count for sufficiency
        //  so all nutrients reach 100%
        //  (capped at throughput)
        @Nullable Mass minimumRequiredMass_asAnalyzed = baseNutrientSufficiencyAnalysis.getMinimumMass();
        int minimumRequiredCount;
        if (minimumRequiredMass_asAnalyzed == null) {
            minimumRequiredCount = 0;
        } else {
            minimumRequiredCount = Math.min(
                    maxThroughputUnits,
                    (int) Math.ceil(minimumRequiredMass_asAnalyzed.getInBase() / unitMass.getInBase()));
        }

        //maximum permissible count
        //  so no nutrient go over 100%
        //  (capped at throughput)
        @Nullable Mass maximumPermissibleMass_asAnalyzed = baseNutrientSufficiencyAnalysis.getMaximumMass();
        int maximumPermissibleCount;
        if (maximumPermissibleMass_asAnalyzed == null ||
                maximumPermissibleMass_asAnalyzed.getInBase() > maximumDailyThroughput.getInBase()) {
            maximumPermissibleCount = maxThroughputUnits;
        } else {
            maximumPermissibleCount = (int) Math.floor(maximumPermissibleMass_asAnalyzed.getInBase() / unitMass.getInBase());
        }

        //resulting optimal count = min(minimum_required, maximum_permissible)
        int optimalCount = Math.min(minimumRequiredCount, maximumPermissibleCount);
        printIndentedLine(indent, "Maximally optimal count: " + optimalCount);
        @NotNull Mass optimalMass = food.getUnitMass().getMultiplied(optimalCount);

        //nutrient sufficiency at optimal mass
        @NotNull SufficiencyAnalysis optimalSufficiencyAnalysis = digestiveSystem.getNutrientSufficiency(
                baseNutrients.getMultiplied(optimalMass.getInBase()), optimalMass);
        /*printNutritionalValueAndSufficiency(indent, "Maximally optimal",
                optimalMass, optimalSufficiencyAnalysis, true);*/

        printLine(null);
    }

    private void printNutritionalValueAndSufficiency(int indent, @NotNull String prefix, @NotNull Mass referenceMass,
                                                     @NotNull SufficiencyAnalysis sufficiencyAnalysis, boolean printNull) {
        @NotNull String referenceMassString = referenceMass.getValueAndShortUnit(MassUnit.G, 0);
        printIndentedLine(indent, prefix + " nutritional value " + "(per " + referenceMassString + "):");
        @Nullable List<@NotNull SingleNutrientSufficiencyData> sortedSufficiencies = sufficiencyAnalysis.getSortedSufficiencies();
        for (@NotNull SingleNutrientSufficiencyData data : sortedSufficiencies) {
            @Nullable Mass nutrientMass = data.getMass();

            if (nutrientMass == null && !printNull) continue;

            @NotNull NutrientInterface nutrient = data.getNutrient();
            @NotNull String nutrientMassString;
            if (nutrientMass == null) {
                nutrientMassString = "-";
            } else {
                @NotNull MassUnit unit;
                int decimalPlaces;
                if (nutrient instanceof @NotNull MacroNutrient) {
                    unit = MACROS_MASS_DISPLAY_UNIT;
                    decimalPlaces = MACROS_MASS_DECIMAL_PLACES;
                } else if (nutrient instanceof @NotNull MicroNutrient) {
                    unit = MICROS_MASS_DISPLAY_UNIT;
                    decimalPlaces = MICROS_MASS_DECIMAL_PLACES;
                } else {
                    throw new RuntimeException("Unrecognized nutrient type.");
                }
                nutrientMassString = nutrientMass.getValueAndShortUnit(unit, decimalPlaces);
            }
            printIndentedLine(indent + 1, nutrient.getName() + ": " + nutrientMassString +
                    ", " + getRoundedPercentageString(data.getSufficiency(), SUFFICIENCY_DECIMAL_PLACES));
        }
    }
}
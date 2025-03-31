package nutritionTests;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.NutrientInterface;
import nutrition.nutrients.NutritionalValue;
import nutrition.digestion.DigestiveSystemInterface;
import nutrition.food.SimpleFood;
import static nutrition.food.ParticularFoods.*;

import static nutritionTests.NutritionTestConstants.*;
import static nutritionTests.NutritionTestConstants.printIndentedLine;

//
@SuppressWarnings("SameParameterValue")
public class SingleFoodTest extends AbstractNutritionTest {
    private final @NotNull NutritionalValue foodBaseNutrients;
    
    //
    public static void main(String[] args) {
        printLine("Single food test.");
        printLine(null);
        new SingleFoodTest(SPINACH, true);
        new SingleFoodTest(ALMONDS);
    }

    SingleFoodTest(@NotNull SimpleFood food, boolean printRequirements) {
        super();
        if (printRequirements) {
            printLine("Reference nutritional requirements:");
            printNutritionRequirements(1);
            printLine(null);
        }
        printLine("Analyzing single food: " + food.getName());
        foodBaseNutrients = food.getNutritionalValue();
        printBaseNutritionalValue(1);
        @NotNull DigestiveSystemInterface digestiveSystem = getHuman().getDigestiveSystem();
        @NotNull Mass maximumDailyThroughput = digestiveSystem.getMaximumDailyFoodThroughput();
        @NotNull Mass totalMass = calculateTotalMass(digestiveSystem, maximumDailyThroughput);
        printMinimumTotalMass(1, maximumDailyThroughput, totalMass);
        @NotNull NutritionalValue totalNutritionalValue = food.getNutritionalValue(totalMass);
        printNutritionalValuePerTotalMass(1, totalNutritionalValue);
        printNutrientSufficiencyPerTotalMass(1, totalNutritionalValue);
        printLine(null);
    }

    SingleFoodTest(@NotNull SimpleFood food) {
        this(food, false);
    }

    private void printBaseNutritionalValue(int indent) {
        printIndentedLine(indent, "Nutritional value (per 1 kg):");
        printNutritionalValue_all(foodBaseNutrients, false, indent + 1, MassUnit.G, 1, MassUnit.MG, 3);
    }

    private @NotNull Mass calculateTotalMass(@NotNull DigestiveSystemInterface digestiveSystem,
                                             @NotNull Mass maximumDailyThroughput) {
        @Nullable Mass minimumMass = digestiveSystem.getNutrientSufficiency(foodBaseNutrients).getMinimumMass();
        if (minimumMass == null || minimumMass.getInBase() > maximumDailyThroughput.getInBase()) {
            return maximumDailyThroughput;
        } else {
            return minimumMass;
        }
    }

    private void printMinimumTotalMass(int indent, @NotNull Mass maximumDailyThroughput, @NotNull Mass mass) {
        printIndentedLine(indent, "Total mass to suffice for a day " +
                "(capped at " + maximumDailyThroughput.getValueAndShortUnit(1) + "): " +
                mass.getValueAndShortUnit(MassUnit.G, 0));
    }

    private void printNutritionalValuePerTotalMass(int indent, @NotNull NutritionalValue nutritionalValue) {
        printIndentedLine(indent, "Nutritional value (total mass):");
        printNutritionalValue_all(nutritionalValue, true, indent + 1, MassUnit.G, 1, MassUnit.MG, 3);
    }

    private void printNutrientSufficiencyPerTotalMass(int indent, @NotNull NutritionalValue nutritionalValue) {
        printIndentedLine(indent, "Daily sufficiency (total mass):");
        @NotNull Map<@NotNull NutrientInterface, @NotNull Double>
                sufficiencyMap = getHuman().getDigestiveSystem().getNutrientSufficiency(nutritionalValue).getSufficiencyMap();
        for (@NotNull NutrientInterface nutrient : sufficiencyMap.keySet()) {
            double sufficiency = sufficiencyMap.get(nutrient);
            printIndentedLine(indent + 1, nutrient.getName() + ": " +
                    getRoundedPercentageString(sufficiency, 1));
        }
    }
}
package nutritionTests;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.NutrientInterface;
import nutrition.nutrients.NutritionalValue;
import nutrition.digestion.DigestiveHumanInterface;
import nutrition.food.*;

import static nutritionTests.NutritionTestConstants.*;
import static nutritionTests.NutritionTestConstants.printIndentedLine;

//
public class FoodMixTest extends AbstractNutritionTest {

    //
    public static void main(String[] args) {
        printLine("Food mix test.");
        printLine(null);
        @NotNull DigestiveHumanInterface human = new TestHuman();
        new FoodMixTest(human, new TestFoodMix_auto(human), true);
    }

    private FoodMixTest(@NotNull DigestiveHumanInterface human, @NotNull FoodMixInterface foodMix,
                        boolean printRequirements) {
        super(human);
        if (printRequirements) {
            printLine("Reference nutritional requirements:");
            printNutritionRequirements(1);
            printLine(null);
        }
        printLine("Analyzing food mix:");
        @NotNull NutritionalValue foodMixBaseNutrients = foodMix.getNutritionalValue();
        @Nullable Mass minimumMass = getHuman().getDigestiveSystem().getNutrientSufficiency(foodMixBaseNutrients).getMinimumMass();

        printConstituents(1, foodMix, minimumMass);
        printNutritionalValuePer1kg(1, foodMixBaseNutrients);
        printMinimumMass(1, minimumMass);
        @NotNull NutritionalValue totalNutritionalValue = foodMixBaseNutrients.getMultiplied(minimumMass.getInBase());
        printNutritionalValuePerTotalMass(1, totalNutritionalValue);
        printNutrientSufficiencyPerTotalMass(1, totalNutritionalValue);
        printLine(null);
    }

    @SuppressWarnings("unused")
    private FoodMixTest(@NotNull DigestiveHumanInterface human, @NotNull FoodMixInterface foodMix) {
        this(human, foodMix, false);
    }

    @SuppressWarnings("SameParameterValue")
    private void printConstituents(int indent, @NotNull FoodMixInterface foodMix, @NotNull Mass minimumMass) {
        printIndentedLine(indent, "Constituents:");
        @NotNull Map<@NotNull FoodMixConstituent, @NotNull Double> foodRatios = foodMix.getFoodRatios();
        for (@NotNull FoodMixConstituent constituent : foodRatios.keySet()) {
            double ratio = foodRatios.get(constituent);
            @NotNull Mass mass = minimumMass.getMultiplied(ratio);
            printIndentedLine(indent + 1, constituent.getTemplate().getFood().getName() +
                    " - " + getRoundedPercentageString(ratio, 1) +
                    " - " + mass.getValueAndShortUnit(MassUnit.G, 1));
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void printNutritionalValuePer1kg(int indent, @NotNull NutritionalValue nutritionalValue) {
        printIndentedLine(indent, "Nutritional value (per 1 kg):");
        printNutritionalValue_all(nutritionalValue, false, indent + 1, MassUnit.G, 1, MassUnit.MG, 3);
    }

    @SuppressWarnings("SameParameterValue")
    private void printMinimumMass(int indent, @NotNull Mass mass) {
        printIndentedLine(indent, "Minimum total mass to suffice for a day: " +
                mass.getValueAndShortUnit(MassUnit.G, 0));
    }

    @SuppressWarnings("SameParameterValue")
    private void printNutritionalValuePerTotalMass(int indent, @NotNull NutritionalValue nutritionalValue) {
        printIndentedLine(indent, "Nutritional value (total mass):");
        printNutritionalValue_all(nutritionalValue, true, indent + 1, MassUnit.G, 1, MassUnit.MG, 3);
    }

    @SuppressWarnings("SameParameterValue")
    private void printNutrientSufficiencyPerTotalMass(int indent, @NotNull NutritionalValue nutritionalValue) {
        printIndentedLine(indent, "Daily sufficiency (total mass):");
        @NotNull Map<@NotNull NutrientInterface, @NotNull Double>
                nutrientSufficiencyMap = getHuman().getDigestiveSystem().getNutrientSufficiency(nutritionalValue).getSufficiencyMap();
        for (@NotNull NutrientInterface nutrient : nutrientSufficiencyMap.keySet()) {
            printIndentedLine(indent + 1, nutrient.getName() + ": " +
                    getRoundedPercentageString(nutrientSufficiencyMap.get(nutrient), 1));
        }
    }
}
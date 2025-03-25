package humanRequirementTests.nutritionTests;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import humanRequirements.nutritionRequirements.NutritionRequirements;

import static humanRequirementTests.nutritionTests.NutritionTestConstants.INDENT;

//
public class NutritionRequirementsTest {
    private static final @NotNull NutritionRequirements
            LUXURY_NUTRITION_REQUIREMENTS = new NutritionRequirements.LuxuryNutritionRequirements(),
            CIVILIAN_NUTRITION_REQUIREMENTS = new NutritionRequirements.CivilianNutritionRequirements(),
            MILITARY_NUTRITION_REQUIREMENTS = new NutritionRequirements.MilitaryNutritionRequirements();

    //
    public static void main(String[] args) {
        printLine("Nutrition requirements test.");
        printLine(INDENT);
        testNutritionRequirements(LUXURY_NUTRITION_REQUIREMENTS, "luxury");
        testNutritionRequirements(CIVILIAN_NUTRITION_REQUIREMENTS, "civilian");
        testNutritionRequirements(MILITARY_NUTRITION_REQUIREMENTS, "military");
        testNutritionRequirements(new NutritionTestConstants.CustomNutritionRequirements(), "custom");
    }

    private static void testNutritionRequirements(@NotNull NutritionRequirements nutritionRequirements,
                                                  @NotNull String requirementsName) {
        printLine("Testing " + requirementsName + " requirements:");
        NutritionTestConstants.printNutritionRequirements(nutritionRequirements, 1);
        printLine(INDENT);
    }
}
package humanRequirementTests.nutritionTests;

import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.Mass;
import dimensions.mass.MassUnit;
import food.NutrientEnum;
import food.NutritionalValue;
import humanRequirements.nutritionRequirements.Diet;
import humanRequirements.nutritionRequirements.NutritionRequirements;

//
class NutritionTestConstants {
    //
    static final @NotNull String INDENT = "  ";

    //
    static void printIndentedLine(int indent, @NotNull String line) {
        printLine(INDENT.repeat(indent) + line);
    }

    //
    @SuppressWarnings("SameParameterValue")
    static void printNutritionalValue(@NotNull NutritionalValue nutrients,
                                      int indent, @NotNull MassUnit displayUnit, int decimalPlaces) {
        printSingleNutrient(nutrients, NutrientEnum.PROTEIN, indent, displayUnit, decimalPlaces);
        printSingleNutrient(nutrients, NutrientEnum.FAT, indent, displayUnit, decimalPlaces);
        printSingleNutrient(nutrients, NutrientEnum.CARB, indent, displayUnit, decimalPlaces);
    }

    private static void printSingleNutrient(@NotNull NutritionalValue nutrients, @NotNull NutrientEnum nutrient,
                                            int indent, @NotNull MassUnit displayUnit, int decimalPlaces) {
        printIndentedLine(indent, nutrient.getName() + ": " +
                nutrients.get(nutrient).getValueAndShortUnit(displayUnit, decimalPlaces));
    }

    //
    @SuppressWarnings("SameParameterValue")
    static void printNutritionRequirements(@NotNull NutritionRequirements nutritionRequirements, int indent) {
        //body weight & diet
        printIndentedLine(indent, "Body weight: " + nutritionRequirements.getBodyWeight().getValueAndShortUnit(0));
        printIndentedLine(indent, "Diet:");
        @NotNull Diet diet = nutritionRequirements.getDiet();
        printIndentedLine(indent + 1, "Daily calories: " + nutritionRequirements.getDailyCalories() + " kcal");
        printIndentedLine(indent + 1, "Daily protein per body weight: " +
                diet.getNutrientsPerBodyMass().get(NutrientEnum.PROTEIN).getValueAndShortUnit(MassUnit.G, 1) +
                " / " + new Mass(1).getValueAndShortUnit());
        printIndentedLine(indent + 1, "Calories from fats: " + ((int) (diet.getFatsEnergyRatio() * 100)) + " %");

        //daily totals
        printIndentedLine(indent, "Daily nutrients:");
        @NotNull NutritionalValue dailyNutrients = nutritionRequirements.getDailyNutrients();
        printNutritionalValue(dailyNutrients, indent + 1, MassUnit.G, 1);
    }

    //
    static final class CustomNutritionRequirements extends NutritionRequirements {
        private static final @NotNull Diet
                REFERENCE_DIET = new Diet(0.3, new EnumMap<>(NutrientEnum.class) {{
                    put(NutrientEnum.PROTEIN, new Mass(0.8, MassUnit.G));
        }});
        private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(60);
        private static final double DAILY_CALORIES = 2000;

        //
        CustomNutritionRequirements() {
            //super(REFERENCE_DIET, REFERENCE_PERSON_MASS, DAILY_CALORIES);
            super(new CivilianDiet(), REFERENCE_PERSON_MASS, DAILY_CALORIES);
        }
    }
}
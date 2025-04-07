package nutritionTests;

import java.util.Objects;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.Mass;
import dimensions.mass.MassUnit;
import nutrition.nutrients.NutrientInterface;
import nutrition.nutrients.MacroNutrient;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;

//
@SuppressWarnings("SameParameterValue")
class NutritionTestConstants {
    //
    private static final @NotNull String INDENT = "  ";

    //
    static @NotNull String getRoundedPercentageString(double coefficient, int decimalPlaces) {
        return (((int) (coefficient * 100 * Math.pow(10, decimalPlaces))) / Math.pow(10, decimalPlaces)) + " %";
    }

    //
    static void printIndentedLine(int indent, @Nullable String line) {
        printLine(INDENT.repeat(indent) + Objects.requireNonNullElse(line, ""));
    }

    //
    static void printNutritionalValue_all(@NotNull NutritionalValue nutritionalValue, boolean displayNull, int indent,
                                          @NotNull MassUnit macrosDisplayUnit, int macrosDecimalPlaces,
                                          @NotNull MassUnit microsDisplayUnit, int microsDecimalPlaces) {
        printNutritionalValue_macros(nutritionalValue, displayNull, indent, macrosDisplayUnit, macrosDecimalPlaces);
        printNutritionalValue_micros(nutritionalValue, displayNull, indent, microsDisplayUnit, microsDecimalPlaces);
    }

    //
    static void printNutritionalValue_macros(@NotNull NutritionalValue nutritionalValue, boolean displayNull,
                                             int indent, @NotNull MassUnit displayUnit, int decimalPlaces) {
        @NotNull Map<@NotNull MacroNutrient, @Nullable Mass> nutrients = nutritionalValue.getMacroNutrients();
        @NotNull MacroNutrient @NotNull [] checkableNutrients = MacroNutrient.values();
        for (@NotNull MacroNutrient nutrient : checkableNutrients) {
            @Nullable Mass nutrientMass = nutrients.get(nutrient);
            if (nutrientMass != null || displayNull) {
                printSingleNutrient(nutrient, nutrientMass, indent, displayUnit, decimalPlaces);
            }
        }
    }

    //
    static void printNutritionalValue_micros(@NotNull NutritionalValue nutritionalValue, boolean displayNull,
                                             int indent, @NotNull MassUnit displayUnit, int decimalPlaces) {
        @NotNull Map<@NotNull NutrientInterface, @Nullable Mass> nutrients = nutritionalValue.getAllNutrients();
        @NotNull MicroNutrient @NotNull [] checkableNutrients = MicroNutrient.values();
        for (@NotNull MicroNutrient nutrient : checkableNutrients) {
            @Nullable Mass nutrientMass = nutrients.get(nutrient);
            if (nutrientMass != null || displayNull) {
                printSingleNutrient(nutrient, nutrientMass, indent, displayUnit, decimalPlaces);
            }
        }
    }

    private static void printSingleNutrient(@NotNull NutrientInterface nutrient, @Nullable Mass nutrientMass,
                                            int indent, @NotNull MassUnit displayUnit, int decimalPlaces) {
        @NotNull String nutrientValueString;
        if (nutrientMass == null) {
            nutrientValueString = "-";
        } else {
            nutrientValueString = nutrientMass.getValueAndShortUnit(displayUnit, decimalPlaces);
        }
        printIndentedLine(indent, nutrient.getName() + ": " + nutrientValueString);
    }
}
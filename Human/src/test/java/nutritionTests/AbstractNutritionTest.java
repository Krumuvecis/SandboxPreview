package nutritionTests;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.NutritionalValue;
import nutrition.digestion.Diet;
import nutrition.digestion.DigestiveSystemInterface;
import nutrition.digestion.DigestiveHumanInterface;

import static nutritionTests.NutritionTestConstants.*;

//
abstract class AbstractNutritionTest {
    private static final @NotNull DigestiveHumanInterface DEFAULT_HUMAN = new TestHuman();
    private final @NotNull DigestiveHumanInterface human;

    AbstractNutritionTest(@Nullable DigestiveHumanInterface human) {
        this.human = Objects.requireNonNullElse(human, DEFAULT_HUMAN);
    }

    AbstractNutritionTest() {
        this(null);
    }

    //
    final @NotNull DigestiveHumanInterface getHuman() {
        return human;
    }

    public final void printNutritionRequirements(int indent) {
        //body weight & diet
        printIndentedLine(indent, "Body mass: " + human.getBodyMass().getValueAndShortUnit(0));
        @NotNull DigestiveSystemInterface digestiveSystem = human.getDigestiveSystem();
        printIndentedLine(indent, "Diet:");
        @NotNull Diet diet = digestiveSystem.getDiet();
        printIndentedLine(indent + 1, "Daily calories: " + digestiveSystem.getDailyCalories() + " kcal");
        printIndentedLine(indent + 1, "Daily protein per body weight: " +
                diet.getProteinsPerBodyMass().getValueAndShortUnit(MassUnit.G, 1) +
                " / " + new Mass(1).getValueAndShortUnit());
        printIndentedLine(indent + 1, "Calories from fats: " +
                getRoundedPercentageString(diet.getFatsEnergyRatio(), 1));

        //daily totals
        printIndentedLine(indent, "Daily nutrients:");
        @NotNull NutritionalValue dailyNutrients = digestiveSystem.getDailyNutrients();
        printNutritionalValue_all(dailyNutrients, true, indent + 1,
                MassUnit.G, 1, MassUnit.MG, 3);
    }
}
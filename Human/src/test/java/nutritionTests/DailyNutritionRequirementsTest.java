package nutritionTests;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import nutrition.digestion.DigestiveHumanInterface;
import human.StandardDigestiveHuman;

//
public class DailyNutritionRequirementsTest extends AbstractNutritionTest {
    private static final @NotNull DigestiveHumanInterface
            HUMAN_LUXURY = new StandardDigestiveHuman.StandardHuman_luxury(),
            HUMAN_CIVILIAN = new StandardDigestiveHuman.StandardHuman_civilian(),
            HUMAN_MILITARY = new StandardDigestiveHuman.StandardHuman_military();

    //
    public static void main(String[] args) {
        printLine("Nutrition requirements test.");
        printLine(null);
        new DailyNutritionRequirementsTest(HUMAN_LUXURY, "luxury");
        new DailyNutritionRequirementsTest(HUMAN_CIVILIAN, "civilian");
        new DailyNutritionRequirementsTest(HUMAN_MILITARY, "military");
        new DailyNutritionRequirementsTest(null, "custom");
    }

    private DailyNutritionRequirementsTest(@Nullable DigestiveHumanInterface human,
                                           @NotNull String requirementsName) {
        super(human);
        printLine("Testing " + requirementsName + " requirements:");
        printNutritionRequirements(1);
        printLine(null);
    }
}
package nutritionTests;

import static consoleUtils.SimplePrinting.printLine;

//TODO: finish this
public class MealPlanTest extends AbstractNutritionTest {
    //
    public static void main(String[] args) {
        printLine("Meal plan test.");
        printLine(null);
        new MealPlanTest(true);
        new MealPlanTest();
    }

    private MealPlanTest(boolean printRequirements) {
        super();
        if (printRequirements) {
            printLine("Reference nutritional requirements:");
            printNutritionRequirements(1);
            printLine(null);
        }

        //TODO: test meal plan here
    }

    private MealPlanTest() {
        this(false);
    }
}
package nutritionTests;

import human.StandardDigestiveHuman;
import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.digestion.Diet;

//
final class TestHuman extends StandardDigestiveHuman {
    private static final @NotNull Diet REFERENCE_DIET = new Diet(
            0.3, new Mass(0.8, MassUnit.G), Diet.STANDARD_MICRONUTRIENTS);
    private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(60);
    private static final double DAILY_CALORIES = 2000;
    private static final @NotNull Mass MAXIMUM_DAILY_FOOD_THROUGHPUT = new Mass(1);

    //
    TestHuman() {
        super(REFERENCE_PERSON_MASS, DAILY_CALORIES, REFERENCE_DIET, MAXIMUM_DAILY_FOOD_THROUGHPUT);
    }
}
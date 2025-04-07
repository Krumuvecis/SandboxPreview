package human;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import nutrition.digestion.Diet;
import nutrition.digestion.DigestiveSystemInterface;
import nutrition.digestion.HumanDigestiveSystem;
import nutrition.digestion.DigestiveHumanInterface;

//
public class StandardDigestiveHuman implements DigestiveHumanInterface {
    private @NotNull Mass bodyMass;
    private final @NotNull DigestiveSystemInterface digestiveSystem;

    //
    public StandardDigestiveHuman(@NotNull Mass bodyMass, double dailyCalories, @NotNull Diet diet,
                                  @NotNull Mass maximumDailyFoodThroughput) {
        this.bodyMass = bodyMass;
        this.digestiveSystem = new HumanDigestiveSystem(this, dailyCalories, diet, maximumDailyFoodThroughput);
    }

    //
    @Override
    public final @NotNull Mass getBodyMass() {
        return bodyMass;
    }

    //
    @Override
    public final @NotNull DigestiveSystemInterface getDigestiveSystem() {
        return digestiveSystem;
    }

    //
    @Override
    public final void setBodyMass(@NotNull Mass bodyMass) {
        this.bodyMass = bodyMass;
        digestiveSystem.updateDiet();
    }

    //
    public static final class StandardHuman_luxury extends StandardDigestiveHuman {
        private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(150);
        private static final double DAILY_CALORIES = 3000;
        private static final @NotNull Diet REFERENCE_DIET = Diet.LUXURY_DIET;
        private static final @NotNull Mass MAXIMUM_DAILY_FOOD_THROUGHPUT = new Mass(3);

        //
        public StandardHuman_luxury() {
            super(REFERENCE_PERSON_MASS, DAILY_CALORIES, REFERENCE_DIET, MAXIMUM_DAILY_FOOD_THROUGHPUT);
        }
    }

    //
    public static final class StandardHuman_civilian extends StandardDigestiveHuman {
        private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(80);
        private static final double DAILY_CALORIES = 2200;
        private static final @NotNull Diet REFERENCE_DIET = Diet.CIVILIAN_DIET;
        private static final @NotNull Mass MAXIMUM_DAILY_FOOD_THROUGHPUT = new Mass(2);

        //
        public StandardHuman_civilian() {
            super(REFERENCE_PERSON_MASS, DAILY_CALORIES, REFERENCE_DIET, MAXIMUM_DAILY_FOOD_THROUGHPUT);
        }
    }

    //
    public static final class StandardHuman_military extends StandardDigestiveHuman {
        private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(80);
        private static final double DAILY_CALORIES = 4000;
        private static final @NotNull Diet REFERENCE_DIET = Diet.MILITARY_DIET;
        private static final @NotNull Mass MAXIMUM_DAILY_FOOD_THROUGHPUT = new Mass(3);

        //
        public StandardHuman_military() {
            super(REFERENCE_PERSON_MASS, DAILY_CALORIES, REFERENCE_DIET, MAXIMUM_DAILY_FOOD_THROUGHPUT);
        }
    }
}
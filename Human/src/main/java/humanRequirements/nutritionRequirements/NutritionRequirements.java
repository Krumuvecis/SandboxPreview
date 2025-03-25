package humanRequirements.nutritionRequirements;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import food.NutrientEnum;
import food.NutritionalValue;

//
public class NutritionRequirements extends Diet implements NutritionRequirementInterface {
    private @NotNull Mass bodyWeight;
    private double dailyCalories;
    private @NotNull NutritionalValue dailyNutrients;

    //custom requirements
    public NutritionRequirements(@NotNull Diet diet, @NotNull Mass bodyWeight, double dailyCalories) {
        super(diet);
        this.bodyWeight = bodyWeight;
        this.dailyCalories = dailyCalories;
        dailyNutrients = getNewDailyNutrients();
    }

    private void update() {
        dailyNutrients = getNewDailyNutrients();
    }

    private @NotNull NutritionalValue getNewDailyNutrients() {
        @NotNull Mass dailyProtein = new Mass(
                getNutrientsPerBodyMass().get(NutrientEnum.PROTEIN).getInBase() * bodyWeight.getInBase());
        double dailyCaloriesFromFats = getDailyCalories() * getFatsEnergyRatio();
        @NotNull Mass
                dailyFats = NutrientEnum.FAT.calculateMassFromEnergy(dailyCaloriesFromFats),
                dailyCarbs = calculateDailyCarbs(dailyProtein, dailyCaloriesFromFats);
        return new NutritionalValue(dailyProtein, dailyFats, dailyCarbs);
    }

    private @NotNull Mass calculateDailyCarbs(@NotNull Mass dailyProtein, double dailyCaloriesFromFats) {
        double
                dailyCaloriesFromProtein = NutrientEnum.PROTEIN.calculateEnergyFromMass(dailyProtein),
                dailyCaloriesFromCarbs =
                        getDailyCalories() - dailyCaloriesFromProtein - dailyCaloriesFromFats;
        return NutrientEnum.CARB.calculateMassFromEnergy(dailyCaloriesFromCarbs);
    }

    //
    public @NotNull Mass getBodyWeight() {
        return bodyWeight;
    }

    //
    @Override
    public final double getDailyCalories() {
        return dailyCalories;
    }

    //
    @Override
    public final @NotNull Diet getDiet() {
        return this;
    }

    @Override
    public @NotNull NutritionalValue getDailyNutrients() {
        return dailyNutrients;
    }

    //
    @Override
    public final void setBodyWeight(@NotNull Mass bodyWeight) {
        this.bodyWeight = bodyWeight;
        update();
    }

    //
    @Override
    public final void set(@NotNull Diet diet) {
        super.set(diet);
        update();
    }

    //
    @Override
    public final void setDailyCalories(double dailyCalories) {
        this.dailyCalories = dailyCalories;
        update();
    }

    //
    @Override
    public final void setFatsEnergyRatio(double fatsEnergyRatio) {
        super.setFatsEnergyRatio(fatsEnergyRatio);
        update();
    }

    //
    public static class LuxuryNutritionRequirements extends NutritionRequirements {
        private static final @NotNull Diet REFERENCE_DIET = new Diet.LuxuryDiet();
        private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(200);
        private static final double DAILY_CALORIES = 3000;

        //
        public LuxuryNutritionRequirements() {
            super(REFERENCE_DIET, REFERENCE_PERSON_MASS, DAILY_CALORIES);
        }
    }

    //
    public static class CivilianNutritionRequirements extends NutritionRequirements {
        private static final @NotNull Diet REFERENCE_DIET = new Diet.CivilianDiet();
        private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(100);
        private static final double DAILY_CALORIES = 2200;

        //
        public CivilianNutritionRequirements() {
            super(REFERENCE_DIET, REFERENCE_PERSON_MASS, DAILY_CALORIES);
        }
    }

    //
    public static class MilitaryNutritionRequirements extends NutritionRequirements {
        private static final @NotNull Diet REFERENCE_DIET = new Diet.MilitaryDiet();
        private static final @NotNull Mass REFERENCE_PERSON_MASS = new Mass(70);
        private static final double DAILY_CALORIES = 5000;

        //
        public MilitaryNutritionRequirements() {
            super(REFERENCE_DIET, REFERENCE_PERSON_MASS, DAILY_CALORIES);
        }
    }
}
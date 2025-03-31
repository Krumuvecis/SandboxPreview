package nutrition.digestion;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.MacroNutrient;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;

//
final class ParticularDiet extends Diet implements DailyNutritionalValueInterface {
    private final @NotNull MassiveHumanInterface human;
    private final @NotNull DigestiveSystemInterface digestiveSystem;
    private @NotNull NutritionalValue dailyNutrients;

    //
    ParticularDiet(@NotNull MassiveHumanInterface human, @NotNull DigestiveSystemInterface digestiveSystem,
                   @NotNull Diet diet) {
        super(diet);
        this.human = human;
        this.digestiveSystem = digestiveSystem;
        dailyNutrients = calculateDailyNutrients();
    }

    //
    void update() {
        dailyNutrients = calculateDailyNutrients();
    }

    private @NotNull NutritionalValue calculateDailyNutrients() {
        @NotNull Mass dailyProtein = getProteinsPerBodyMass().getMultiplied(human.getBodyMass().getInBase());
        double
                dailyCalories = digestiveSystem.getDailyCalories(),
                dailyCaloriesFromFats = dailyCalories * getFatsEnergyRatio();
        @NotNull Mass
                dailyFats = MacroNutrient.FAT.calculateMassFromEnergy(dailyCaloriesFromFats),
                dailyCarbs = calculateDailyCarbs(dailyProtein, dailyCalories, dailyCaloriesFromFats);
        return new NutritionalValue(dailyProtein, dailyFats, dailyCarbs, getMicroNutrients());
    }

    private @NotNull Mass calculateDailyCarbs(@NotNull Mass dailyProtein,
                                              double dailyCalories, double dailyCaloriesFromFats) {
        double
                dailyCaloriesFromProtein = MacroNutrient.PROTEIN.calculateEnergyFromMass(dailyProtein),
                dailyCaloriesFromCarbs = dailyCalories - dailyCaloriesFromProtein - dailyCaloriesFromFats;
        return MacroNutrient.CARB.calculateMassFromEnergy(dailyCaloriesFromCarbs);
    }

    //
    @Override
    public @NotNull NutritionalValue getDailyNutrients() {
        return dailyNutrients;
    }

    //
    @Override
    public void set(@NotNull Diet diet) {
        super.set(diet);
        update();
    }

    //
    @Override
    public void setFatsEnergyRatio(double fatsEnergyRatio) {
        super.setFatsEnergyRatio(fatsEnergyRatio);
        update();
    }

    //
    @Override
    public void setProteinsPerBodyMass(@NotNull Mass proteinsPerBodyMass) {
        super.setProteinsPerBodyMass(proteinsPerBodyMass);
        update();
    }

    //
    @Override
    public void setMicroNutrient(@NotNull MicroNutrient nutrient, @Nullable Mass mass) {
        super.setMicroNutrient(nutrient, mass);
        update();
    }
}
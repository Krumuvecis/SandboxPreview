package food;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
public enum NutrientEnum implements NutrientEnumInterface {
    //macronutrients
    PROTEIN("proteins"),
    FAT("fats"),
    CARB("carbs"),

    //vitamins
    VITAMIN_A("vitamin A"),
    VITAMIN_B1("vitamin B1"),
    VITAMIN_B2("vitamin B2"),
    VITAMIN_B3("vitamin B3"),
    VITAMIN_B5("vitamin B5"),
    VITAMIN_B6("vitamin B6"),
    VITAMIN_B7("vitamin B7"),
    VITAMIN_B9("vitamin B9"),
    VITAMIN_B12("vitamin B12"),
    VITAMIN_C("vitamin C"),
    VITAMIN_D("vitamin D"),
    VITAMIN_E("vitamin E"),
    VITAMIN_K("vitamin K");

    //TODO: minerals
    //TODO: ???

    static {
        //initialize energy densities
        double
                carbsEnergyDensity = 4, // (4kcal ~ 17kJ) / 1g; here kcal/kg
                fatsEnergyDensity = 9; // (9kcal ~ 37kJ) / 1g
        PROTEIN.setEnergyDensity(carbsEnergyDensity);
        FAT.setEnergyDensity(fatsEnergyDensity);
        CARB.setEnergyDensity(carbsEnergyDensity);
    }

    private final @NotNull String name;
    private double energyDensity; // kcal/kg

    //
    NutrientEnum(@NotNull String name) {
        this.name = name;
        energyDensity = 0;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }

    //in kcal/kg
    @Override
    public final double getEnergyDensity() {
        return energyDensity;
    }

    private void setEnergyDensity(double kcalPerGram) {
        this.energyDensity = kcalPerGram / new Mass(1, MassUnit.G).getInBase();
    }
}
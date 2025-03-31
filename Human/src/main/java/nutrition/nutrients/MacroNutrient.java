package nutrition.nutrients;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
public enum MacroNutrient implements MacroNutrientInterface {
    //macronutrients
    PROTEIN("proteins"),
    FAT("fats"),
    CARB("carbs");

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
    MacroNutrient(@NotNull String name) {
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
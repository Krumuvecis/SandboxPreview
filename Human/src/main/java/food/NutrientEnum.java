package food;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
public enum NutrientEnum {
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
    NutrientEnum(@NotNull String name) {
        this.name = name;
        energyDensity = 0;
    }

    //
    public final @NotNull String getName() {
        return name;
    }

    //in kcal/kg
    public final double getEnergyDensity() {
        return energyDensity;
    }

    private void setEnergyDensity(double kcalPerGram) {
        this.energyDensity = kcalPerGram / new Mass(1, MassUnit.G).getInBase();
    }

    //energy in kcal
    public final double calculateEnergyFromMass(@NotNull Mass mass) {
        return energyDensity * mass.getInBase();
    }

    //energy in kcal
    public final @NotNull Mass calculateMassFromEnergy(double energy) {
        return new Mass(energy / energyDensity);
    }
}
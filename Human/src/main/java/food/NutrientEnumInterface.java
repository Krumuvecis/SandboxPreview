package food;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
public interface NutrientEnumInterface {
    //
    @NotNull String getName();

    //kcal/kg
    double getEnergyDensity();

    //energy in kcal
    default double calculateEnergyFromMass(@NotNull Mass mass) {
        return getEnergyDensity() * mass.getInBase();
    }

    //energy in kcal
    default @NotNull Mass calculateMassFromEnergy(double energy) {
        return new Mass(energy / getEnergyDensity());
    }
}
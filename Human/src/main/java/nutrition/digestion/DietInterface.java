package nutrition.digestion;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.MicroNutrientContainer;

//
public interface DietInterface extends MicroNutrientContainer<@NotNull DietInterface> {
    //
    double getFatsEnergyRatio();

    //
    @NotNull Mass getProteinsPerBodyMass();

    //copies values
    void set(@NotNull Diet diet);

    //
    void setFatsEnergyRatio(double fatsEnergyRatio);

    //
    void setProteinsPerBodyMass(@NotNull Mass proteinsPerBodyMass);

    //
    void setMicroNutrient(@NotNull MicroNutrient nutrient, @NotNull Mass mass);
}
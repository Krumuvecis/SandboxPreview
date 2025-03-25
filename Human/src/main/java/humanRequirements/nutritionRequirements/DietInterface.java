package humanRequirements.nutritionRequirements;

import java.util.Map;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import food.NutrientEnum;

//
public interface DietInterface {
    //
    double getFatsEnergyRatio();

    //
    @NotNull Map<NutrientEnum, Mass> getNutrientsPerBodyMass();

    //copies values
    void set(@NotNull Diet diet);

    //
    void setFatsEnergyRatio(double fatsEnergyRatio);

    //
    void setNutrientPerBodyWeight(@NotNull NutrientEnum nutrient, @NotNull Mass mass);
}
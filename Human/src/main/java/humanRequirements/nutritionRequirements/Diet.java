package humanRequirements.nutritionRequirements;

import java.util.Map;
import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import food.NutrientEnum;

//
public class Diet implements DietInterface {
    private double fatsEnergyRatio; //of total calories
    private @NotNull Map<@NotNull NutrientEnum, @NotNull Mass> nutrientsPerBodyMass; //per 1kg of body mass; daily values

    //for custom diets
    public Diet(double fatsEnergyRatio, @NotNull Map<@NotNull NutrientEnum, @NotNull Mass> nutrientsPerBodyMass) {
        this.fatsEnergyRatio = fatsEnergyRatio;
        this.nutrientsPerBodyMass = nutrientsPerBodyMass;
    }

    //for nutrition initialization
    Diet(@NotNull Diet diet) {
        this(diet.getFatsEnergyRatio(), diet.getNutrientsPerBodyMass());
    }

    //
    @Override
    public final double getFatsEnergyRatio() {
        return fatsEnergyRatio;
    }

    //
    @Override
    public @NotNull Map<NutrientEnum, Mass> getNutrientsPerBodyMass() {
        return nutrientsPerBodyMass;
    }

    //copies values
    @Override
    public void set(@NotNull Diet diet) {
        this.fatsEnergyRatio = diet.getFatsEnergyRatio();
        this.nutrientsPerBodyMass = diet.nutrientsPerBodyMass;
    }

    //
    @Override
    public void setFatsEnergyRatio(double fatsEnergyRatio) {
        this.fatsEnergyRatio = fatsEnergyRatio;
    }

    //
    @Override
    public void setNutrientPerBodyWeight(@NotNull NutrientEnum nutrient, @NotNull Mass mass) {
        this.nutrientsPerBodyMass.put(nutrient, mass);
    }

    public static final class LuxuryDiet extends Diet {
        private static final @NotNull Mass DAILY_PROTEIN_PER_BODY_WEIGHT = new Mass(1, MassUnit.G);
        private static final double FATS_ENERGY_RATIO = 0.2;

        //
        public LuxuryDiet() {
            super(FATS_ENERGY_RATIO, new EnumMap<>(NutrientEnum.class) {{
                put(NutrientEnum.PROTEIN, DAILY_PROTEIN_PER_BODY_WEIGHT);
            }});
        }
    }

    public static final class CivilianDiet extends Diet {
        private static final @NotNull Mass DAILY_PROTEIN_PER_BODY_WEIGHT = new Mass(0.8, MassUnit.G);
        private static final double FATS_ENERGY_RATIO = 0.3;

        //
        public CivilianDiet() {
            super(FATS_ENERGY_RATIO, new EnumMap<>(NutrientEnum.class) {{
                put(NutrientEnum.PROTEIN, DAILY_PROTEIN_PER_BODY_WEIGHT);
            }});
        }
    }

    public static final class MilitaryDiet extends Diet {
        private static final @NotNull Mass DAILY_PROTEIN_PER_BODY_WEIGHT = new Mass(1.5, MassUnit.G);
        private static final double FATS_ENERGY_RATIO = 0.4;

        //
        public MilitaryDiet() {
            super(FATS_ENERGY_RATIO, new EnumMap<>(NutrientEnum.class) {{
                put(NutrientEnum.PROTEIN, DAILY_PROTEIN_PER_BODY_WEIGHT);
            }});
        }
    }
}
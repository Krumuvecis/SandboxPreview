package nutrition.digestion;

import java.util.Map;
import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.MicroNutrient;

//
public class Diet implements DietInterface {
    public static final @NotNull EnumMap<@NotNull MicroNutrient, @Nullable Mass>
            STANDARD_MICRONUTRIENTS = new StandardMicronutrients();
    public static final @NotNull Diet
            LUXURY_DIET = new Diet(0.2, 1),
            CIVILIAN_DIET = new Diet(0.3, 0.8),
            MILITARY_DIET = new Diet(0.4, 1.5);
    private double fatsEnergyRatio; //of total calories
    private @NotNull Mass proteinsPerBodyMass; //per 1kg of body mass; daily values
    private @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> microNutrients;

    //for custom diets
    public Diet(double fatsEnergyRatio, @NotNull Mass proteinsPerBodyMass,
                @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> microNutrients) {
        this.fatsEnergyRatio = fatsEnergyRatio;
        this.proteinsPerBodyMass = proteinsPerBodyMass;
        this.microNutrients = microNutrients;
    }

    //for nutrition initialization
    Diet(@NotNull Diet diet) {
        this(diet.getFatsEnergyRatio(), diet.getProteinsPerBodyMass(), diet.getMicroNutrients());
    }

    //for standard diets
    private Diet(double fatsEnergyRatio, double proteinGramsPerBodyMass) {
        this(fatsEnergyRatio, new Mass(proteinGramsPerBodyMass, MassUnit.G), STANDARD_MICRONUTRIENTS);
    }

    //
    @Override
    public final double getFatsEnergyRatio() {
        return fatsEnergyRatio;
    }

    //
    @Override
    public final @NotNull Mass getProteinsPerBodyMass() {
        return proteinsPerBodyMass;
    }

    //
    @Override
    public final @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> getMicroNutrients() {
        return microNutrients;
    }

    //
    @Override
    public final @NotNull DietInterface copy() {
        return new Diet(this);
    }

    //copies values
    @Override
    public void set(@NotNull Diet diet) {
        this.fatsEnergyRatio = diet.getFatsEnergyRatio();
        this.proteinsPerBodyMass = diet.getProteinsPerBodyMass();
        this.microNutrients = diet.getMicroNutrients();
    }

    //
    @Override
    public void setFatsEnergyRatio(double fatsEnergyRatio) {
        this.fatsEnergyRatio = fatsEnergyRatio;
    }

    //
    @Override
    public void setProteinsPerBodyMass(@NotNull Mass proteinsPerBodyMass) {
        this.proteinsPerBodyMass = proteinsPerBodyMass;
    }

    //
    @Override
    public void setMicroNutrient(@NotNull MicroNutrient nutrient, @Nullable Mass mass) {
        this.microNutrients.put(nutrient, mass);
    }

    //
    @Override
    public void sum(@NotNull DietInterface addend) {
        //TODO: finish this
        throw new RuntimeException("not done!");
    }

    //
    @Override
    public void multiply(double multiplier) {
        //TODO: finish this
        throw new RuntimeException("not done!");
    }

    private static final class StandardMicronutrients extends EnumMap<@NotNull MicroNutrient, @Nullable Mass> {
        //male; 19-70
        public StandardMicronutrients() {
            super(MicroNutrient.class);

            put(MicroNutrient.VITAMIN_A, new Mass(900, MassUnit.UG));

            put(MicroNutrient.VITAMIN_B1, new Mass(1.2, MassUnit.MG));
            put(MicroNutrient.VITAMIN_B2, new Mass(1.3, MassUnit.MG));
            put(MicroNutrient.VITAMIN_B3, new Mass(16, MassUnit.MG));
            put(MicroNutrient.VITAMIN_B5, new Mass(5, MassUnit.MG));
            put(MicroNutrient.VITAMIN_B6, new Mass(1.3, MassUnit.MG));
            put(MicroNutrient.VITAMIN_B7, new Mass(30, MassUnit.UG));
            put(MicroNutrient.VITAMIN_B9, new Mass(400, MassUnit.UG));
            put(MicroNutrient.VITAMIN_B12, new Mass(2.4, MassUnit.UG));

            put(MicroNutrient.VITAMIN_C, new Mass(90, MassUnit.MG));
            put(MicroNutrient.VITAMIN_D, new Mass(15, MassUnit.UG));
            put(MicroNutrient.VITAMIN_E, new Mass(15, MassUnit.MG));
            put(MicroNutrient.VITAMIN_K, new Mass(110, MassUnit.UG));
        }
    }
}
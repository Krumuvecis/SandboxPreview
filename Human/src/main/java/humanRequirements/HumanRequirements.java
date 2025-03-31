package humanRequirements;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.time.TimeUnit;
import dimensions.time.Time;
import humanRequirements.rotationalLimits.RotationalLimits;
import humanRequirements.rotationalLimits.particularRotationalLimits.LuxuryRotationalLimits;
import humanRequirements.rotationalLimits.particularRotationalLimits.CivilianRotationalLimits;
import humanRequirements.rotationalLimits.particularRotationalLimits.MilitaryRotationalLimits;
import humanRequirements.aerationRequirements.AerationRequirements;
import humanRequirements.hydrationRequirements.HydrationRequirements;
import humanRequirements.accommodationRequirements.AccommodationRequirements;
import humanRequirements.sanitationRequirements.SanitationRequirements;

//
public class HumanRequirements implements HumanRequirementInterface {
    private final @NotNull RotationalLimits rotationalLimits;
    private final @NotNull AerationRequirements aerationRequirements;
    private final @NotNull HydrationRequirements hydrationRequirements;
    //private final @NotNull NutritionRequirements nutritionRequirements;
    private final @NotNull AccommodationRequirements accommodationRequirements;
    private final @NotNull SanitationRequirements sanitationRequirements;

    //
    public HumanRequirements(@NotNull RotationalLimits rotationalLimits,
                             @NotNull Mass dailyWaterConsumption, //@NotNull NutritionRequirements nutritionRequirements,
                             double privateAreaPerPerson, double commonAreaPerPerson,
                             @NotNull Mass luggagePerPerson, double showerRate) {
        this.rotationalLimits = rotationalLimits;
        aerationRequirements = new AerationRequirements();
        hydrationRequirements = new HydrationRequirements(dailyWaterConsumption);
        //this.nutritionRequirements = nutritionRequirements;
        accommodationRequirements = new AccommodationRequirements(privateAreaPerPerson, commonAreaPerPerson, luggagePerPerson);
        sanitationRequirements = new SanitationRequirements(showerRate);
    }

    //
    @Override
    public @NotNull RotationalLimits getRotationalLimits() {
        return rotationalLimits;
    }

    //
    @Override
    public @NotNull AerationRequirements getAerationRequirements() {
        return aerationRequirements;
    }

    //
    @Override
    public @NotNull HydrationRequirements getHydrationRequirements() {
        return hydrationRequirements;
    }

    //
    /*@Override
    public @NotNull NutritionRequirements getNutritionRequirements() {
        return nutritionRequirements;
    }*/

    //
    @Override
    public @NotNull AccommodationRequirements getAccommodationRequirements() {
        return accommodationRequirements;
    }

    //
    @Override
    public @NotNull SanitationRequirements getSanitationRequirements() {
        return sanitationRequirements;
    }

    //
    public static class LuxuryRequirements extends HumanRequirements {
        private static final @NotNull RotationalLimits REFERENCE_ROTATIONAL_LIMITS = new LuxuryRotationalLimits();
        private static final @NotNull Mass REFERENCE_DAILY_WATER_CONSUMPTION = new Mass(20);
        private static final double
                REFERENCE_PRIVATE_AREA_PER_PERSON = 50,
                REFERENCE_COMMON_AREA_PER_PERSON = 50;
        private static final @NotNull Mass REFERENCE_LUGGAGE_AMOUNT = new Mass(10, MassUnit.T);
        private static final double REFERENCE_SHOWER_RATE = 1 / new Time(1, TimeUnit.DAY).getInBase();

        //
        public LuxuryRequirements() {
            super(REFERENCE_ROTATIONAL_LIMITS,
                    REFERENCE_DAILY_WATER_CONSUMPTION,// new NutritionRequirements.LuxuryNutritionRequirements(),
                    REFERENCE_PRIVATE_AREA_PER_PERSON, REFERENCE_COMMON_AREA_PER_PERSON, REFERENCE_LUGGAGE_AMOUNT,
                    REFERENCE_SHOWER_RATE);
        }
    }

    //
    public static class CivilianRequirements extends HumanRequirements {
        private static final @NotNull RotationalLimits REFERENCE_ROTATIONAL_LIMITS = new CivilianRotationalLimits();
        private static final @NotNull Mass REFERENCE_DAILY_WATER_CONSUMPTION = new Mass(10);
        private static final double
                REFERENCE_PRIVATE_AREA_PER_PERSON = 30,
                REFERENCE_COMMON_AREA_PER_PERSON = 20;
        private static final @NotNull Mass REFERENCE_LUGGAGE_AMOUNT = new Mass(2, MassUnit.T);
        private static final double REFERENCE_SHOWER_RATE = 2 / new Time(1, TimeUnit.WEEK).getInBase();

        //
        public CivilianRequirements() {
            super(REFERENCE_ROTATIONAL_LIMITS,
                    REFERENCE_DAILY_WATER_CONSUMPTION,// new NutritionRequirements.CivilianNutritionRequirements(),
                    REFERENCE_PRIVATE_AREA_PER_PERSON, REFERENCE_COMMON_AREA_PER_PERSON, REFERENCE_LUGGAGE_AMOUNT,
                    REFERENCE_SHOWER_RATE);
        }
    }

    //
    public static class MilitaryRequirements extends HumanRequirements {
        private static final @NotNull RotationalLimits REFERENCE_ROTATIONAL_LIMITS = new MilitaryRotationalLimits();
        private static final @NotNull Mass REFERENCE_DAILY_WATER_CONSUMPTION = new Mass(10);
        private static final double
                REFERENCE_PRIVATE_AREA_PER_PERSON = 10,
                REFERENCE_COMMON_AREA_PER_PERSON = 20;
        private static final @NotNull Mass REFERENCE_LUGGAGE_AMOUNT = new Mass(1, MassUnit.T);
        private static final double REFERENCE_SHOWER_RATE = 1 / new Time(1, TimeUnit.DAY).getInBase();

        //
        public MilitaryRequirements() {
            super(REFERENCE_ROTATIONAL_LIMITS,
                    REFERENCE_DAILY_WATER_CONSUMPTION,// new NutritionRequirements.MilitaryNutritionRequirements(),
                    REFERENCE_PRIVATE_AREA_PER_PERSON, REFERENCE_COMMON_AREA_PER_PERSON, REFERENCE_LUGGAGE_AMOUNT,
                    REFERENCE_SHOWER_RATE);
        }
    }
}
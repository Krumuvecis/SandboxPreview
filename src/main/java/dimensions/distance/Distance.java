package dimensions.distance;

import java.util.Collections;
import java.util.Map;

import dimensions.ConversionRatiosInitializer;
import dimensions.DimensionalValue;

//
public final class Distance extends DimensionalValue<DistanceUnit> implements ConversionRatiosInitializer<DistanceUnit> {
    private static final double
            KM_TO_M = 1000,
            AU_TO_KM = 149597870.7,
            LY_TO_KM = 9460730472580.8,
            PC_TO_LY = 3.26;
    private static final Map<DistanceUnit, Map<DistanceUnit, Double>> CONVERSION_RATIOS;

    static {
        Map<DistanceUnit, Map<DistanceUnit, Double>>
                ratios = ConversionRatiosInitializer.initializeConversionMap((DistanceUnit.values()));
        new Distance().populateConversionRatios(ratios);
        CONVERSION_RATIOS = Collections.unmodifiableMap(ratios);
    }

    //
    public Distance(double value, DistanceUnit unit) {
        super("Distance", value, unit, CONVERSION_RATIOS);
    }

    //for conversion ratio initialization
    private Distance() {
        this(0, null);
    }

    //
    @Override
    public void populateConversionRatios(Map<DistanceUnit, Map<DistanceUnit, Double>> ratios) {
        //km
        addConversions(ratios, DistanceUnit.KM, DistanceUnit.M, KM_TO_M);

        //au
        addConversions(ratios, DistanceUnit.AU, DistanceUnit.KM, AU_TO_KM);
        addConversions(ratios, DistanceUnit.AU, DistanceUnit.M, AU_TO_KM * KM_TO_M);

        //ly
        addConversions(ratios, DistanceUnit.LY, DistanceUnit.KM, LY_TO_KM);
        addConversions(ratios, DistanceUnit.LY, DistanceUnit.AU, LY_TO_KM / AU_TO_KM);
        addConversions(ratios, DistanceUnit.LY, DistanceUnit.M, LY_TO_KM * KM_TO_M);

        //pc
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.LY, PC_TO_LY);
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.KM, PC_TO_LY * LY_TO_KM);
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.AU, PC_TO_LY * LY_TO_KM / AU_TO_KM);
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.M, PC_TO_LY * LY_TO_KM * KM_TO_M);
    }
}
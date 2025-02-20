package dimensions.mass;

import java.util.Collections;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.ConversionRatiosInitializer;
import dimensions.DimensionalValue;

//
public final class Mass extends DimensionalValue<@NotNull MassUnit, @NotNull Mass>
        implements ConversionRatiosInitializer<@NotNull MassUnit> {
    private static final @NotNull MassUnit SI_UNIT = MassUnit.KG;
    private static final double
            KG_TO_G = 1000,
            T_TO_KG = 1000,
            EARTH_MASS_TO_KG = 5.972 * Math.pow(10, 24),
            JUPITER_MASS_TO_KG = 1.899 * Math.pow(10, 27),
            SOLAR_MASS_TO_KG = 1.9855 * Math.pow(10, 30);
    private static final @NotNull Map<@NotNull MassUnit, @NotNull Map<@NotNull MassUnit, @NotNull Double>>
            CONVERSION_RATIOS;

    static {
        @NotNull Map<@NotNull MassUnit, @NotNull Map<@NotNull MassUnit, @NotNull Double>>
                ratios = ConversionRatiosInitializer.initializeConversionMap(MassUnit.values());
        new Mass().populateConversionRatios(ratios);
        CONVERSION_RATIOS = Collections.unmodifiableMap(ratios);
    }

    //custom units, null - SI
    public Mass(double value, @Nullable MassUnit unit) {
        super("Mass", SI_UNIT, value, unit, CONVERSION_RATIOS);
    }

    //default units, SI
    public Mass(double value) {
        this(value, null);
    }

    //for conversion ratio initialization
    private Mass() {
        this(0);
    }

    //
    @Override
    public void populateConversionRatios(
            @NotNull Map<@NotNull MassUnit, @NotNull Map<@NotNull MassUnit, @NotNull Double>> ratios) {
        //kg
        addConversions(ratios, MassUnit.KG, MassUnit.G, KG_TO_G);

        //tonne
        addConversions(ratios, MassUnit.T, MassUnit.KG, T_TO_KG);
        addConversions(ratios, MassUnit.T, MassUnit.G, T_TO_KG * KG_TO_G);

        //earth mass
        addConversions(ratios, MassUnit.EARTH_MASS, MassUnit.KG, EARTH_MASS_TO_KG);
        addConversions(ratios, MassUnit.EARTH_MASS, MassUnit.T, EARTH_MASS_TO_KG / T_TO_KG);
        addConversions(ratios, MassUnit.EARTH_MASS, MassUnit.G, EARTH_MASS_TO_KG * KG_TO_G);

        //jupiter mass
        addConversions(ratios, MassUnit.JUPITER_MASS, MassUnit.KG, JUPITER_MASS_TO_KG);
        addConversions(ratios, MassUnit.JUPITER_MASS, MassUnit.EARTH_MASS, JUPITER_MASS_TO_KG / EARTH_MASS_TO_KG);
        addConversions(ratios, MassUnit.JUPITER_MASS, MassUnit.T, JUPITER_MASS_TO_KG / T_TO_KG);
        addConversions(ratios, MassUnit.JUPITER_MASS, MassUnit.G, JUPITER_MASS_TO_KG * KG_TO_G);

        //solar mass
        addConversions(ratios, MassUnit.SOLAR_MASS, MassUnit.KG, SOLAR_MASS_TO_KG);
        addConversions(ratios, MassUnit.SOLAR_MASS, MassUnit.JUPITER_MASS, SOLAR_MASS_TO_KG / JUPITER_MASS_TO_KG);
        addConversions(ratios, MassUnit.SOLAR_MASS, MassUnit.EARTH_MASS, SOLAR_MASS_TO_KG / EARTH_MASS_TO_KG);
        addConversions(ratios, MassUnit.SOLAR_MASS, MassUnit.T, SOLAR_MASS_TO_KG / T_TO_KG);
        addConversions(ratios, MassUnit.SOLAR_MASS, MassUnit.G, SOLAR_MASS_TO_KG * KG_TO_G);
    }

    //
    @Override
    public @NotNull Mass copy() {
        return new Mass(getValue(), getUnit());
    }
}
package dimensions.mass;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.DimensionName;
import dimensions.ConversionManager;
import dimensions.DimensionalValue;

//TODO: add docs
@SuppressWarnings("MissingJavadoc")
public final class Mass extends DimensionalValue<@NotNull MassUnit, @NotNull Mass> {
    private static final @NotNull DimensionName
            DIMENSION_NAME = new DimensionName("Mass", "mass");
    private static final @NotNull MassUnit BASE_UNIT = MassUnit.KG;
    private static final @NotNull MassConversion CONVERSION = new MassConversion(DIMENSION_NAME, BASE_UNIT);

    //custom units, null - base
    public Mass(double value, @Nullable MassUnit unit) {
        super(DIMENSION_NAME, CONVERSION, value, unit);
    }

    //base units
    public Mass(double value) {
        this(value, null);
    }

    //preserves units
    @Override
    public @NotNull Mass copy() {
        return new Mass(getValue(), getUnit());
    }

    //
    private static final class MassConversion extends ConversionManager<@NotNull MassUnit> {
        private static final double
                KG_TO_G = 1000,
                T_TO_KG = 1000,
                EARTH_MASS_TO_KG = 5.972 * Math.pow(10, 24),
                JUPITER_MASS_TO_KG = 1.899 * Math.pow(10, 27),
                SOLAR_MASS_TO_KG = 1.9855 * Math.pow(10, 30);

        //
        MassConversion(@NotNull DimensionName dimensionName, @NotNull MassUnit baseUnit) {
            super(dimensionName, baseUnit);
        }

        //
        @Override
        public @NotNull List<@NotNull ConversionRatioTemplate<@NotNull MassUnit>> getRatioTemplates() {
            return new ArrayList<>() {{
                add(new ConversionRatioTemplate<>(MassUnit.KG, MassUnit.G, KG_TO_G));
                add(new ConversionRatioTemplate<>(MassUnit.T, MassUnit.KG, T_TO_KG));
                add(new ConversionRatioTemplate<>(MassUnit.EARTH_MASS, MassUnit.KG, EARTH_MASS_TO_KG));
                add(new ConversionRatioTemplate<>(MassUnit.JUPITER_MASS, MassUnit.KG, JUPITER_MASS_TO_KG));
                add(new ConversionRatioTemplate<>(MassUnit.SOLAR_MASS, MassUnit.KG, SOLAR_MASS_TO_KG));
            }};
        }
    }
}
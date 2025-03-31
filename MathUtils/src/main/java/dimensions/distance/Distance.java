package dimensions.distance;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.DimensionName;
import dimensions.conversion.ConversionRatioTemplate;
import dimensions.conversion.ConversionManager;
import dimensions.DimensionalValue;

//TODO: add docs
@SuppressWarnings("MissingJavadoc")
public final class Distance extends DimensionalValue<@NotNull DistanceUnit, @NotNull Distance> {
    private static final @NotNull DimensionName
            DIMENSION_NAME = new DimensionName("Distance", "distance");
    private static final @NotNull DistanceUnit BASE_UNIT = DistanceUnit.M;
    private static final @NotNull DistanceConversion CONVERSION = new DistanceConversion(DIMENSION_NAME, BASE_UNIT);

    //custom units, null - base
    public Distance(double value, @Nullable DistanceUnit unit) {
        super(DIMENSION_NAME, CONVERSION, value, unit);
    }

    //base units
    public Distance(double value) {
        this(value, null);
    }

    //preserves units
    @Override
    public @NotNull Distance copy() {
        return new Distance(getValue(), getUnit());
    }

    //
    private static final class DistanceConversion extends ConversionManager<@NotNull DistanceUnit> {
        private static final double
                KM_TO_M = 1000,
                AU_TO_KM = 149597870.7,
                LY_TO_KM = 9460730472580.8,
                PC_TO_LY = 3.26;

        //
        DistanceConversion(@NotNull DimensionName dimensionName, @NotNull DistanceUnit baseUnit) {
            super(dimensionName, baseUnit);
        }

        //
        @Override
        public @NotNull List<@NotNull ConversionRatioTemplate<@NotNull DistanceUnit>> getRatioTemplates() {
            return new ArrayList<>() {{
                add(new ConversionRatioTemplate<>(DistanceUnit.KM, DistanceUnit.M, KM_TO_M));
                add(new ConversionRatioTemplate<>(DistanceUnit.AU, DistanceUnit.KM, AU_TO_KM));
                add(new ConversionRatioTemplate<>(DistanceUnit.LY, DistanceUnit.KM, LY_TO_KM));
                add(new ConversionRatioTemplate<>(DistanceUnit.PC, DistanceUnit.LY, PC_TO_LY));
            }};
        }
    }
}
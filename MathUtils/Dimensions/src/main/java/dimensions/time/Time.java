package dimensions.time;

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
public final class Time extends DimensionalValue<@NotNull TimeUnit, @NotNull Time> {
    private static final @NotNull DimensionName
            DIMENSION_NAME = new DimensionName("Time", "time");
    private static final @NotNull TimeUnit BASE_UNIT = TimeUnit.S;
    private static final @NotNull TimeConversion CONVERSION = new TimeConversion(DIMENSION_NAME, BASE_UNIT);

    //custom units, null - base
    public Time(double value, @Nullable TimeUnit unit) {
        super(DIMENSION_NAME, CONVERSION, value, unit);
    }

    //base units
    public Time(double value) {
        this(value, null);
    }

    //preserves units
    @Override
    public @NotNull Time copy() {
        return new Time(getValue(), getUnit());
    }

    //
    private static final class TimeConversion extends ConversionManager<@NotNull TimeUnit> {
        private static final double
                S_TO_MS = 1000,
                MIN_TO_S = 60,
                H_TO_MIN = 60,
                DAY_TO_H = 24,
                WEEK_TO_DAY = 7,
                YEAR_TO_MONTH = 12,
                YEAR_TO_DAY = 365.25;

        //
        TimeConversion(@NotNull DimensionName dimensionName, @NotNull TimeUnit baseUnit) {
            super(dimensionName, baseUnit);
        }

        //
        @Override
        public @NotNull List<@NotNull ConversionRatioTemplate<@NotNull TimeUnit>> getRatioTemplates() {
            return new ArrayList<>() {{
                add(new ConversionRatioTemplate<>(TimeUnit.S, TimeUnit.MS, S_TO_MS));
                add(new ConversionRatioTemplate<>(TimeUnit.MIN, TimeUnit.S, MIN_TO_S));
                add(new ConversionRatioTemplate<>(TimeUnit.H, TimeUnit.MIN, H_TO_MIN));
                add(new ConversionRatioTemplate<>(TimeUnit.DAY, TimeUnit.H, DAY_TO_H));
                add(new ConversionRatioTemplate<>(TimeUnit.WEEK, TimeUnit.DAY, WEEK_TO_DAY));
                add(new ConversionRatioTemplate<>(TimeUnit.YEAR, TimeUnit.MONTH, YEAR_TO_MONTH));
                add(new ConversionRatioTemplate<>(TimeUnit.YEAR, TimeUnit.DAY, YEAR_TO_DAY));
            }};
        }
    }
}
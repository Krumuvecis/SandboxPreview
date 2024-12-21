package dimensions.time;

import java.util.Collections;
import java.util.Map;

import org.jetbrains.annotations.NotNull;

import dimensions.ConversionRatiosInitializer;
import dimensions.DimensionalValue;

//
public final class Time extends DimensionalValue<TimeUnit> implements ConversionRatiosInitializer<TimeUnit> {
    private static final @NotNull TimeUnit SI_UNIT = TimeUnit.S;
    private static final double
            S_TO_MS = 1000,
            MIN_TO_S = 60,
            H_TO_MIN = 60,
            DAY_TO_H = 24,
            WEEK_TO_DAY = 7,
            YEAR_TO_MONTH = 12,
            YEAR_TO_DAY = 365.25;
    private static final Map<TimeUnit, Map<TimeUnit, Double>> CONVERSION_RATIOS;

    static {
        Map<TimeUnit, Map<TimeUnit, Double>>
                ratios = ConversionRatiosInitializer.initializeConversionMap(TimeUnit.values());
        new Time().populateConversionRatios(ratios);
        CONVERSION_RATIOS = Collections.unmodifiableMap(ratios);
    }

    //custom units
    public Time(double value, TimeUnit unit) {
        super("Time", SI_UNIT, value, unit, CONVERSION_RATIOS);
    }

    //default units
    public Time(double value) {
        this(value, SI_UNIT);
    }

    //for conversion ratio initialization
    private Time() {
        this(0, null);
    }

    //
    @Override
    public void populateConversionRatios(Map<TimeUnit, Map<TimeUnit, Double>> ratios) {
        //seconds
        addConversions(ratios, TimeUnit.S, TimeUnit.MS, S_TO_MS);

        //minutes
        addConversions(ratios, TimeUnit.MIN, TimeUnit.MS, MIN_TO_S * S_TO_MS);
        addConversions(ratios, TimeUnit.MIN, TimeUnit.S, MIN_TO_S);

        //hours
        addConversions(ratios, TimeUnit.H, TimeUnit.MS, H_TO_MIN * MIN_TO_S * S_TO_MS);
        addConversions(ratios, TimeUnit.H, TimeUnit.S, H_TO_MIN * MIN_TO_S);
        addConversions(ratios, TimeUnit.H, TimeUnit.MIN, H_TO_MIN);

        //days
        addConversions(ratios, TimeUnit.DAY, TimeUnit.MS, DAY_TO_H * H_TO_MIN * MIN_TO_S * S_TO_MS);
        addConversions(ratios, TimeUnit.DAY, TimeUnit.S, DAY_TO_H * H_TO_MIN * MIN_TO_S);
        addConversions(ratios, TimeUnit.DAY, TimeUnit.MIN, DAY_TO_H * H_TO_MIN);
        addConversions(ratios, TimeUnit.DAY, TimeUnit.H, DAY_TO_H);

        //weeks
        addConversions(ratios, TimeUnit.WEEK, TimeUnit.MS, WEEK_TO_DAY * DAY_TO_H * H_TO_MIN * MIN_TO_S * S_TO_MS);
        addConversions(ratios, TimeUnit.WEEK, TimeUnit.S, WEEK_TO_DAY * DAY_TO_H * H_TO_MIN * MIN_TO_S);
        addConversions(ratios, TimeUnit.WEEK, TimeUnit.MIN, WEEK_TO_DAY * DAY_TO_H * H_TO_MIN);
        addConversions(ratios, TimeUnit.WEEK, TimeUnit.H, WEEK_TO_DAY * DAY_TO_H);
        addConversions(ratios, TimeUnit.WEEK, TimeUnit.DAY, WEEK_TO_DAY);

        //months
        addConversions(ratios, TimeUnit.MONTH, TimeUnit.MS, YEAR_TO_DAY * DAY_TO_H * H_TO_MIN * MIN_TO_S * S_TO_MS / YEAR_TO_MONTH);
        addConversions(ratios, TimeUnit.MONTH, TimeUnit.S, YEAR_TO_DAY * DAY_TO_H * H_TO_MIN * MIN_TO_S / YEAR_TO_MONTH);
        addConversions(ratios, TimeUnit.MONTH, TimeUnit.MIN, YEAR_TO_DAY * DAY_TO_H * H_TO_MIN / YEAR_TO_MONTH);
        addConversions(ratios, TimeUnit.MONTH, TimeUnit.H, YEAR_TO_DAY * DAY_TO_H / YEAR_TO_MONTH);
        addConversions(ratios, TimeUnit.MONTH, TimeUnit.DAY, YEAR_TO_DAY / YEAR_TO_MONTH);
        addConversions(ratios, TimeUnit.MONTH, TimeUnit.WEEK, YEAR_TO_DAY / YEAR_TO_MONTH / WEEK_TO_DAY);

        //years
        addConversions(ratios, TimeUnit.YEAR, TimeUnit.MS, YEAR_TO_DAY * DAY_TO_H * H_TO_MIN * MIN_TO_S * S_TO_MS);
        addConversions(ratios, TimeUnit.YEAR, TimeUnit.S, YEAR_TO_DAY * DAY_TO_H * H_TO_MIN * MIN_TO_S);
        addConversions(ratios, TimeUnit.YEAR, TimeUnit.MIN, YEAR_TO_DAY * DAY_TO_H * H_TO_MIN);
        addConversions(ratios, TimeUnit.YEAR, TimeUnit.H, YEAR_TO_DAY * DAY_TO_H);
        addConversions(ratios, TimeUnit.YEAR, TimeUnit.DAY, YEAR_TO_DAY);
        addConversions(ratios, TimeUnit.YEAR, TimeUnit.WEEK, YEAR_TO_DAY / WEEK_TO_DAY);
        addConversions(ratios, TimeUnit.YEAR, TimeUnit.MONTH, YEAR_TO_MONTH);
    }
}
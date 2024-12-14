package dimensions.time;

import dimensions.DimensionalUnit;

//Units of time.
public enum TimeUnit implements DimensionalUnit {
    MS("ms", "milliseconds"),
    S("s", "seconds"),
    MIN("min", "minutes"),
    H("h", "hours"),
    DAY("d", "days"),
    WEEK("w", "weeks"),
    MONTH("m", "months"),
    YEAR("y", "years");

    private final String
            shortName,
            longName;

    //
    TimeUnit(String shortName, String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    //
    @Override
    public final String getShortName() {
        return shortName;
    }

    //
    @Override
    public final String getLongName() {
        return longName;
    }
}
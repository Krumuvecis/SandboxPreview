package dimensions.time;

import org.jetbrains.annotations.NotNull;

import dimensions.DimensionalUnit;

//Units of time. TODO: add docs
@SuppressWarnings("MissingJavadoc")
public enum TimeUnit implements DimensionalUnit {
    MS("ms", "milliseconds"),
    S("s", "seconds"),
    MIN("min", "minutes"),
    H("h", "hours"),
    DAY("d", "days"),
    WEEK("w", "weeks"),
    MONTH("m", "months"),
    YEAR("y", "years");

    private final @NotNull String
            shortName,
            longName;

    //
    TimeUnit(@NotNull String shortName, @NotNull String longName) {
        this.shortName = shortName;
        this.longName = longName;
    }

    //
    @Override
    public final @NotNull String getShortName() {
        return shortName;
    }

    //
    @Override
    public final @NotNull String getLongName() {
        return longName;
    }
}
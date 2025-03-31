package dimensions.distance;

import org.jetbrains.annotations.NotNull;

import dimensions.DimensionalUnit;

//Units of distance. TODO: add docs
@SuppressWarnings("MissingJavadoc")
public enum DistanceUnit implements DimensionalUnit {
    M("m", "meters"),
    KM("km", "kilometers"),
    AU("au", "astronomical units"),
    LY("ly", "light years"),
    PC("pc", "parsecs");

    private final @NotNull String
            shortName,
            longName;

    //
    DistanceUnit(@NotNull String shortName, @NotNull String longName) {
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
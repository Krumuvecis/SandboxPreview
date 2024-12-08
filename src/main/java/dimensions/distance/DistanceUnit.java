package dimensions.distance;

import dimensions.DimensionalUnit;

//Units of distance.
public enum DistanceUnit implements DimensionalUnit {
    M("m", "meters"),
    KM("km", "kilometers"),
    AU("au", "astronomical units"),
    LY("ly", "light years"),
    PC("pc", "parsecs");

    private final String
            shortName,
            longName;

    //
    DistanceUnit(String shortName, String longName) {
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
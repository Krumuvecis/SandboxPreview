package dimensions.mass;

import dimensions.DimensionalUnit;

//Units of mass.
public enum MassUnit implements DimensionalUnit {
    G("g", "grams"),
    KG("kg", "kilograms"),
    T("t", "tonnes"),
    EARTH_MASS("Earth's mass", "Earth's masses"),
    JUPITER_MASS("Jupiter's mass", "Jupiter's masses"),
    SOLAR_MASS("Sun's mass", "Sun's masses");

    private final String
            shortName,
            longName;

    //
    MassUnit(String shortName, String longName) {
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
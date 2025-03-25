package dimensions.mass;

import org.jetbrains.annotations.NotNull;

import dimensions.DimensionalUnit;

//Units of mass. TODO: add docs
@SuppressWarnings("MissingJavadoc")
public enum MassUnit implements DimensionalUnit {
    G("g", "grams"),
    TEASPOON("tsp", "teaspoons"),
    TABLESPOON("tbsp", "tablespoons"),
    KG("kg", "kilograms"),
    T("t", "tonnes"),
    EARTH_MASS("Earth's mass", "Earth's masses"),
    JUPITER_MASS("Jupiter's mass", "Jupiter's masses"),
    SOLAR_MASS("Sun's mass", "Sun's masses");

    private final @NotNull String
            shortName,
            longName;

    //
    MassUnit(@NotNull String shortName, @NotNull String longName) {
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
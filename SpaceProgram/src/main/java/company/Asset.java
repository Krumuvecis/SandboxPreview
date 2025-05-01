package company;

import org.jetbrains.annotations.NotNull;

//
public abstract class Asset {
    private final @NotNull String shortName;

    //
    public Asset(@NotNull String shortName) {
        this.shortName = shortName;
    }

    //
    public @NotNull String getShortName() {
        return shortName;
    }

    //
    public abstract @NotNull String getLongName();
}
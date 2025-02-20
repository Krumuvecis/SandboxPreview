package dimensions;

import org.jetbrains.annotations.NotNull;

//
public interface DimensionalUnit {
    //
    @NotNull String getShortName();

    //
    @NotNull String getLongName();
}
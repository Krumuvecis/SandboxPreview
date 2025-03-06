package dimensions;

import org.jetbrains.annotations.NotNull;

//TODO: add docs
@SuppressWarnings("MissingJavadoc")
public interface DimensionalUnit {
    //
    @NotNull String getShortName();

    //
    @NotNull String getLongName();
}
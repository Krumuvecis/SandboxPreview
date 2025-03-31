package dimensions;

import org.jetbrains.annotations.NotNull;

//TODO: add javadoc
@SuppressWarnings({"ClassCanBeRecord", "MissingJavadoc"})
public final class DimensionName {
    private final @NotNull String nameUppercase, nameLowercase;

    //
    public DimensionName(@NotNull String nameUppercase, @NotNull String nameLowercase) {
        this.nameUppercase = nameUppercase;
        this.nameLowercase = nameLowercase;
    }

    //
    public @NotNull String getNameUppercase() {
        return nameUppercase;
    }

    //
    public @NotNull String getNameLowercase() {
        return nameLowercase;
    }
}
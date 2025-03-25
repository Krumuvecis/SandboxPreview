package dimensions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public interface DimensionalValueInterface<T extends @NotNull Enum<T> & DimensionalUnit> {
    //
    double getValue();

    //
    T getUnit();

    //
    T getBaseUnit();

    //For unit conversion. Null unit - base
    double get(@Nullable T unit);

    //base units
    default double getInBase() {
        return get(getBaseUnit());
    }
}
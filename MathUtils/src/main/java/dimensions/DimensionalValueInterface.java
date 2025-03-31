package dimensions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import operands.Operable;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public interface DimensionalValueInterface<
            T extends @NotNull Enum<T> & DimensionalUnit,
            U extends @NotNull DimensionalValueInterface<T, U>>
        extends Operable<U> {
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

    //
    void convert(T unit);
}
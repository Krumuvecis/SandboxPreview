package dimensions;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public interface DisplayableDimensionalValue<T extends @NotNull Enum<T> & DimensionalUnit>
        extends DimensionalValueInterface<T> {
    //for output purposes, default unit, unrounded
    default @NotNull String getValueAndShortUnit() {
        return getValueAndShortUnit(getUnit());
    }

    //for output purposes, custom unit, unrounded; null unit - SI
    default @NotNull String getValueAndShortUnit(@Nullable T unit) {
        T nonNullUnit = getNonNullUnit(unit);
        @NotNull String valueString = String.valueOf(get(nonNullUnit));
        return getValueAndShortUnitString(valueString, nonNullUnit);
    }

    //for output purposes, default unit with rounding
    default @NotNull String getValueAndShortUnit(int decimalPlaces) {
        return getValueAndShortUnit(getUnit(), decimalPlaces);
    }

    //for output purposes, custom unit with rounding; null unit - SI
    default @NotNull String getValueAndShortUnit(@Nullable T unit, int decimalPlaces) {
        T nonNullUnit = getNonNullUnit(unit);
        @NotNull String valueString = doubleToString(get(nonNullUnit), decimalPlaces);
        return getValueAndShortUnitString(valueString, nonNullUnit);
    }

    private T getNonNullUnit(@Nullable T unit) {
        return Objects.requireNonNullElse(unit, getBaseUnit());
    }

    private @NotNull String getValueAndShortUnitString(@NotNull String valueString, T unit) {
        return valueString + " " + unit.getShortName();
    }
}
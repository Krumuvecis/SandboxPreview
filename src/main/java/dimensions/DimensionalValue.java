package dimensions;

import java.util.Objects;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import utils.Copyable;

//
public abstract class DimensionalValue<
            T extends @NotNull Enum<T> & @NotNull DimensionalUnit,
            U extends @NotNull DimensionalValue<T, U>>
        implements Copyable<U> {
    private final @NotNull String dimensionName;
    private final @NotNull T unitSI;
    private final double value;
    private final @NotNull T unit;
    private final @NotNull Map<@NotNull T, @NotNull Map<@NotNull T, @NotNull Double>> conversionRatios;

    //
    public static <T extends @NotNull Enum<@NotNull T>> void addConversions(
            @NotNull Map<@NotNull T, @NotNull Map<@NotNull T, @NotNull Double>> ratios,
            @NotNull T unit1, @NotNull T unit2, double ratio) {
        ratios.get(unit1).put(unit2, ratio);
        ratios.get(unit2).put(unit1, 1 / ratio);
    }

    //null unit - SI
    public DimensionalValue(@NotNull String dimensionName, @NotNull T unitSI,
                            double value, @Nullable T unit,
                            @Nullable Map<@NotNull T, @NotNull Map<@NotNull T, @NotNull Double>> conversionRatios) {
        this.dimensionName = dimensionName;
        this.unitSI = unitSI;
        this.value = value;
        this.unit = Objects.requireNonNullElse(unit, unitSI);
        this.conversionRatios = Objects.requireNonNullElse(conversionRatios, new HashMap<>());
    }

    //
    public final @NotNull String getDimensionName() {
        return dimensionName;
    }

    //
    public final @NotNull T getUnitSI() {
        return unitSI;
    }

    //
    public final double getValue() {
        return value;
    }

    //
    public final @NotNull T getUnit() {
        return unit;
    }

    //For unit conversion. Null unit - SI
    public final double get(@Nullable T unit) {
        @NotNull T nonNullTargetUnit = Objects.requireNonNullElse(unit, getUnitSI());
        if (this.unit == nonNullTargetUnit) {
            return this.value;
        }
        @NotNull Double conversionRatio = conversionRatios.get(this.unit).get(nonNullTargetUnit);
        if (conversionRatio == null) {
            throw new ConversionNotDefinedException(dimensionName, this.unit, nonNullTargetUnit);
        }
        return this.value * conversionRatio;
    }

    //
    public final double getSI() {
        return get(unitSI);
    }

    //for output purposes, default unit, unrounded
    public final @NotNull String getValueAndShortUnit() {
        return getValueAndShortUnit(getUnit());
    }

    //for output purposes, custom unit, unrounded; null unit - SI
    public final @NotNull String getValueAndShortUnit(@Nullable T unit) {
        @NotNull T nonNullUnit = Objects.requireNonNullElse(unit, getUnitSI());
        @NotNull String valueString = String.valueOf(get(nonNullUnit));
        return getValueAndShortUnitString(valueString, nonNullUnit);
    }

    //for output purposes, default unit with rounding
    public final @NotNull String getValueAndShortUnit(int decimalPlaces) {
        return getValueAndShortUnit(getUnit(), decimalPlaces);
    }

    //for output purposes, custom unit with rounding; null unit - SI
    public final @NotNull String getValueAndShortUnit(@Nullable T unit, int decimalPlaces) {
        @NotNull T nonNullUnit = Objects.requireNonNullElse(unit, getUnitSI());
        @NotNull String valueString = doubleToString(get(nonNullUnit), decimalPlaces);
        return getValueAndShortUnitString(valueString, nonNullUnit);
    }

    private @NotNull String getValueAndShortUnitString(@NotNull String valueString, @NotNull T unit) {
        return valueString + " " + unit.getShortName();
    }

    private static class ConversionNotDefinedException extends RuntimeException {
        //
        ConversionNotDefinedException(String dimensionString, DimensionalUnit unit1, DimensionalUnit unit2) {
            super(dimensionString + " conversion from " + unit1.getLongName() + " to " + unit2.getLongName() + " not defined.");
        }
    }
}
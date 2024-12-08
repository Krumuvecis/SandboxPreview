package dimensions;

import java.util.Map;

//
public abstract class DimensionalValue<T extends Enum<T> & DimensionalUnit> {
    private final String dimensionName;
    private final double value;
    private final T unit;
    private final Map<T, Map<T, Double>> conversionRatios;

    //
    public static <T extends Enum<T>> void addConversions(Map<T, Map<T, Double>> ratios,
                                                          T unit1, T unit2, double ratio) {
        ratios.get(unit1).put(unit2, ratio);
        ratios.get(unit2).put(unit1, 1 / ratio);
    }

    //
    public DimensionalValue(String dimensionName,
                            double value, T unit,
                            Map<T, Map<T, Double>> conversionRatios) {
        this.dimensionName = dimensionName;
        this.value = value;
        this.unit = unit;
        this.conversionRatios = conversionRatios;
    }

    //
    public final String getDimensionName() {
        return dimensionName;
    }

    //
    public final double getValue() {
        return value;
    }

    //
    public final T getUnit() {
        return unit;
    }

    //For unit conversion.
    public final double get(T unit) {
        if (this.unit == unit) {
            return this.value;
        }
        Double conversionRatio = conversionRatios.get(this.unit).get(unit);
        if (conversionRatio == null) {
            throw new ConversionNotDefinedException(dimensionName, this.unit, unit);
        }
        return this.value * conversionRatio;
    }

    //for output purposes, custom unit
    public final String getValueAndShortUnit(T unit) {
        return get(unit) + " " + unit.getShortName();
    }

    //for output purposes, default unit
    public final String getValueAndShortUnit() {
        return getValueAndShortUnit(getUnit());
    }

    private static class ConversionNotDefinedException extends RuntimeException {
        //
        ConversionNotDefinedException(String dimensionString, DimensionalUnit unit1, DimensionalUnit unit2) {
            super(dimensionString + " conversion from " + unit1.getLongName() + " to " + unit2.getLongName() + " not defined.");
        }
    }
}
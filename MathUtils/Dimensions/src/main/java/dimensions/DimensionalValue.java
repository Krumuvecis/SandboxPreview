package dimensions;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import utils.Copyable;
import dimensions.conversion.ConversionManager;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public abstract class DimensionalValue<
            T extends @NotNull Enum<T> & DimensionalUnit,
            U extends @NotNull DimensionalValue<T, U>>
        implements Copyable<U>, DimensionalValueInterface<T>, DisplayableDimensionalValue<T> {
    private final @NotNull DimensionName dimensionName;
    private final @NotNull ConversionManager<T> conversionManager;
    private final double value;
    private final T unit;

    //null unit - SI
    public DimensionalValue(@NotNull DimensionName dimensionName, @NotNull ConversionManager<T> conversionManager,
                            double value, @Nullable T unit) {
        this.dimensionName = dimensionName;
        this.conversionManager = conversionManager;
        this.value = value;
        this.unit = Objects.requireNonNullElse(unit, conversionManager.getBaseUnit());
    }

    //
    public final @NotNull DimensionName getDimensionName() {
        return dimensionName;
    }

    //
    @Override
    public final T getBaseUnit() {
        return conversionManager.getBaseUnit();
    }

    //
    @Override
    public final double getValue() {
        return value;
    }

    //
    @Override
    public final T getUnit() {
        return unit;
    }

    //For unit conversion. Null unit - SI
    @Override
    public final double get(@Nullable T unit) {
        return conversionManager.getConvertedValue(this, unit);
    }
}
package dimensions.conversion;

import org.jetbrains.annotations.NotNull;

import dimensions.DimensionalUnit;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public final class ConversionRatioTemplate<T extends @NotNull Enum<T> & DimensionalUnit> {
    private final T
            from,
            to;
    private final double ratio;

    //
    public ConversionRatioTemplate(T from, T to, double ratio) {
        if (from == to) {
            throw new RuntimeException(sameUnitExceptionString(from, to));
        }
        this.from = from;
        this.to = to;
        this.ratio = ratio;
    }

    //
    T getFrom() {
        return from;
    }

    //
    T getTo() {
        return to;
    }

    //
    double getRatio() {
        return ratio;
    }

    private static <T extends @NotNull Enum<T> & DimensionalUnit> @NotNull String sameUnitExceptionString(T from,
                                                                                                          T to) {
        return "Meaningless conversion from " + from.getLongName() + " to " + to.getLongName() + ".";
    }
}
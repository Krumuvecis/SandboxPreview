package dimensions;

import java.util.Map;
import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;

//
public interface ConversionRatiosInitializer<T extends @NotNull Enum<T>> {
    //
    static <T extends @NotNull Enum<T>>
    @NotNull Map<@NotNull T, @NotNull Map<@NotNull T, @NotNull Double>> initializeConversionMap(
            @NotNull T @NotNull [] units) {
        @NotNull Map<@NotNull T, @NotNull Map<@NotNull T, @NotNull Double>>
                ratios = new EnumMap<>(units[0].getDeclaringClass());
        for (@NotNull T unit : units) {
            ratios.put(unit, new EnumMap<>(units[0].getDeclaringClass()));
        }
        return ratios;
    }

    //
    void populateConversionRatios(@NotNull Map<@NotNull T, @NotNull Map<@NotNull T, @NotNull Double>> ratios);
}
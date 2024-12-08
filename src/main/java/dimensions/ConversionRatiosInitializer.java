package dimensions;

import java.util.Map;
import java.util.EnumMap;

//
public interface ConversionRatiosInitializer<T extends Enum<T>> {
    //
    static <T extends Enum<T>> Map<T, Map<T, Double>> initializeConversionMap(T[] units) {
        Map<T, Map<T, Double>> ratios = new EnumMap<>(units[0].getDeclaringClass());
        for (T unit : units) {
            ratios.put(unit, new EnumMap<>(units[0].getDeclaringClass()));
        }
        return ratios;
    }

    //
    void populateConversionRatios(Map<T, Map<T, Double>> ratios);
}
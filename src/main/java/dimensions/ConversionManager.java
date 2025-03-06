package dimensions;

import java.util.Objects;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public abstract class ConversionManager<T extends @NotNull Enum<T> & DimensionalUnit> {
    private final @NotNull DimensionName dimensionName;
    private final T baseUnit;
    private final @NotNull Map<T, ? extends @NotNull Map<T, @NotNull Double>> directConversionRatios;

    //TODO: old from interface ConversionRatiosInitializer<T extends @NotNull Enum<T>>
    /*
    //
    static <T extends @NotNull Enum<T>>
    @NotNull Map<T, @NotNull Map<T, @NotNull Double>> initializeConversionMap(T @NotNull [] units) {
        @NotNull Map<T, @NotNull Map<T, @NotNull Double>> ratios = new EnumMap<>(units[0].getDeclaringClass());
        for (T unit : units) {
            ratios.put(unit, new EnumMap<>(units[0].getDeclaringClass()));
        }
        return ratios;
    }

    //
    void populateConversionRatios(@NotNull Map<T, @NotNull Map<T, @NotNull Double>> ratios);
    */

    //TODO: old from DimensionalUnit<>
    /*
    public static <T extends @NotNull Enum<T>> void addConversions(
            @NotNull Map<T, ? extends @NotNull Map<T, @NotNull Double>> ratios,
            T unit1, T unit2, double ratio) {
        ratios.get(unit1).put(unit2, ratio);
        ratios.get(unit2).put(unit1, 1 / ratio);
    }*/

    //TODO: old from Distance
    /*
    private static final @NotNull Map<@NotNull DistanceUnit, @NotNull Map<@NotNull DistanceUnit, @NotNull Double>>
            CONVERSION_RATIOS;

    static {
        @NotNull Map<@NotNull DistanceUnit, @NotNull Map<@NotNull DistanceUnit, @NotNull Double>>
                ratios = ConversionRatiosInitializer.initializeConversionMap((DistanceUnit.values()));
        new Distance().populateConversionRatios(ratios);
        CONVERSION_RATIOS = Collections.unmodifiableMap(ratios);
    }

    @Override
    public void populateConversionRatios(
            @NotNull Map<@NotNull DistanceUnit, @NotNull Map<@NotNull DistanceUnit, @NotNull Double>> ratios) {
        //km
        addConversions(ratios, DistanceUnit.KM, DistanceUnit.M, KM_TO_M);

        //au
        addConversions(ratios, DistanceUnit.AU, DistanceUnit.KM, AU_TO_KM);
        addConversions(ratios, DistanceUnit.AU, DistanceUnit.M, AU_TO_KM * KM_TO_M);

        //ly
        addConversions(ratios, DistanceUnit.LY, DistanceUnit.KM, LY_TO_KM);
        addConversions(ratios, DistanceUnit.LY, DistanceUnit.AU, LY_TO_KM / AU_TO_KM);
        addConversions(ratios, DistanceUnit.LY, DistanceUnit.M, LY_TO_KM * KM_TO_M);

        //pc
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.LY, PC_TO_LY);
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.KM, PC_TO_LY * LY_TO_KM);
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.AU, PC_TO_LY * LY_TO_KM / AU_TO_KM);
        addConversions(ratios, DistanceUnit.PC, DistanceUnit.M, PC_TO_LY * LY_TO_KM * KM_TO_M);
    }*/

    //
    public ConversionManager(@NotNull DimensionName dimensionName, T baseUnit) {
        this.dimensionName = dimensionName;
        this.baseUnit = baseUnit;
        directConversionRatios = initializeConversionRatios(getRatioTemplates());
    }

    //
    public final T getBaseUnit() {
        return baseUnit;
    }

    //
    public abstract @NotNull List<@NotNull ConversionRatioTemplate<T>> getRatioTemplates();

    private @NotNull Map<T, ? extends @NotNull Map<T, @NotNull Double>> initializeConversionRatios(
            @NotNull List<@NotNull ConversionRatioTemplate<T>> templateList) {

        //T baseUnit = getBaseUnit(); //TODO: restore this line, when done with the method as a whole
        @NotNull Map<T, @NotNull Double> baseIntermediaryMap = new HashMap<>();
        @NotNull Map<T, @NotNull Map<T, @NotNull Double>> ratioMap = new HashMap<>() {{
            put(baseUnit, baseIntermediaryMap);
        }};

        while (!templateList.isEmpty()) {
            int templateCount = templateList.size();
            for (int i = 0; i < templateList.size(); i++) {
                @NotNull ConversionRatioTemplate<T> template = templateList.get(i);
                T
                        from = template.getFrom(),
                        to = template.getTo();
                double
                        directRatio = template.getRatio(),
                        directRatioInverted = 1 / directRatio;








                //TODO: old ideas

                if (from == baseUnit) { //from base
                    //TODO: check for duplicate ratios
                    baseIntermediaryMap.put(to, directRatio);
                    ratioMap.put(to, new HashMap<>() {{
                        put(baseUnit, directRatioInverted);
                    }});
                    templateList.remove(i);
                    i--;
                } else if (to == baseUnit) { //to base
                    //TODO: check for duplicate ratios
                    baseIntermediaryMap.put(to, directRatioInverted);
                    ratioMap.put(from, new HashMap<>() {{
                        put(baseUnit, directRatio);
                    }});
                    templateList.remove(i);
                    i--;
                } else { //from non-base to non-base
                    //TODO: check for duplicate ratios here
                    //TODO: initialize ratios here
                }





            }
            if (templateCount == templateList.size()) { //template list unchanged during the last iteration
                throw new RuntimeException(
                        dimensionName.getNameUppercase() + " conversion ratio initialization exception." +
                                " Unable to add " + templateCount + " ratios.");
            }
        }

        //TODO: make an unmodifiable map here

        return ratioMap;
    }

    //null target - base
    private @NotNull Double getRatio(T convertibleUnit, @Nullable T targetUnit) throws UnitConversionException {
        T nonNullTargetUnit = Objects.requireNonNullElse(targetUnit, getBaseUnit());
        if (convertibleUnit == nonNullTargetUnit) {
            return (double) 1;
        } else {
            try {
                @NotNull Map<T, @NotNull Double> particularRatios = directConversionRatios.get(convertibleUnit);
                try {
                    return particularRatios.get(nonNullTargetUnit);
                } catch (@NotNull NullPointerException ignored) {
                    throw new ConversionNotDefinedException(dimensionName, convertibleUnit, nonNullTargetUnit);
                }
            } catch (@NotNull NullPointerException ignored) {
                throw new UnitNotAddedException(dimensionName, convertibleUnit);
            }
        }
    }

    //null target - base
    public final <U extends @NotNull DimensionalValue<T, U>> double getConvertedValue(
            @NotNull DimensionalValue<T, U> convertible, @Nullable T targetUnit) {
        try {
            @NotNull Double ratio = getRatio(convertible.getUnit(), targetUnit);
            return convertible.getValue() * ratio;
        } catch (@NotNull UnitConversionException e) {
            throw new RuntimeException(e);
        }
    }

    //
    public static final class ConversionRatioTemplate<T extends @NotNull Enum<T> & DimensionalUnit> {
        private final T
                from,
                to;
        private final double ratio;

        //
        public ConversionRatioTemplate(T from, T to, double ratio) {
            if (from == to) {
                throw new RuntimeException(
                        "Meaningless conversion from " + from.getLongName() + " to " + to.getLongName() + ".");
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
    }

    //
    private static class UnitConversionException extends Exception {
        private static final @NotNull String NULL_UNIT_NAME = "NULL";

        //
        UnitConversionException(@NotNull DimensionName dimensionName, @NotNull String message) {
            super(message + " " + getMessageSuffix(dimensionName));
        }

        private static @NotNull String getMessageSuffix(@NotNull DimensionName dimensionName) {
            return "Unable to convert " + dimensionName.getNameLowercase() + ".";
        }

        //
        static @NotNull String getNonNullUnitName(@Nullable DimensionalUnit unit) {
            return unit == null ? NULL_UNIT_NAME : unit.getLongName();
        }
    }

    //
    private static final class UnitNotAddedException extends UnitConversionException {
        //
        UnitNotAddedException(@NotNull DimensionName dimensionName, @Nullable DimensionalUnit unit) {
            super(dimensionName, getNewMessage(dimensionName, unit));
        }

        private static @NotNull String getNewMessage(@NotNull DimensionName dimensionName,
                                                     @Nullable DimensionalUnit unit) {
            return dimensionName.getNameUppercase() + " unit " + getNonNullUnitName(unit) +
                    " not added to conversion ratio map.";
        }
    }

    //
    private static final class ConversionNotDefinedException extends UnitConversionException {
        //
        ConversionNotDefinedException(@NotNull DimensionName dimensionName,
                                      @Nullable DimensionalUnit unit1, @Nullable DimensionalUnit unit2) {
            super(dimensionName, getNewMessage(dimensionName, unit1, unit2));
        }

        private static @NotNull String getNewMessage(@NotNull DimensionName dimensionName,
                                                     @Nullable DimensionalUnit unit1, @Nullable DimensionalUnit unit2) {
            return dimensionName.getNameUppercase() + " conversion ratio " +
                    "from " + getNonNullUnitName(unit1) + " to " + getNonNullUnitName(unit2) +
                    " not defined.";
        }
    }
}
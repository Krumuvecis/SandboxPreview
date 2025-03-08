package dimensions;

import java.util.Objects;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public abstract class ConversionManager<T extends @NotNull Enum<T> & DimensionalUnit> {
    private static final @NotNull String INDENT = "  "; //for debugging
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
    */

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
        printLine("Initializing " + dimensionName.getNameLowercase() + " conversion ratios.");
        //adds the base unit
        @NotNull Map<T, @NotNull Map<T, @NotNull Double>> ratioMap = new HashMap<>() {{
            put(baseUnit, new HashMap<>());
        }};

        /*
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
            }
            if (templateCount == templateList.size()) { //template list unchanged during the last iteration
                throw new RuntimeException(
                        dimensionName.getNameUppercase() + " conversion ratio initialization exception." +
                                " Unable to add " + templateCount + " ratios.");
            }
        }*/

        sortTemplates(ratioMap, templateList);
        printLine(dimensionName.getNameUppercase() + " conversion ratio initialization complete.");
        printLine("");
        return Collections.unmodifiableMap(ratioMap);
    }

    //TODO: finish this
    private void sortTemplates(@NotNull Map <T, @NotNull Map<T, Double>> ratioMap,
                               @NotNull List<@NotNull ConversionRatioTemplate<T>> templateList) {
        for (@NotNull ConversionRatioTemplate<T> template : templateList) {
            sortSingleTemplate(ratioMap, template);
        }
    }

    private void sortSingleTemplate(@NotNull Map<T, @NotNull Map<T, Double>> ratioMap,
                                    @NotNull ConversionRatioTemplate<T> template) {
        @NotNull Set<T> alreadyDefinedUnits = ratioMap.keySet();
        T startUnit = template.getFrom();
        T targetUnit = template.getTo();

        boolean startIsBase = startUnit == baseUnit;
        if (!startIsBase) {
            boolean startAlreadyDefined = alreadyDefinedUnits.contains(startUnit);
            if (startAlreadyDefined) {
                printLine("Start unit already defined.");
                //throw new RuntimeException("Start unit already defined.");
            }
            ratioMap.put(startUnit, new HashMap<>());
        }

        boolean targetIsBase = targetUnit == baseUnit;
        if (!targetIsBase) {
            boolean targetAlreadyDefined = alreadyDefinedUnits.contains(targetUnit);
            if (targetAlreadyDefined) {
                printLine("Target unit already defined.");
                //throw new RuntimeException("Target unit already defined.");
            }
            ratioMap.put(targetUnit, new HashMap<>());
        }

        //direct
        printLine(INDENT + "Adding direct conversion" +
                " from " + startUnit.getShortName() + " to " + targetUnit.getShortName() + ".");
        double d_direct = template.getRatio();
        addSingleRatio(ratioMap, startUnit, targetUnit, d_direct);

        if (!startIsBase || !targetIsBase) { //either start or target is non-base
            printLine(INDENT + "Checking conversion to base and remaining units.");
            double d_base;
            if (startIsBase || targetIsBase) { //either one is base
                d_base = d_direct;
            } else { //none is base
                d_base = d_direct * ratioMap.get(targetUnit).get(baseUnit);
                printLine(INDENT + "Adding conversion to base.");
                addSingleRatio(ratioMap, startUnit, baseUnit, d_base);
            }

            //remaining
            printLine(INDENT + "Checking remaining ratios.");
            for (T intermediateTargetUnit : alreadyDefinedUnits) {
                if (intermediateTargetUnit != baseUnit && intermediateTargetUnit != startUnit && intermediateTargetUnit != targetUnit) {
                    //from start to base, from base to target
                    double d = d_base * ratioMap.get(baseUnit).get(intermediateTargetUnit);
                    addSingleRatio(ratioMap, startUnit, intermediateTargetUnit, d);
                }
            }
        }

        printLine(INDENT + "Template sorted.");
        printLine("");
    }

    private void addSingleRatio(@NotNull Map<T, @NotNull Map<T, Double>> ratioMap,
                                T start, T target, double ratio) {
        printLine(INDENT.repeat(2) + "Adding ratio" +
                " from " + start.getShortName() + " to " + target.getShortName() +
                ", ratio: " + ratio);
        ratioMap.get(start).put(target, ratio);
        ratioMap.get(target).put(start, 1 / ratio);
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
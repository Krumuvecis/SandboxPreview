package dimensions.conversion;

import java.util.Objects;
import java.util.Collections;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.DimensionalUnit;
import dimensions.DimensionName;
import dimensions.DimensionalValue;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public abstract class ConversionManager<T extends @NotNull Enum<T> & DimensionalUnit> {
    private static final @NotNull String INDENT = "  "; //for debugging
    private static final boolean PRINT_INITIALIZATION_TO_CONSOLE = false;
    private final @NotNull DimensionName dimensionName;
    private final T baseUnit;
    private final @NotNull Map<T, ? extends @NotNull Map<T, @NotNull Double>> directConversionRatios;

    //
    public ConversionManager(@NotNull DimensionName dimensionName, T baseUnit) {
        this.dimensionName = dimensionName;
        this.baseUnit = baseUnit;
        directConversionRatios = initializeConversionRatios(getRatioTemplates(), PRINT_INITIALIZATION_TO_CONSOLE);
    }

    //
    public final T getBaseUnit() {
        return baseUnit;
    }

    //
    public abstract @NotNull List<@NotNull ConversionRatioTemplate<T>> getRatioTemplates();

    @SuppressWarnings("SameParameterValue")
    private @NotNull Map<T, ? extends @NotNull Map<T, @NotNull Double>> initializeConversionRatios(
            @NotNull List<@NotNull ConversionRatioTemplate<T>> templateList, boolean printToConsole) {
        printIndentedLineIf(0, printToConsole,
                "Initializing " + dimensionName.getNameLowercase() + " conversion ratios.");

        //adds the base unit
        printIndentedLineIf(1, printToConsole, "Adding base unit: " + baseUnit.getShortName());
        @NotNull Map<T, @NotNull Map<T, @NotNull Double>> ratioMap = new EnumMap<>(baseUnit.getDeclaringClass()) {{
            put(baseUnit, new EnumMap<>(baseUnit.getDeclaringClass()));
        }};

        while (!templateList.isEmpty()) {
            int templateCount = templateList.size();
            processTemplates(ratioMap, templateList, printToConsole, 1);
            if (templateCount == templateList.size()) { //template list unchanged during the last iteration
                throw new RuntimeException(new ConversionRatiosInitializationException(dimensionName,
                        "Unable to add " + templateCount + " ratios."));
            }
        }
        printIndentedLineIf(0, printToConsole,
                dimensionName.getNameUppercase() + " conversion ratio initialization complete.");
        printIndentedLineIf(0, printToConsole, null);
        return Collections.unmodifiableMap(ratioMap);
    }

    @SuppressWarnings("SameParameterValue")
    private void processTemplates(@NotNull Map <T, @NotNull Map<T, Double>> ratioMap,
                                  @NotNull List<@NotNull ConversionRatioTemplate<T>> templateList,
                                  boolean printToConsole, int indent) {
        printIndentedLineIf(indent, printToConsole, "Sorting " + templateList.size() + " templates:");
        for (int i = 0; i < templateList.size(); i++) { // 1-level-for, checking all templates
            @NotNull ConversionRatioTemplate<T> template = templateList.get(i);
            if (processTemplate(ratioMap, template, printToConsole, indent + 1)) {
                printIndentedLineIf(indent + 2, printToConsole, "Template processed successfully.");
                templateList.remove(i);
                i--;
            } else {
                printIndentedLineIf(indent + 2, printToConsole, "Template not processed.");
            }
        }
        printIndentedLineIf(indent, printToConsole, null);
    }

    @SuppressWarnings("RedundantCast")
    private boolean processTemplate(@NotNull Map<T, @NotNull Map<T, Double>> ratioMap,
                                    @NotNull ConversionRatioTemplate<? extends T> template,
                                    boolean printToConsole, int indent) {
        T startUnit = (T) template.getFrom();
        T targetUnit = (T) template.getTo();
        printIndentedLineIf(indent, printToConsole, "Conversion template: " +
                startUnit.getShortName() + " - " + targetUnit.getShortName() + ", ratio: " + template.getRatio());

        @NotNull Set<T> alreadyDefinedUnits = ratioMap.keySet();
        boolean
                startAlreadyDefined = alreadyDefinedUnits.contains(startUnit),
                targetAlreadyDefined = alreadyDefinedUnits.contains(targetUnit);

        if (startAlreadyDefined && targetAlreadyDefined) {
            throw new RuntimeException(new ConversionRatiosInitializationException(dimensionName,
                    "Both start and target already defined - refusing to overwrite."));
        }
        double
                startTargetRatio = template.getRatio(),
                startTargetRatio_inverse = 1 / startTargetRatio;

        if (startAlreadyDefined) {
            printIndentedLineIf(indent + 1, printToConsole,
                    "Start unit (" + startUnit.getShortName() + ") is already defined.");
            return processNewUnit(ratioMap, alreadyDefinedUnits,
                    targetUnit, startUnit, startTargetRatio_inverse, startTargetRatio,
                    printToConsole, indent + 1);
        } else if (targetAlreadyDefined) {
            printIndentedLineIf(indent + 1, printToConsole,
                    "Target unit (" + targetUnit.getShortName() + ") is already defined.");
            return processNewUnit(ratioMap, alreadyDefinedUnits,
                    startUnit, targetUnit, startTargetRatio, startTargetRatio_inverse,
                    printToConsole, indent + 1);
        } else {
            printIndentedLineIf(indent + 1, printToConsole, "Neither start nor target is defined.");
            return false;
        }
    }

    @SuppressWarnings("SameParameterValue")
    private boolean processNewUnit(@NotNull Map<? super T, @NotNull Map<T, Double>> ratioMap,
                                   @NotNull Set<? extends T> alreadyDefinedUnits,
                                   T newUnit, T referenceUnit,
                                   double newToReferenceRatio, double referenceToNewRatio,
                                   boolean printToConsole, int indent) {
        printIndentedLineIf(indent, printToConsole, "Adding " + newUnit.getShortName() + " to map.");
        ratioMap.put(newUnit, new EnumMap<>(baseUnit.getDeclaringClass()));

        printIndentedLineIf(indent + 1, printToConsole,
                "Direct conversions to/from: " + referenceUnit.getShortName());
        ratioMap.get(newUnit).put(referenceUnit, newToReferenceRatio); //direct new->reference
        ratioMap.get(referenceUnit).put(newUnit, referenceToNewRatio); //direct reference->new

        for (T particularUnit : alreadyDefinedUnits) {
            if (particularUnit != newUnit && particularUnit != referenceUnit) {
                printIndentedLineIf(indent + 1, printToConsole,
                        "Direct conversions to/from: " + particularUnit.getShortName());

                //direct new->particular
                double referenceToParticularRatio = ratioMap.get(referenceUnit).get(particularUnit);
                double newToParticularRatio = newToReferenceRatio * referenceToParticularRatio;
                ratioMap.get(newUnit).put(particularUnit, newToParticularRatio);

                //direct particular->new
                double particularToReferenceRatio = ratioMap.get(particularUnit).get(referenceUnit);
                double particularToNewRatio = particularToReferenceRatio * referenceToNewRatio;
                ratioMap.get(particularUnit).put(newUnit, particularToNewRatio);
            }
        }
        return true;
    }

    private static void printIndentedLineIf(int indent, boolean print, @Nullable String message) {
        if (print) {
            printLine(INDENT.repeat(indent) + Objects.requireNonNullElse(message, ""));
        }
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
    @SuppressWarnings("RedundantCast")
    public final <U extends @NotNull DimensionalValue<T, U>> double getConvertedValue(
            @NotNull DimensionalValue<? extends T, U> convertible, @Nullable T targetUnit) {
        try {
            @NotNull Double ratio = getRatio((T) convertible.getUnit(), targetUnit);
            return convertible.getValue() * ratio;
        } catch (@NotNull UnitConversionException e) {
            throw new RuntimeException(e);
        }
    }

    //
    private static class ConversionRatiosInitializationException extends Exception {
        //
        ConversionRatiosInitializationException(@NotNull DimensionName dimensionName, @NotNull String message) {
            super(dimensionName.getNameUppercase() + " conversion ratio initialization exception. " + message);
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

    //a particular unit hasn't been added to the map, so can't be found within it
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
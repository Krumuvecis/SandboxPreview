package dimensions;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

//
abstract class DimensionalValueTest<
        T extends @NotNull DimensionalValue<U, T>,
        U extends @NotNull Enum<U> & @NotNull DimensionalUnit> {
    private static final @NotNull String INDENT = "  ";

    //
    DimensionalValueTest(@NotNull T dimensionalValue, @NotNull List<@NotNull U> units) {
        printLine("Testing " + dimensionalValue.getDimensionName() + ", " + dimensionalValue.getValueAndShortUnit());
        printConvertedLines(dimensionalValue, units);
        printLine("");
    }

    private void printConvertedLines(@NotNull T dimensionalValue, @NotNull List<@NotNull U> units) {
        for (@NotNull U unit : units) {
            printConvertedLine(dimensionalValue, unit);
        }
    }

    private void printConvertedLine(@NotNull T dimensionalValue, @NotNull U unit) {
        printLine(INDENT + dimensionalValue.getValueAndShortUnit(unit));
    }
}
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
    DimensionalValueTest(T dimensionalValue, @NotNull List<U> units) {
        printLine("Testing " + dimensionalValue.getDimensionName().getNameLowercase() +
                ", " + dimensionalValue.getValueAndShortUnit());
        printConvertedLines(dimensionalValue, units);
        printLine("");
    }

    private void printConvertedLines(T dimensionalValue, @NotNull List<U> units) {
        for (U unit : units) {
            printConvertedLine(dimensionalValue, unit);
        }
    }

    private void printConvertedLine(T dimensionalValue, U unit) {
        printLine(INDENT + dimensionalValue.getValueAndShortUnit(unit));
    }
}
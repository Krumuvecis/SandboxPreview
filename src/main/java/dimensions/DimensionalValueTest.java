package dimensions;

import java.util.List;

import static consoleUtils.SimplePrinting.printLine;

//
public abstract class DimensionalValueTest<T extends DimensionalValue<U>, U extends Enum<U> & DimensionalUnit> {
    private static final String INDENT = "  ";

    //
    public DimensionalValueTest(T dimensionalValue, List<U> units) {
        printLine("Testing " + dimensionalValue.getDimensionName() + ", " + dimensionalValue.getValueAndShortUnit());
        printConvertedLines(dimensionalValue, units);
        printLine("");
    }

    private void printConvertedLines(T dimensionalValue, List<U> units) {
        for (U unit : units) {
            printConvertedLine(dimensionalValue, unit);
        }
    }

    private void printConvertedLine(T dimensionalValue, U unit) {
        printLine(INDENT + dimensionalValue.getValueAndShortUnit(unit));
    }
}
package chemistryTests;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.MassUnit;
import chemistry.Element;

//
public class ElementTest {
    private static final @NotNull String INDENT = "  ";

    //
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) {
        printLine("Testing elements.");
        new ElementTest(Element.PLUTONIUM);
        new ElementTest(Element.OXYGEN);
        new ElementTest(Element.SODIUM);
    }

    private ElementTest(@NotNull Element element) {
        printLine("Element: " + element.getName() + " (" + element.getAtomicNumber() + ", "  + element.getSymbol() + ")");
        printIndentedLine(1, "Atomic mass: " + element.getAtomicMass().getValueAndShortUnit(MassUnit.G, 1) + "/mol");
    }

    @SuppressWarnings("SameParameterValue")
    private static void printIndentedLine(int indent, @NotNull String line) {
        printLine(INDENT.repeat(indent) + line);
    }
}
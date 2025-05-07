package chemistryTests;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import chemistry.substance.Substance;
import chemistry.substance.ParticularSubstances;

//
public class SubstanceTest {
    private static final @NotNull String INDENT = "  ";

    //
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) {
        printLine("Testing substances.");
        new SubstanceTest(ParticularSubstances.HYDROGEN_GAS);
        new SubstanceTest(ParticularSubstances.OXYGEN_GAS);
        new SubstanceTest(ParticularSubstances.WATER_VAPOR);
    }

    private SubstanceTest(@NotNull Substance substance) {
        printLine("Substance: " + substance.getName());
    }

    @SuppressWarnings("SameParameterValue")
    private static void printIndentedLine(int indent, @NotNull String line) {
        printLine(INDENT.repeat(indent) + line);
    }
}
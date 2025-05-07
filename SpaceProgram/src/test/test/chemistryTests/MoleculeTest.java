package chemistryTests;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.MassUnit;
import chemistry.Element;
import chemistry.SuperElementalContainer.ConstituentFraction;
import chemistry.molecules.Molecule;
import chemistry.molecules.ElementalMolecules;
import chemistry.molecules.ParticularMolecules;

//
public class MoleculeTest {
    private static final @NotNull String INDENT = "  ";
    private static final @NotNull List<@NotNull Molecule> MOLECULES = new ArrayList<>() {
        {
            addMolecule(ElementalMolecules.getElementalMolecule(Element.OXYGEN));
            addMolecule(ElementalMolecules.getElementalMolecule(Element.CHLORINE));
            addMolecule(ElementalMolecules.getElementalMolecule(Element.SULFUR));
            addMolecule(ElementalMolecules.getElementalMolecule(Element.LITHIUM));
            addMolecule(ParticularMolecules.WATER);
            addMolecule(ParticularMolecules.getOxide(Element.CARBON));
            addMolecule(ParticularMolecules.getHydride(Element.CARBON)); //methane
            addMolecule(ParticularMolecules.getHydride(Element.NITROGEN)); //ammonia
        }

        private void addMolecule(@Nullable Molecule molecule) {
            if (molecule != null) {
                add(molecule);
            }
        }
    };

    //
    public static void main(String[] args) {
        printLine("Testing molecules.");
        for (@NotNull Molecule molecule : MOLECULES) {
            printLine("");
            new MoleculeTest(molecule);
        }
    }

    private MoleculeTest(@Nullable Molecule molecule) {
        if (molecule == null) {
            printLine("Molecule: null.");
        } else {
            printLine("Molecule: " + molecule.getName() + ", formula: " + molecule.getFormula());
            printIndentedLine(1, "Atomic mass: " +
                    molecule.getAtomicMass().getValueAndShortUnit(MassUnit.G, 1) + "/mol");
            printConstituents(molecule, 1);
            printElementsByMolarFraction(molecule, 1);
            printElementsByMassFraction(molecule, 1);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void printConstituents(@NotNull Molecule molecule, int indent) {
        printIndentedLine(indent, "Constituents: ");
        @NotNull Map<@NotNull Element, @NotNull Integer> constituents = molecule.getConstituents();
        for (@NotNull Element constituent : constituents.keySet()) {
            printIndentedLine(indent + 1, constituent.getSymbol() + " - " + constituents.get(constituent));
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void printElementsByMolarFraction(@NotNull Molecule molecule, int indent) {
        printIndentedLine(indent, "Elements by molar fraction: ");
        @NotNull List<@NotNull ConstituentFraction<@NotNull Element>>
                sortedElements = molecule.getElementsByMolarFraction();
        for (@NotNull ConstituentFraction<@NotNull Element> fraction : sortedElements) {
            printIndentedLine(indent + 1, fraction.getConstituent().getSymbol() + " - " +
                    getRoundedPercentage(fraction.getFraction(), 1) + " %");
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void printElementsByMassFraction(@NotNull Molecule molecule, int indent) {
        printIndentedLine(indent, "Elements by mass fraction: ");
        @NotNull List<@NotNull ConstituentFraction<@NotNull Element>>
                sortedElements = molecule.getElementsByMassFraction();
        for (@NotNull ConstituentFraction<@NotNull Element> fraction : sortedElements) {
            printIndentedLine(indent + 1,fraction.getConstituent().getSymbol() + " - " +
                    getRoundedPercentage(fraction.getFraction(), 1) + " %");
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void printIndentedLine(int indent, @NotNull String line) {
        printLine(INDENT.repeat(indent) + line);
    }

    @SuppressWarnings("SameParameterValue")
    private static double getRoundedPercentage(double fraction, int decimalPlaces) {
        return ((int) (100 * fraction * Math.pow(10, decimalPlaces))) / Math.pow(10, decimalPlaces);
    }
}
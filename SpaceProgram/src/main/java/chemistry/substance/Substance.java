package chemistry.substance;

import java.util.List;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import chemistry.molecules.Molecule;
import chemistry.SuperMolecularCompound;

//
public class Substance extends SuperMolecularCompound<@NotNull Molecule> {
    private static final int
            NAME_BUILDER_MAXIMUM_CONSTITUENTS = 5,
            NAME_BUILDER_DECIMAL_PLACES = 1;
    private final @NotNull SubstancePhase phase;

    //
    public Substance(@Nullable String name, @NotNull Map<@NotNull Molecule, @NotNull Double> constituents,
                     @NotNull SubstancePhase phase) {
        super(name, constituents);
        this.phase = phase;
    }

    //
    @Override
    public final @NotNull String determineNewName() {
        @NotNull StringBuilder name = new StringBuilder(phase + " substance (");
        @NotNull List<@NotNull ConstituentFraction<@NotNull Molecule>> sortedMolecules = getMoleculesByMolarFraction();
        int membersInName = Math.min(NAME_BUILDER_MAXIMUM_CONSTITUENTS, sortedMolecules.size());
        for (int i = 0; i < membersInName; i++) {
            if (i > 0) {
                name.append(", ");
            }
            @NotNull ConstituentFraction<@NotNull Molecule> moleculeFraction = sortedMolecules.get(i);
            name.append(getPercentageString(moleculeFraction.getFraction(), NAME_BUILDER_DECIMAL_PLACES));
            name.append(" ");
            name.append(moleculeFraction.getConstituent().getName());
        }
        name.append(")");
        return name.toString();
    }

    @SuppressWarnings("SameParameterValue")
    private @NotNull String getPercentageString(double fraction, int decimalPlaces) {
        return getRoundedPercentage(fraction, decimalPlaces) + " %";
    }

    private double getRoundedPercentage(double fraction, int decimalPlaces) {
        return ((int) (100 * fraction * Math.pow(10, decimalPlaces))) / Math.pow(10, decimalPlaces);
    }

    //
    public final @NotNull SubstancePhase getPhase() {
        return phase;
    }
}
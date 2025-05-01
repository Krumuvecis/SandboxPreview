package chemistry.substance;

import java.util.Set;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import chemistry.molecules.Molecule;
import chemistry.SuperMolecularCompound;

//
public class Substance extends SuperMolecularCompound<@NotNull Molecule> {
    private final @NotNull SubstancePhase phase;

    //
    public Substance(@Nullable String name, @NotNull Map<@NotNull Molecule, @NotNull Double> constituents, @NotNull SubstancePhase phase) {
        super(name, constituents);
        this.phase = phase;
    }

    //
    @Override
    public final @NotNull String determineNewName() {
        @NotNull StringBuilder name = new StringBuilder("compound (");
        @NotNull Set<@NotNull Molecule> molecules = getConstituents().keySet();

        //TODO: finish this
        /*List<Molecule> =
        for (int i) {
            @NotNull Molecule molecule;
            name.append(molecule);
        }*/

        name.append(")");
        return name.toString();
    }

    //
    public final @NotNull SubstancePhase getPhase() {
        return phase;
    }
}
package chemistry;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import chemistry.molecules.Molecule;

//
public class SuperMolecularContainer<K extends @NotNull SuperElementalContainer<? extends @NotNull AtomicInterface, ? extends @NotNull Number>>
        extends SuperElementalContainer<K, @NotNull Double> {
    //
    public SuperMolecularContainer(@NotNull Map<K, @NotNull Double> constituents) {
        super(constituents);
    }

    //for internal operations with constituents' values
    @Override
    protected final @NotNull Double newValue() {
        return (double) 0;
    }

    //for internal operations with constituents' values
    @Override
    protected final @NotNull Double addToValue(@NotNull Double v, @NotNull Double d) {
        return v + d;
    }

    //for internal operations of calculating constituent molar fractions
    @Override
    protected final <T extends @NotNull AtomicInterface> double getMolarFraction_withinConstituent(K constituent, T target) {
        if (constituent == target) {
            return 1;
        } else if (constituent instanceof @NotNull SuperMolecularCompound<?> compound) {
            return compound.getMolarFraction_generic(target);
        } else {
            return 0;
        }
    }

    //returns 0, if molecule not found
    public final double getMolarFraction_molecule(@NotNull Molecule molecule) {
        return getMolarFraction_generic(molecule);
    }

    //returns 0, if molecule not found
    public final double getMassFraction_molecule(@NotNull Molecule molecule) {
        return getMassFraction_generic(molecule);
    }

    //
    public final @NotNull Set<@NotNull Molecule> getAllMolecules() {
        @NotNull Set<@NotNull Molecule> molecules = new HashSet<>();
        for (K constituent : getConstituents().keySet()) {
            if (constituent instanceof @NotNull Molecule molecule) {
                molecules.add(molecule);
            } else if (constituent instanceof @NotNull SuperMolecularCompound<?> compound) {
                molecules.addAll(compound.getAllMolecules());
            }
        }
        return molecules;
    }

    //
    public final @NotNull List<@NotNull ConstituentFraction<@NotNull Molecule>> getMoleculesByMolarFraction() {
        @NotNull List<@NotNull ConstituentFraction<@NotNull Molecule>> orderedMolecules = new ArrayList<>();

        //gets all molecules unordered
        @NotNull Set<@NotNull Molecule> unorderedMolecules = getAllMolecules();

        //sorts the molecules elements
        while (!unorderedMolecules.isEmpty()) {
            double biggestFraction = 0;
            @Nullable Molecule biggestMolecule = null;
            for (@NotNull Molecule molecule : unorderedMolecules) {
                double fraction = getMolarFraction_molecule(molecule);
                if (fraction > biggestFraction) {
                    biggestFraction = fraction;
                    biggestMolecule = molecule;
                }
            }
            if (biggestMolecule == null) {
                break;
            }
            orderedMolecules.add(new ConstituentFraction<@NotNull Molecule>(biggestMolecule, biggestFraction));
            unorderedMolecules.remove(biggestMolecule);
        }
        return orderedMolecules;
    }

    //
    public final @NotNull List<@NotNull ConstituentFraction<@NotNull Molecule>> getMoleculesByMassFraction() {
        @NotNull List<@NotNull ConstituentFraction<@NotNull Molecule>> orderedMolecules = new ArrayList<>();

        //gets all molecules unordered
        @NotNull Set<@NotNull Molecule> unorderedMolecules = getAllMolecules();

        //sorts the molecules elements
        while (!unorderedMolecules.isEmpty()) {
            double biggestFraction = 0;
            @Nullable Molecule biggestMolecule = null;
            for (@NotNull Molecule molecule : unorderedMolecules) {
                double fraction = getMassFraction_molecule(molecule);
                if (fraction > biggestFraction) {
                    biggestFraction = fraction;
                    biggestMolecule = molecule;
                }
            }
            if (biggestMolecule == null) {
                break;
            }
            orderedMolecules.add(new ConstituentFraction<@NotNull Molecule>(biggestMolecule, biggestFraction));
            unorderedMolecules.remove(biggestMolecule);
        }
        return orderedMolecules;
    }
}
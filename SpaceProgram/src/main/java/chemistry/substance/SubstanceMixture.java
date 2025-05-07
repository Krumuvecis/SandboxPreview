package chemistry.substance;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import chemistry.SuperMolecularCompound;
//
public class SubstanceMixture extends SuperMolecularCompound<@NotNull Substance> {
    //
    public SubstanceMixture(@Nullable String name, @NotNull Map<@NotNull Substance, @NotNull Double> constituents) {
        super(name, constituents);
    }

    //
    @Override
    public final @NotNull String determineNewName() {
        //TODO: finish this
        return null;
    }
}
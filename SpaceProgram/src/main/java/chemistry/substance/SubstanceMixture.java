package chemistry.substance;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import chemistry.SuperMolecularCompound;
import chemistry.SuperSubstantialInterface;
//
public class SubstanceMixture extends SuperMolecularCompound<@NotNull Substance> implements SuperSubstantialInterface {
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
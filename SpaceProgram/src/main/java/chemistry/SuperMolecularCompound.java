package chemistry;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import common.NamedInterface;

//
public abstract class SuperMolecularCompound<K extends @NotNull SuperElementalContainer<? extends @NotNull AtomicInterface, ? extends @NotNull Number>>
        extends SuperMolecularContainer<K> implements NamedInterface {
    private @Nullable String name;

    //
    public SuperMolecularCompound(@Nullable String name, @NotNull Map<K, @NotNull Double> constituents) {
        super(constituents);
        this.name = name;
    }

    //
    public abstract @NotNull String determineNewName();

    //
    @Override
    public final @NotNull String getName() {
        if (name == null) { //on-demand creates a new name
            name = determineNewName();
        }
        return name;
    }
}
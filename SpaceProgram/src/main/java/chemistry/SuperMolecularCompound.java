package chemistry;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//
public abstract class SuperMolecularCompound<T extends @NotNull SuperElementalInterface>
        extends Compound<T, @NotNull Double> implements SuperMolecularInterface {
    //
    public SuperMolecularCompound(@Nullable String name, @NotNull Map<T, @NotNull Double> constituents) {
        super(name, constituents);
    }

    //for internal operations with constituents' values
    @Override
    public final @NotNull Double newValue() {
        return (double) 0;
    }

    //for internal operations with constituents' values
    @Override
    public final @NotNull Double addToValue(@NotNull Double v, @NotNull Double d) {
        return v + d;
    }
}
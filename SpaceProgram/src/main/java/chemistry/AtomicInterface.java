package chemistry;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
public interface AtomicInterface extends NamedInterface {
    //
    @NotNull Mass getAtomicMass();
}
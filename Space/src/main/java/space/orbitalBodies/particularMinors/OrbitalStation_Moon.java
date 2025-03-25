package space.orbitalBodies.particularMinors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.orbits.OrbitInterface;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class OrbitalStation_Moon extends OrbitalStation {
    private static final @NotNull String NAME = "Moon orbital station";

    //
    public OrbitalStation_Moon(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit);
    }
}
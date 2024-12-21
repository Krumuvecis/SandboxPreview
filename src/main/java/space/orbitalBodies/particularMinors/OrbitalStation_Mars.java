package space.orbitalBodies.particularMinors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.orbits.OrbitInterface;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class OrbitalStation_Mars extends OrbitalStation {
    private static final @NotNull String NAME = "Mars orbital station";

    //
    public OrbitalStation_Mars(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit);
    }
}
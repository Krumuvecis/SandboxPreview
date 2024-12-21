package space.orbitalBodies.particularMinors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import space.orbits.OrbitInterface;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.MinorOrbitalBody;

//
public class OrbitalStation extends MinorOrbitalBody {
    //
    public OrbitalStation(@NotNull String name, @Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(name, parent, orbit);
    }
}
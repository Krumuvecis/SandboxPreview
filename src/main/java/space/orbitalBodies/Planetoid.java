package space.orbitalBodies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import space.orbits.OrbitInterface;

//
public class Planetoid extends MajorOrbitalBody {
    public final @NotNull PlanetaryParameters planetaryParameters;

    //
    public Planetoid(@NotNull String name, @Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit,
                     @NotNull Mass mass, @NotNull PlanetaryParameters planetaryParameters) {
        super(name, parent, orbit, mass);
        this.planetaryParameters = planetaryParameters;
    }
}
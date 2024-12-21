package space.orbitalBodies.particularMajors.earth;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.orbits.OrbitInterface;
import space.orbitalBodies.PlanetaryParameters;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.Planetoid;

//
public final class Moon extends Planetoid {
    private static final @NotNull String NAME = "Moon";
    private static final @NotNull Mass MASS = new Mass(7.346 * Math.pow(10, 22), MassUnit.KG);
    private static final @NotNull Distance RADIUS = new Distance(1738.1, DistanceUnit.KM); //equatorial

    //
    public Moon(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit, MASS, new PlanetaryParameters(RADIUS));
    }
}
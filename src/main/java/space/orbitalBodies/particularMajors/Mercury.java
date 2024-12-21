package space.orbitalBodies.particularMajors;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.orbits.OrbitInterface;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.PlanetaryParameters;
import space.orbitalBodies.Planetoid;

//
public final class Mercury extends Planetoid {
    private static final @NotNull String NAME = "Mercury";
    private static final @NotNull Mass MASS = new Mass(3.302 * Math.pow(10, 23), MassUnit.KG);
    private static final @NotNull Distance RADIUS = new Distance(2440.53, DistanceUnit.KM); //equatorial

    //
    public Mercury(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit, MASS, new PlanetaryParameters(RADIUS));
    }
}
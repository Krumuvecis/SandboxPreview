package space.orbitalBodies.particularMajors.mars;

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
public final class Phobos extends Planetoid {
    private static final @NotNull String NAME = "Phobos";
    private static final @NotNull Mass MASS = new Mass(1.06 * Math.pow(10, 16), MassUnit.KG);
    private static final @NotNull Distance RADIUS = new Distance(11.08, DistanceUnit.KM); //equatorial

    //
    public Phobos(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit, MASS, new PlanetaryParameters(RADIUS));
    }
}
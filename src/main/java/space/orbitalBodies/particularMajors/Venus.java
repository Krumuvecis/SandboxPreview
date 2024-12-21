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
public final class Venus extends Planetoid {
    private static final @NotNull String NAME = "Venus";
    private static final @NotNull Mass MASS = new Mass(4.8690 * Math.pow(10, 24), MassUnit.KG);
    private static final @NotNull Distance RADIUS = new Distance(6051.8, DistanceUnit.KM); //equatorial

    //
    public Venus(@Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        super(NAME, parent, orbit, MASS, new PlanetaryParameters(RADIUS));
    }
}
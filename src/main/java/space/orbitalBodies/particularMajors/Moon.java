package space.orbitalBodies.particularMajors;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.Orbit;
import space.orbitalBodies.PlanetaryParameters;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class Moon extends MajorOrbitalBody {
    private static final String NAME = "Moon";
    private static final Mass MASS = new Mass(7.346 * Math.pow(10, 22), MassUnit.KG);
    private static final Distance RADIUS = new Distance(1738.1, DistanceUnit.KM); //equatorial

    //
    public Moon(MajorOrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit, MASS, new PlanetaryParameters(RADIUS));
    }
}
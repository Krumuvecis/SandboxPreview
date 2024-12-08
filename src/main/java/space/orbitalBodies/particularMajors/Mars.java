package space.orbitalBodies.particularMajors;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.Orbit;
import space.orbitalBodies.OrbitalBody;
import space.orbitalBodies.PlanetaryParameters;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class Mars extends MajorOrbitalBody {
    private static final String NAME = "Mars";
    private static final Mass MASS = new Mass(6.4191 * Math.pow(10, 23), MassUnit.KG);
    private static final Distance RADIUS = new Distance(3396.19, DistanceUnit.KM); //equatorial

    //
    public Mars(OrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit, new PlanetaryParameters(MASS, RADIUS));
    }
}
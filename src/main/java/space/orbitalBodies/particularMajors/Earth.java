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
public final class Earth extends MajorOrbitalBody {
    private static final String NAME = "Earth";
    private static final Mass MASS = new Mass(1, MassUnit.EARTH_MASS);
    private static final Distance RADIUS = new Distance(6378.1366, DistanceUnit.KM); //equatorial

    //
    public Earth(OrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit, new PlanetaryParameters(MASS, RADIUS));
    }
}
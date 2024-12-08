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
public final class Sun extends MajorOrbitalBody {
    private static final String NAME = "Sun";
    private static final Mass MASS = new Mass(1, MassUnit.SOLAR_MASS);
    private static final Distance RADIUS = new Distance(695508, DistanceUnit.KM); //equatorial

    //
    public Sun(OrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit, new PlanetaryParameters(MASS, RADIUS));
    }
}
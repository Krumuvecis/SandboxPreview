package space.orbitalBodies.particularMajors;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.Orbit;
import space.orbitalBodies.PlanetaryParameters;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class Phobos extends MajorOrbitalBody {
    private static final String NAME = "Phobos";
    private static final Mass MASS = new Mass(1.06 * Math.pow(10, 16), MassUnit.KG);
    private static final Distance RADIUS = new Distance(11.08, DistanceUnit.KM); //equatorial

    //
    public Phobos(MajorOrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit, MASS, new PlanetaryParameters(RADIUS));
    }
}
package space.orbitalBodies;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import dimensions.mass.Mass;
import space.Orbit;
import space.satelliteContainer.SatelliteContainer;

//
public class MajorOrbitalBody extends OrbitalBody {
    public final SatelliteContainer satellites; //access satellites through this container
    private final Mass mass;
    public final PlanetaryParameters planetaryParameters;

    //
    public MajorOrbitalBody(String name, MajorOrbitalBody parent, Orbit orbit, Mass mass, PlanetaryParameters planetaryParameters) {
        super(name, parent, orbit);
        satellites = new SatelliteContainer();
        this.mass = mass;
        this.planetaryParameters = planetaryParameters;
    }

    //
    public Mass getMass() {
        return mass;
    }

    public Distance getHillRadius(DistanceUnit unit) {
        //Rh = x * (m2 / (3 * (m1 + m2)))^(1/3)
        //x - instantaneous distance to parent
        //m1 - parent
        //m2 - child
        return null;
    }
}
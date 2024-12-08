package space.orbitalBodies;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.Orbit;
import space.satelliteContainer.SatelliteContainer;

//
public class MajorOrbitalBody extends OrbitalBody {
    public final SatelliteContainer satellites; //access satellites through this container
    public final PlanetaryParameters planetaryParameters;

    //
    public MajorOrbitalBody(String name, OrbitalBody parent, Orbit orbit, PlanetaryParameters planetaryParameters) {
        super(name, parent, orbit);
        satellites = new SatelliteContainer();
        this.planetaryParameters = planetaryParameters;
    }

    public Distance getHillRadius(DistanceUnit unit) {
        //Rh = x * (m2 / (3 * (m1 + m2)))^(1/3)
        //x - instantaneous distance to parent
        //m1 - parent
        //m2 - child
        return null;
    }
}
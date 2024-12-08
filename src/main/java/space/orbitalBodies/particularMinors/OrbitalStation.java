package space.orbitalBodies.particularMinors;

import space.Orbit;
import space.orbitalBodies.OrbitalBody;
import space.orbitalBodies.MinorOrbitalBody;

//
public class OrbitalStation extends MinorOrbitalBody {
    //
    public OrbitalStation(String name, OrbitalBody parent, Orbit orbit) {
        super(name, parent, orbit);
    }
}
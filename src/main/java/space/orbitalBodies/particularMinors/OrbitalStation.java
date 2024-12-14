package space.orbitalBodies.particularMinors;

import space.Orbit;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.MinorOrbitalBody;

//
public class OrbitalStation extends MinorOrbitalBody {
    //
    public OrbitalStation(String name, MajorOrbitalBody parent, Orbit orbit) {
        super(name, parent, orbit);
    }
}
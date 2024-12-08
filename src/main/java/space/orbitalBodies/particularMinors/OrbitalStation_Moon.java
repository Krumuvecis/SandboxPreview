package space.orbitalBodies.particularMinors;

import space.Orbit;
import space.orbitalBodies.OrbitalBody;

//
public final class OrbitalStation_Moon extends OrbitalStation {
    private static final String NAME = "Moon orbital station";

    //
    public OrbitalStation_Moon(OrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit);
    }
}
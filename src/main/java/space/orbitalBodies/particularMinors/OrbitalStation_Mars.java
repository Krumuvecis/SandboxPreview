package space.orbitalBodies.particularMinors;

import space.Orbit;
import space.orbitalBodies.OrbitalBody;

//
public final class OrbitalStation_Mars extends OrbitalStation {
    private static final String NAME = "Mars orbital station";

    //
    public OrbitalStation_Mars(OrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit);
    }
}
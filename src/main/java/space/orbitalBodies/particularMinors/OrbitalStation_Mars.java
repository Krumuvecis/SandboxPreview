package space.orbitalBodies.particularMinors;

import space.Orbit;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class OrbitalStation_Mars extends OrbitalStation {
    private static final String NAME = "Mars orbital station";

    //
    public OrbitalStation_Mars(MajorOrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit);
    }
}
package space.orbitalBodies.particularMinors;

import space.Orbit;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class OrbitalStation_Moon extends OrbitalStation {
    private static final String NAME = "Moon orbital station";

    //
    public OrbitalStation_Moon(MajorOrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit);
    }
}
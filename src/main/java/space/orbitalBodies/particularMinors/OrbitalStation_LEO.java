package space.orbitalBodies.particularMinors;

import space.Orbit;
import space.orbitalBodies.MajorOrbitalBody;

//
public final class OrbitalStation_LEO extends OrbitalStation {
    private static final String NAME = "LEO orbital station";

    //
    public OrbitalStation_LEO(MajorOrbitalBody parent, Orbit orbit) {
        super(NAME, parent, orbit);
    }
}
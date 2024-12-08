package space.orbitalBodies;

import space.Orbit;

//
public abstract class OrbitalBody {
    private final String name;
    private final OrbitalBody parent;
    public Orbit orbit;

    //
    OrbitalBody(String name, OrbitalBody parent, Orbit orbit) {
        this.name = name;
        this.parent = parent;
        this.orbit = orbit;
    }

    public final String getName() {
        return name;
    }

    public OrbitalBody getParent() {
        return parent;
    }
}
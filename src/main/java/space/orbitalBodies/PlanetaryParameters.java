package space.orbitalBodies;

import dimensions.distance.Distance;
import dimensions.mass.Mass;

//
public class PlanetaryParameters {
    private final Mass mass;
    private final Distance radius;

    //
    public PlanetaryParameters(Mass mass, Distance radius) {
        this.mass = mass;
        this.radius = radius;
    }

    //
    public Mass getMass() {
        return mass;
    }

    //
    public Distance getRadius() {
        return radius;
    }
}
package space.orbitalBodies;

import dimensions.distance.Distance;

//
public class PlanetaryParameters {
    private final Distance radius;

    //
    public PlanetaryParameters(Distance radius) {
        this.radius = radius;
    }

    //
    public Distance getRadius() {
        return radius;
    }
}
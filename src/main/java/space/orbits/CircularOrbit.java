package space.orbits;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.orbitalBodies.Planetoid;

//
public class CircularOrbit implements OrbitInterface {
    private static final double ECCENTRICITY = 0;
    private final @NotNull Distance radius;

    //
    public CircularOrbit(@NotNull Distance radius) {
        this.radius = radius;
    }

    //
    public CircularOrbit(@NotNull Planetoid planetoid, @NotNull Distance height) {
        this(getRadiusAboveSurface(planetoid, height));
    }

    private static @NotNull Distance getRadiusAboveSurface(@NotNull Planetoid planetoid, @NotNull Distance height) {
        DistanceUnit unit = DistanceUnit.KM; //base unit for conversion
        double radius = planetoid.planetaryParameters.getRadius().get(unit) + height.get(unit);
        return new Distance(radius, unit);
    }

    //
    @Override
    public Distance getSemiMajorAxis() {
        return radius;
    }

    //
    @Override
    public Distance getPeriapsis() {
        return radius;
    }

    //
    @Override
    public Distance getApoapsis() {
        return radius;
    }

    //
    @Override
    public double getEccentricity() {
        return ECCENTRICITY;
    }
}
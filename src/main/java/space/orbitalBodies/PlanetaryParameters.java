package space.orbitalBodies;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;
import dimensions.time.Time;

//
public class PlanetaryParameters {
    private final @NotNull MajorOrbitalBody major;
    private final @NotNull Distance radius;
    private final @NotNull Time rotationPeriod;

    //
    public PlanetaryParameters(@NotNull MajorOrbitalBody major, @NotNull Distance radius, @NotNull Time rotationPeriod) {
        this.major = major;
        this.radius = radius;
        this.rotationPeriod = rotationPeriod;
    }

    //
    public @NotNull Distance getRadius() {
        return radius;
    }

    //
    public @NotNull Time getRotationPeriod() {
        return rotationPeriod;
    }

    // m/s^2
    public double getSurfaceGravity() {
        // g = G*M / R^2
        return major.getSpecificGravity() / Math.pow(radius.getSI(), 2);
    }

    // 1/m
    public double getGravityGradient() {
        //h - reference height
        // r_g / h = ( g(R) - g(R+h) ) / g(R) / h
        // g = G*M / R^2
        // r_g / h  = (1/R^2 - 1/(R+h)^2) / (1/R^2) / h = (1 - (R / (R+h))^2) / h = (2*R*h + h^2) / (R+h))^2 / h
        // r_g / h = (2*R + h) / (R+h))^2
        double
                referenceHeightSI = 1,
                radiusSI = radius.getSI();
        return (2 * radiusSI + referenceHeightSI) / Math.pow(radiusSI + referenceHeightSI, 2);
    }
}
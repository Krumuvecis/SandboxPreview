package space.orbitalBodies;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import dimensions.time.TimeUnit;
import dimensions.time.Time;
import space.Orbit;

//
public abstract class OrbitalBody {
    private static final double GRAVITATIONAL_CONSTANT = 6.67430 * Math.pow(10, -11);
    private final @NotNull String name;
    private final @Nullable MajorOrbitalBody parent;
    public Orbit orbit;

    //
    OrbitalBody(@NotNull String name, @Nullable MajorOrbitalBody parent, Orbit orbit) {
        this.name = name;
        this.parent = parent;
        this.orbit = orbit;
    }

    public final @NotNull String getName() {
        return name;
    }

    public @Nullable MajorOrbitalBody getParent() {
        return parent;
    }

    //in rad/s
    public double getAngularVelocity() {
        //omega = v_t / R
        //v = (G * M / R)^(1/2)
        //omega = (G * M / R^3)^(1/2)
        try {
            @NotNull Mass parentMass = getParentMass();
            @NotNull Distance semiMajorAxis = getSemiMajorAxis();
            return calculateAngularVelocity(parentMass, semiMajorAxis);
        } catch (@NotNull OrbitalPeriodCalculationException e) {
            throw new RuntimeException(e);
        }
    }

    private static double calculateAngularVelocity(@NotNull Mass parentMass, @NotNull Distance semiMajorAxis) {
        return Math.sqrt(GRAVITATIONAL_CONSTANT * parentMass.get(MassUnit.KG) / Math.pow(semiMajorAxis.get(DistanceUnit.M), 3));
    }

    public @NotNull Time getOrbitalPeriod() {
        /*
        a_ct = v^2 / R
        a_ct = g = G * M / R^2
        v = (G * M / R)^(1/2)
        S = v * t => (2 * pi * R / T)^2 = G * M / R
        T^2 = 4 * pi^2 * R^3 / (G * M)
        T = 2 * pi * (R^3 / (G * M))^(1/2)
        */

        try {
            @NotNull Mass parentMass = getParentMass();
            @NotNull Distance semiMajorAxis = getSemiMajorAxis();
            return new Time(calculateOrbitalPeriod(parentMass, semiMajorAxis), TimeUnit.S);
        } catch (@NotNull OrbitalPeriodCalculationException e) {
            throw new RuntimeException(e);
        }
    }

    private @NotNull Mass getParentMass() throws OrbitalPeriodCalculationException {
        if (parent == null) {
            throw new OrbitalPeriodCalculationException("Null parent.");
        }
        @Nullable Mass parentMass = parent.getMass();
        if (parentMass == null) {
            throw new OrbitalPeriodCalculationException("Parent mass not defined.");
        }
        return parentMass;
    }

    private @NotNull Distance getSemiMajorAxis() throws OrbitalPeriodCalculationException {
        if (orbit == null) {
            throw new OrbitalPeriodCalculationException("Orbit not defined.");
        }
        @Nullable Distance semiMajorAxis = orbit.semiMajorAxis;
        if (semiMajorAxis == null) {
            throw new OrbitalPeriodCalculationException("Semi-major axis not defined.");
        }
        return semiMajorAxis;
    }

    private static double calculateOrbitalPeriod(@NotNull Mass parentMass, @NotNull Distance semiMajorAxis) {
        return 2 * Math.PI * Math.sqrt(Math.pow(semiMajorAxis.get(DistanceUnit.M), 3) / GRAVITATIONAL_CONSTANT / parentMass.get(MassUnit.KG));
    }

    private static final class OrbitalPeriodCalculationException extends Exception {
        OrbitalPeriodCalculationException(@NotNull String cause) {
            super(cause + " Unable to calculate orbital period.");
        }
    }
}
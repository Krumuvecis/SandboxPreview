package space.orbits;

import dimensions.time.Time;
import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;

//
public class HohmannTransferOrbit extends EllipticOrbit {
    //
    public HohmannTransferOrbit(@NotNull Distance periapsis, @NotNull Distance apoapsis) {
        super(periapsis, apoapsis);
    }

    //
    public Time getTransferTime(double parentSpecificGravity) {
        Time orbitalPeriod = getOrbitalPeriod(parentSpecificGravity);
        return new Time(orbitalPeriod.getValue() / 2, orbitalPeriod.getUnit());
    }

    //
    public double getVelocityAtPeriapsis(double parentSpecificGravity) {
        return getVelocity(parentSpecificGravity, getPeriapsis());
    }

    //
    public double getVelocityAtApoapsis(double parentSpecificGravity) {
        return getVelocity(parentSpecificGravity, getApoapsis());
    }

    private double getVelocity(double parentSpecificGravity, Distance radius) {
        return Math.sqrt(parentSpecificGravity * (2 / radius.getSI() - 1 / getSemiMajorAxis().getSI()));
    }

    //
    public double getDeltaVtoCircularAtPeriapsis(double parentSpecificGravity) {
        return getVelocityAtPeriapsis(parentSpecificGravity) - getCircularVelocityAtPeriapsis(parentSpecificGravity);
    }

    //
    public double getDeltaVtoCircularAtApoapsis(double parentSpecificGravity) {
        return getCircularVelocityAtApoapsis(parentSpecificGravity) - getVelocityAtApoapsis(parentSpecificGravity);
    }

    private double getCircularVelocityAtPeriapsis(double parentSpecificGravity) {
        return getCircularVelocity(parentSpecificGravity, getPeriapsis());
    }

    private double getCircularVelocityAtApoapsis(double parentSpecificGravity) {
        return getCircularVelocity(parentSpecificGravity, getApoapsis());
    }

    private static double getCircularVelocity(double parentSpecificGravity, Distance radius) {
        return Math.sqrt(parentSpecificGravity / radius.getSI());
    }
}
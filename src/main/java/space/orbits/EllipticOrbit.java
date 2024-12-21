package space.orbits;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;

//
public class EllipticOrbit implements OrbitInterface {
    private final Distance semiMajorAxis;
    private final Distance periapsis;
    private final Distance apoapsis;
    private final double eccentricity;

    //a generic orbit from semi-major axis and eccentricity
    public EllipticOrbit(Distance semiMajorAxis, double eccentricity) {
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        periapsis = new Distance((1 - eccentricity) * semiMajorAxis.getValue(), semiMajorAxis.getUnit());
        apoapsis = new Distance((1 + eccentricity) * semiMajorAxis.getValue(), semiMajorAxis.getUnit());
    }

    //a generic orbit from apsis
    public EllipticOrbit(Distance periapsis, Distance apoapsis) {
        this.periapsis = periapsis;
        this.apoapsis = apoapsis;
        @NotNull DistanceUnit unit;
        if (periapsis.getUnit() == apoapsis.getUnit()) {
            unit = periapsis.getUnit();

        } else {
            unit = periapsis.getUnitSI();
        }
        double
                rp = periapsis.get(unit),
                ra = apoapsis.get(unit),
                majorAxis = rp + ra;
        this.semiMajorAxis = new Distance(majorAxis / 2, unit);
        eccentricity = (ra - rp) / majorAxis;
    }

    //
    @Override
    public Distance getSemiMajorAxis() {
        return semiMajorAxis;
    }

    //
    @Override
    public Distance getPeriapsis() {
        return periapsis;
    }

    //
    @Override
    public Distance getApoapsis() {
        return apoapsis;
    }

    //
    @Override
    public double getEccentricity() {
        return eccentricity;
    }
}
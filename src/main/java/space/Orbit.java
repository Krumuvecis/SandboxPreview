package space;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import space.orbitalBodies.MajorOrbitalBody;

/**
 * Currently a crude and simple orbit.
 * TODO: still lacks:
 *  * argument of periapsis (for complete planar orientation)
 *  * inclination (for 3D orientation)
 *  * argument of ascending node (for 3D orientation)
 *  * true anomaly (for current position)
 */
public class Orbit {
    public Distance semiMajorAxis;
    public double eccentricity;

    //a generic orbit
    public Orbit(Distance semiMajorAxis, double eccentricity) {
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
    }

    //preserves units
    public Distance getPeriapsis() {
        return new Distance((1 - eccentricity) * semiMajorAxis.getValue(), semiMajorAxis.getUnit());
    }

    //preserves units
    public Distance getApoapsis() {
        return new Distance((1 + eccentricity) * semiMajorAxis.getValue(), semiMajorAxis.getUnit());
    }

    //prepares a generic circular orbit
    public static Orbit getCircularOrbit(Distance semiMajorAxis) {
        return new Orbit(semiMajorAxis, 0);
    }

    //prepares a generic circular orbit above some major body's surface
    public static Orbit getCircularOrbit(MajorOrbitalBody body, Distance height) {
        DistanceUnit unit = DistanceUnit.KM; //base unit for conversion
        double semiMajorAxis = body.planetaryParameters.getRadius().get(unit) + height.get(unit);
        return getCircularOrbit(new Distance(semiMajorAxis, unit));
    }

    //from apsis
    public static Orbit getEllipticOrbit(Distance periapsis, Distance apoapsis, DistanceUnit unit) {
        double
                basedPeriapsis = periapsis.get(unit),
                basedApoapsis = apoapsis.get(unit),
                majorAxis = basedPeriapsis + basedApoapsis,
                eccentricity = (basedPeriapsis - basedApoapsis) / majorAxis;
        return new Orbit(new Distance(majorAxis / 2, unit), eccentricity);
    }
}
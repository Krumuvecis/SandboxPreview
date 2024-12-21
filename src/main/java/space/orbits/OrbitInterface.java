package space.orbits;

import dimensions.distance.Distance;
import dimensions.time.Time;
import org.jetbrains.annotations.Nullable;

/**
 * Currently a crude and simple orbit.
 * TODO: still lacks:
 *  * argument of periapsis (for complete planar orientation)
 *  * inclination (for 3D orientation)
 *  * argument of ascending node (for 3D orientation)
 *  * true anomaly (for current position)
 */
public interface OrbitInterface {
    //
    Distance getSemiMajorAxis();

    /**
     * omega = v_t / R
     * v = (G * M / R)^(1/2)
     * omega = (G * M / R^3)^(1/2) = sqrt(mu / R3)
     *
     * @param parentSpecificGravity G * M of parent
     * @return Angular velocity in rad/s.
     */
    default double getAverageAngularVelocity(double parentSpecificGravity) {
        @Nullable Distance semiMajorAxis = getSemiMajorAxis();
        if (semiMajorAxis == null) {
            throw new RuntimeException("Semi-major axis not defined, unable to calculate angular velocity.");
        } else {
            double radiusCubed = Math.pow(getSemiMajorAxis().getSI(), 3);
            return Math.sqrt(parentSpecificGravity / radiusCubed);
        }
    }

    //in seconds
    //T = 2 * pi / omega
    default Time getOrbitalPeriod(double parentSpecificGravity) {
        double value = 2 * Math.PI / getAverageAngularVelocity(parentSpecificGravity);
        return new Time(value);
    }

    //
    Distance getPeriapsis();

    //
    Distance getApoapsis();

    //
    double getEccentricity();
}
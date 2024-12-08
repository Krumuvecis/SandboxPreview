package space.satelliteContainer;

import space.orbitalBodies.OrbitalBody;

//
public final class DuplicateSatelliteException extends Exception {
    DuplicateSatelliteException(OrbitalBody satellite) {
        super("Duplicate satellite: " + satellite.getName());
    }
}
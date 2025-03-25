package space.orbitalBodies.satelliteContainer;

import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;

//
public final class DuplicateSatelliteException extends Exception {
    DuplicateSatelliteException(AbstractOrbitalBody satellite) {
        super("Duplicate satellite: " + satellite.getName());
    }
}
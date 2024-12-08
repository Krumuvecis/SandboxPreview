package space.satelliteContainer;

import space.orbitalBodies.OrbitalBody;

//
public final class SatelliteNotFoundException extends Exception {
    SatelliteNotFoundException(OrbitalBody satellite) {
        super("Satellite not found: " + satellite.getName());
    }
}
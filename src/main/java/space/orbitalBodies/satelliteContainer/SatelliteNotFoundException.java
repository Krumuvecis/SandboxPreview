package space.orbitalBodies.satelliteContainer;

import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;

//
public final class SatelliteNotFoundException extends Exception {
    SatelliteNotFoundException(AbstractOrbitalBody satellite) {
        super("Satellite not found: " + satellite.getName());
    }
}
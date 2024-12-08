package space.satelliteContainer;

import java.util.List;
import java.util.ArrayList;

import space.orbitalBodies.OrbitalBody;

//
class TypedSatelliteContainer<T extends OrbitalBody> {
    private final List<T> satellites;

    //
    TypedSatelliteContainer() {
        satellites = new ArrayList<>();
    }

    //
    List<T> getSatellites() {
        return satellites;
    }

    //
    void add(T satellite) throws DuplicateSatelliteException {
        if (satellites.contains(satellite)) {
            throw new DuplicateSatelliteException(satellite);
        } else {
            satellites.add(satellite);
        }
    }

    //
    void remove(T satellite) throws SatelliteNotFoundException {
        if (!satellites.remove(satellite)) {
            throw new SatelliteNotFoundException(satellite);
        }
    }
}
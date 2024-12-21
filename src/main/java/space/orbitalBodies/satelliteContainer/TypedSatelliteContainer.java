package space.orbitalBodies.satelliteContainer;

import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;

//
class TypedSatelliteContainer<T extends AbstractOrbitalBody> {
    private final @NotNull List<@NotNull T> satellites;

    //
    TypedSatelliteContainer() {
        satellites = new ArrayList<>();
    }

    //
    @NotNull List<@NotNull T> getSatellites() {
        return satellites;
    }

    //adds a satellite
    void add(@NotNull T satellite) throws DuplicateSatelliteException {
        if (satellites.contains(satellite)) {
            throw new DuplicateSatelliteException(satellite);
        } else {
            satellites.add(satellite);
        }
    }

    //removes a satellite
    void remove(@NotNull T satellite) throws SatelliteNotFoundException {
        if (!satellites.remove(satellite)) {
            throw new SatelliteNotFoundException(satellite);
        }
    }
}
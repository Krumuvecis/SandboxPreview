package space.orbitalBodies.satelliteContainer;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.MinorOrbitalBody;

//
public final class SatelliteContainer implements SatelliteContainerInterface {
    private final @NotNull TypedSatelliteContainer<@NotNull MajorOrbitalBody> majorSatellites;
    private final @NotNull TypedSatelliteContainer<@NotNull MinorOrbitalBody> minorSatellites;

    //
    public SatelliteContainer() {
        majorSatellites = new TypedSatelliteContainer<>();
        minorSatellites = new TypedSatelliteContainer<>();
    }

    //
    @Override
    public @NotNull List<@NotNull MajorOrbitalBody> getMajorSatellites() {
        return majorSatellites.getSatellites();
    }

    //
    @Override
    public @NotNull List<@NotNull MinorOrbitalBody> getMinorSatellites() {
        return minorSatellites.getSatellites();
    }

    //adds a satellite
    @Override
    public void add(@NotNull AbstractOrbitalBody satellite) throws DuplicateSatelliteException {
        if (satellite instanceof MajorOrbitalBody majorSatellite) {
            majorSatellites.add(majorSatellite);
            return;
        }
        if (satellite instanceof MinorOrbitalBody minorSatellite) {
            minorSatellites.add(minorSatellite);
            return;
        }
        throw new RuntimeException(new UnhandledSatelliteTypeException("Unable to add a satellite."));
    }

    //removes a satellite
    @Override
    public void remove(@NotNull AbstractOrbitalBody satellite) throws SatelliteNotFoundException {
        if (satellite instanceof MajorOrbitalBody majorSatellite) {
            majorSatellites.remove(majorSatellite);
            return;
        }
        if (satellite instanceof MinorOrbitalBody minorSatellite) {
            minorSatellites.remove(minorSatellite);
            return;
        }
        throw new RuntimeException(new UnhandledSatelliteTypeException("Unable to remove a satellite."));
    }

    //
    private static final class UnhandledSatelliteTypeException extends Exception {
        //
        UnhandledSatelliteTypeException(@NotNull String text) {
            super("Unhandled orbital body type. " + text);
        }
    }
}
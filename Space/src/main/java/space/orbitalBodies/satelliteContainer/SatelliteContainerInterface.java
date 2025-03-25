package space.orbitalBodies.satelliteContainer;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import space.orbitalBodies.abstractOrbitalBody.AbstractOrbitalBody;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.MinorOrbitalBody;

//
public interface SatelliteContainerInterface {
    //
    @NotNull List<@NotNull MajorOrbitalBody> getMajorSatellites();

    //
    @NotNull List<@NotNull MinorOrbitalBody> getMinorSatellites();

    //adds a satellite
    void add(@NotNull AbstractOrbitalBody satellite) throws DuplicateSatelliteException;

    //removes a satellite
    void remove(@NotNull AbstractOrbitalBody satellite) throws SatelliteNotFoundException;
}
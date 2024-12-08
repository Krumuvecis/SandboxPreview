package space.satelliteContainer;

import java.util.List;

import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.MinorOrbitalBody;

//
public final class SatelliteContainer {
    private final TypedSatelliteContainer<MajorOrbitalBody> majorSatellites;
    private final TypedSatelliteContainer<MinorOrbitalBody> minorSatellites;

    //
    public SatelliteContainer() {
        this.majorSatellites = new TypedSatelliteContainer<>();
        this.minorSatellites = new TypedSatelliteContainer<>();
    }

    //
    public List<MajorOrbitalBody> getMajorSatellites() {
        return majorSatellites.getSatellites();
    }

    //
    public List<MinorOrbitalBody> getMinorSatellites() {
        return minorSatellites.getSatellites();
    }

    //
    public void add(MajorOrbitalBody satellite) throws DuplicateSatelliteException {
        majorSatellites.add(satellite);
    }

    //
    public void add(MinorOrbitalBody satellite) throws DuplicateSatelliteException {
        minorSatellites.add(satellite);
    }

    //
    public void remove(MajorOrbitalBody satellite) throws SatelliteNotFoundException {
        majorSatellites.remove(satellite);
    }

    //
    public void remove(MinorOrbitalBody satellite) throws SatelliteNotFoundException {
        minorSatellites.remove(satellite);
    }
}
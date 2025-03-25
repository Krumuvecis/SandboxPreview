package space.orbitalBodies.abstractOrbitalBody;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.distance.Distance;
import dimensions.time.Time;
import space.orbits.OrbitInterface;
import space.orbitalBodies.MajorOrbitalBody;

//
final class OrbitHandler {
    private @Nullable OrbitInterface orbit;

    //
    OrbitHandler(@Nullable OrbitInterface orbit) {
        this.orbit = orbit;
    }

    //
    @Nullable OrbitInterface getOrbit() {
        return orbit;
    }

    @NotNull OrbitInterface getNonNullOrbit() throws NullOrbitException {
        if (orbit == null) {
            throw new NullOrbitException();
        }
        return orbit;
    }

    //
    void setOrbit(@Nullable OrbitInterface orbit) {
        this.orbit = orbit;
    }

    //
    @Nullable Distance getSemiMajorAxis() throws NullOrbitException {
        return getNonNullOrbit().getSemiMajorAxis();
    }

    //in rad/s, for circular orbit
    double getAverageAngularVelocity(@NotNull MajorOrbitalBody parent) throws NullOrbitException {
        return getNonNullOrbit().getAverageAngularVelocity(parent.getSpecificGravity());
    }

    //in seconds, for circular orbit
    @NotNull Time getOrbitalPeriod(@NotNull MajorOrbitalBody parent) throws NullOrbitException {
        return getNonNullOrbit().getOrbitalPeriod(parent.getSpecificGravity());
    }
}
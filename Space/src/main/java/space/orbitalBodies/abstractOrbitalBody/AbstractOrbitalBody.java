package space.orbitalBodies.abstractOrbitalBody;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.distance.Distance;
import dimensions.mass.Mass;
import dimensions.time.Time;
import space.orbits.OrbitInterface;
import space.orbits.EllipticOrbit;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.satelliteContainer.DuplicateSatelliteException;

//
public abstract class AbstractOrbitalBody implements ParentInterface {
    private final @NotNull String name;
    private final @NotNull ParentHandler parentHandler;
    private final @NotNull OrbitHandler orbitHandler;

    //
    public AbstractOrbitalBody(@NotNull String name, @Nullable MajorOrbitalBody parent, @Nullable OrbitInterface orbit) {
        this.name = name;
        parentHandler = new ParentHandler(this, parent);
        orbitHandler = new OrbitHandler(orbit);
    }

    //
    public final @NotNull String getName() {
        return name;
    }

    //
    @Override
    public final @Nullable MajorOrbitalBody getParent() {
        return parentHandler.getParent();
    }

    //
    public final @NotNull MajorOrbitalBody getNonNullParent() throws NullParentException {
        return parentHandler.getNonNullParent();
    }

    /**
     * Sets a new parent, removes this from the old parent's satellites, adds this to the new parent's satellites.
     *
     * @param parent New parent.
     * @throws DuplicateSatelliteException If the new parent already contains this satellite.
     */
    @Override
    public final void setParent(@Nullable MajorOrbitalBody parent) throws DuplicateSatelliteException {
        parentHandler.setParent(parent);
    }

    //
    @Override
    public final @NotNull Mass getParentMass() throws NullParentException {
        return parentHandler.getParentMass();
    }

    //
    @Override
    public final double getParentSpecificGravity() throws NullParentException {
        return parentHandler.getParentSpecificGravity();
    }

    //
    public final @Nullable OrbitInterface getOrbit() {
        return orbitHandler.getOrbit();
    }

    //
    public final @NotNull OrbitInterface getNonNullOrbit() throws NullOrbitException {
        return orbitHandler.getNonNullOrbit();
    }

    //
    public final void setOrbit(@Nullable EllipticOrbit orbit) {
        orbitHandler.setOrbit(orbit);
    }

    //
    public final @NotNull Distance getNonNulSemiMajorAxis() {
        try {
            @Nullable Distance semiMajorAxis = orbitHandler.getSemiMajorAxis();
            if (semiMajorAxis == null) {
                throw new RuntimeException("Semi-major axis not defined.");
            }
            return semiMajorAxis;
        } catch (@NotNull NullOrbitException e) {
            throw new RuntimeException(e);
        }
    }

    //in rad/s
    public final double getAverageAngularVelocity() throws NullParentException, NullOrbitException {
        return orbitHandler.getAverageAngularVelocity(getNonNullParent());
    }

    //in seconds
    public final @NotNull Time getOrbitalPeriod() throws NullParentException, NullOrbitException {
        return orbitHandler.getOrbitalPeriod(getNonNullParent());
    }
}
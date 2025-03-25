package space.orbitalBodies.abstractOrbitalBody;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import space.orbitalBodies.satelliteContainer.DuplicateSatelliteException;
import space.orbitalBodies.MajorOrbitalBody;

public interface ParentInterface {
    //
    @Nullable MajorOrbitalBody getParent();

    /**
     * Sets a new parent, removes this from the old parent's satellites, adds this to the new parent's satellites.
     *
     * @param parent New parent.
     * @throws DuplicateSatelliteException If the new parent already contains this satellite.
     */
    void setParent(@Nullable MajorOrbitalBody parent) throws DuplicateSatelliteException;

    //
    @NotNull Mass getParentMass() throws NullParentException;

    //
    double getParentSpecificGravity() throws NullParentException;
}
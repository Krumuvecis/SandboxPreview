package space.orbitalBodies.abstractOrbitalBody;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import space.orbitalBodies.MajorOrbitalBody;
import space.orbitalBodies.satelliteContainer.DuplicateSatelliteException;
import space.orbitalBodies.satelliteContainer.SatelliteNotFoundException;

//
final class ParentHandler implements ParentInterface {
    private final @NotNull AbstractOrbitalBody body;
    private @Nullable MajorOrbitalBody parent;

    //
    ParentHandler(@NotNull AbstractOrbitalBody body, @Nullable MajorOrbitalBody parent) {
        this.body = body;
        try {
            setParent(parent);
        } catch (@NotNull DuplicateSatelliteException e) {
            throw new RuntimeException(e);
        }
    }

    //
    @Override
    public @Nullable MajorOrbitalBody getParent() {
        return parent;
    }

    //
    @NotNull MajorOrbitalBody getNonNullParent() throws NullParentException {
        if (parent == null) {
            throw new NullParentException();
        }
        return parent;
    }

    /**
     * Sets a new parent, removes this from the old parent's satellites, adds this to the new parent's satellites.
     *
     * @param parent New parent.
     * @throws DuplicateSatelliteException If the new parent already contains this satellite.
     */
    @Override
    public void setParent(@Nullable MajorOrbitalBody parent) throws DuplicateSatelliteException {
        removeFromParent();
        this.parent = parent;
        addToParent();
    }

    private void removeFromParent() {
        try {
            getNonNullParent().satellites.remove(body);
        } catch (@NotNull NullParentException | SatelliteNotFoundException ignored) {}
    }

    private void addToParent() throws DuplicateSatelliteException {
        try {
            getNonNullParent().satellites.add(body);
        } catch (@NotNull NullParentException ignored) {}
    }

    //
    @Override
    public @NotNull Mass getParentMass() throws NullParentException {
        return getNonNullParent().getMass();
    }

    //
    @Override
    public double getParentSpecificGravity() throws NullParentException {
        return getNonNullParent().getSpecificGravity();
    }
}
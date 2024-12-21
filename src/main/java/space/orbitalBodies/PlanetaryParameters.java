package space.orbitalBodies;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.Distance;

//
@SuppressWarnings("ClassCanBeRecord")
public class PlanetaryParameters {
    private final @NotNull Distance radius;

    //
    public PlanetaryParameters(@NotNull Distance radius) {
        this.radius = radius;
    }

    //
    public @NotNull Distance getRadius() {
        return radius;
    }
}
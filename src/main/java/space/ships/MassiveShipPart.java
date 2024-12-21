package space.ships;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

public interface MassiveShipPart {
    @NotNull Mass getMass();
}
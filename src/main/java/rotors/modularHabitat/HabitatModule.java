package rotors.modularHabitat;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

//
public abstract class HabitatModule {
    private final @NotNull Color color;

    //
    public HabitatModule(@NotNull Color color) {
        this.color = color;
    }

    //
    public abstract @NotNull HabitatModule copy();

    //for graphical purposes
    public @NotNull Color getColor() {
        return color;
    }
}
package rotors.modularHabitat;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import basicParts.MassivePart;
import utils.Copyable;

//
public abstract class HabitatModule implements MassivePart, Copyable<@NotNull HabitatModule> {
    private final @NotNull Color color;

    //
    public HabitatModule(@NotNull Color color) {
        this.color = color;
    }

    //for graphical purposes
    public @NotNull Color getColor() {
        return color;
    }
}
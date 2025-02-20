package rotors.modularHabitat.habitatSection;

import org.jetbrains.annotations.NotNull;

import rotors.modularHabitat.HabitatSectionCell;

//
public interface CellActionInterface {
    //
    void action(@NotNull HabitatSectionCell cell, int lengthIndex, int widthIndex);
}
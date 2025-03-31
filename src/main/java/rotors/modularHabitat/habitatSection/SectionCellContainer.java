package rotors.modularHabitat.habitatSection;

import java.util.Arrays;

import basicParts.MassivePart;
import dimensions.mass.Mass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.modularHabitat.HabitatSectionCell;

//
class SectionCellContainer implements MassivePart {
    private final int width, length; //cached
    private final @NotNull HabitatSectionCell @NotNull [] @NotNull [] cells;

    //
    SectionCellContainer(int rowCount, int columnCount) {
        width = rowCount;
        length = columnCount;
        cells = new HabitatSectionCell[length][width]; //list of columns
        //populate cells
        for (@NotNull HabitatSectionCell @NotNull [] column : cells) {
            Arrays.setAll(column, i -> new HabitatSectionCell());
        }
    }

    //
    final @NotNull HabitatSectionCell getCell(int lengthIndex, int widthIndex) {
        return getColumn(lengthIndex)[widthIndex];
    }

    //
    private @NotNull HabitatSectionCell @NotNull [] getColumn(int index) {
        return cells[index];
    }

    //
    final void iterate(@Nullable CellActionInterface action) {
        if (action != null) {
            for (int lengthIndex = 0; lengthIndex < length; lengthIndex ++) {
                for (int widthIndex = 0; widthIndex < width; widthIndex ++) {
                    action.action(getCell(lengthIndex, widthIndex), lengthIndex, widthIndex);
                }
            }
        }
    }

    //
    @Override
    public @NotNull Mass getMass() {
        final @NotNull Mass sum = new Mass(0);
        iterate(new CellActionInterface() {
            @Override
            public void action(@NotNull HabitatSectionCell cell, int lengthIndex, int widthIndex) {
                sum.sum(cell.getMass());
            }
        });
        return sum;
    }
}
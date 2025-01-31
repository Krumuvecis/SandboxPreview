package rotors.habitatEditor.mainPanel;

import java.awt.Color;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.modularHabitat.HabitatModule;
import rotors.modularHabitat.HabitatSectionCell;

//
interface HabitatSectionCellPainter extends BorderAndOverlayPainter {
    int
            CELL_BORDER_INSET = 2,
            CELL_CONTENTS_INSET = 3;
    @NotNull Color INACTIVE_CELL_BORDER_COLOR = Color.darkGray;

    //
    default void paintCell(@NotNull Graphics g, double @NotNull [] paintLocation, double @NotNull [] paintSize,
                           @NotNull HabitatSectionCell cell, boolean activeCell) {
        paintCellContents(g, paintLocation, paintSize, cell);
        paintBorderAndOverlay(g, paintLocation, paintSize, CELL_BORDER_INSET, activeCell, INACTIVE_CELL_BORDER_COLOR);
    }

    private void paintCellContents(@NotNull Graphics g, double @NotNull [] paintLocation, double @NotNull [] paintSize,
                                   @NotNull HabitatSectionCell cell) {
        @Nullable HabitatModule module = cell.getModule();
        if (module != null) { //paints a non-null cell
            g.setColor(module.getColor());
            g.fillRect(
                    (int) (paintLocation[0] + CELL_CONTENTS_INSET), (int) (paintLocation[1] + CELL_CONTENTS_INSET),
                    (int) (paintSize[0] - CELL_CONTENTS_INSET * 2),
                    (int) (paintSize[1] - CELL_CONTENTS_INSET * 2));
        }
    }
}
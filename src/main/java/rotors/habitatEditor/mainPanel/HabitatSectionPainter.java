package rotors.habitatEditor.mainPanel;

import java.awt.Color;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.modularHabitat.HabitatSectionCell;
import rotors.modularHabitat.HabitatSection;
import rotors.habitatEditor.window.UserData;

//
interface HabitatSectionPainter extends HabitatSectionCellPainter{
    int SECTION_BORDER_INSET = 1;
    @NotNull Color INACTIVE_SECTION_BORDER_COLOR = Color.gray;

    //
    default void paintSection(@NotNull Graphics g, double sectionStartX_drawable, double sectionCenterY_drawable,
                              double scale, @NotNull HabitatSection section, @NotNull UserData.EditMode editMode,
                              boolean activeSection, int @Nullable [] activeCellLocation) {
        int
                sectionLengthCells = section.getColumnCount(),
                sectionWidthCells = section.getRowCount();
        double
                sectionLengthDraw = sectionLengthCells * HabitatSectionCell.CELL_SIZE.getSI() * scale,
                sectionWidthDraw = sectionWidthCells * HabitatSectionCell.CELL_SIZE.getSI() * scale,
                drawY = sectionCenterY_drawable - sectionWidthDraw / 2;
        double @NotNull []
                paintLocation = new double[] {sectionStartX_drawable, drawY},
                paintSize = new double[] {sectionLengthDraw, sectionWidthDraw};
        paintCellGrid(g, paintLocation, scale, section, editMode, activeSection, activeCellLocation);

        boolean active = editMode == UserData.EditMode.EDIT_SECTIONS && activeSection;
        paintBorderAndOverlay(g, paintLocation, paintSize, SECTION_BORDER_INSET, active, INACTIVE_SECTION_BORDER_COLOR);
    }

    private void paintCellGrid(@NotNull Graphics g, double @NotNull [] sectionLocationDrawable,
                               double scale, @NotNull HabitatSection section, @NotNull UserData.EditMode editMode,
                               boolean activeSection, int @Nullable [] activeCellLocation) {
        for (int i = 0; i < section.getColumnCount(); i++) {
            @NotNull HabitatSectionCell @NotNull [] column = section.getColumn(i);
            for (int j = 0; j < column.length; j++) {
                @NotNull HabitatSectionCell cell = column[j];

                //determines cell's draw location
                double scaledCellSize = HabitatSectionCell.CELL_SIZE.getSI() * scale;
                double @NotNull []
                        paintLocation = new double[] {
                                sectionLocationDrawable[0] + scaledCellSize * i,
                                sectionLocationDrawable[1] + scaledCellSize * j
                        },
                        paintSize = new double[] {scaledCellSize, scaledCellSize};

                boolean activeCell = isActiveCell(activeSection, editMode, activeCellLocation, i, j);
                paintCell(g, paintLocation, paintSize, cell, activeCell);
            }
        }
    }

    private boolean isActiveCell(boolean activeSection, @NotNull UserData.EditMode editMode, int @Nullable [] activeCellLocation,
                                 int lengthIndex, int widthIndex) {
        return activeSection
                && (editMode == UserData.EditMode.EDIT_STRUCTURE || editMode == UserData.EditMode.EDIT_MODULES)
                && activeCellLocation != null
                && lengthIndex == activeCellLocation[1]
                && widthIndex == activeCellLocation[2];
    }
}
package rotors.habitatEditor.mainPanel.sectionPainting;

import java.awt.Color;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.habitatEditor.mainPanel.sectionPainting.sectionInfo.SectionInfoPainter;
import rotors.rotatingHabitat.modularHabitat.HabitatSectionCell;
import rotors.rotatingHabitat.modularHabitat.habitatSection.HabitatSection;
import rotors.habitatEditor.window.UserData;

//
public interface HabitatSectionPainter extends CellPainter, SectionInfoPainter {
    int SECTION_BORDER_INSET = 1;
    @NotNull Color INACTIVE_SECTION_BORDER_COLOR = Color.gray;
    int @NotNull [] SECTION_INFO_OFFSET = new int[] {10, 20}; //relative to the bottom-left corner of the section

    //
    default void paintSection(@NotNull Graphics g, double sectionStartX_drawable, double sectionCenterY_drawable,
                              double scale, @NotNull HabitatSection section,
                              @NotNull UserData.InfoDisplayAmount infoDisplayAmount, @NotNull UserData.HabitatTabEnum activeTab,
                              boolean activeSection, int @Nullable [] activeCellLocation) {
        int @NotNull [] sectionSize = section.getSize();
        double
                sectionLengthDraw = sectionSize[0] * HabitatSectionCell.CELL_SIZE.getInBase() * scale,
                sectionWidthDraw = sectionSize[1] * HabitatSectionCell.CELL_SIZE.getInBase() * scale,
                drawY = sectionCenterY_drawable - sectionWidthDraw / 2;
        double @NotNull [] paintLocation = new double[] {sectionStartX_drawable, drawY};
        paintCellGrid(g, paintLocation, scale, section, activeTab, activeSection, activeCellLocation);

        double @NotNull [] paintSize = new double[] {sectionLengthDraw, sectionWidthDraw};
        boolean active = (activeTab == UserData.HabitatTabEnum.SECTIONS) && activeSection;
        paintBorderAndOverlay(g, paintLocation, paintSize, SECTION_BORDER_INSET, active, INACTIVE_SECTION_BORDER_COLOR);

        int @NotNull [] sectionInfoPaintLocation = getSectionInfoPaintLocation(paintLocation, paintSize[1]);
        paintSectionInfo(g, sectionInfoPaintLocation, section, infoDisplayAmount);
    }

    private void paintCellGrid(@NotNull Graphics g, double @NotNull [] sectionLocationDrawable,
                               double scale, @NotNull HabitatSection section, @NotNull UserData.HabitatTabEnum activeTab,
                               boolean isActiveSection, int @Nullable [] activeCellLocation) {
        section.iterateCells((cell, lengthIndex, widthIndex) -> { //lambda
            //determines cell's draw location
            double scaledCellSize = HabitatSectionCell.CELL_SIZE.getInBase() * scale;
            double @NotNull []
                    paintLocation = new double[] {
                            sectionLocationDrawable[0] + scaledCellSize * lengthIndex,
                            sectionLocationDrawable[1] + scaledCellSize * widthIndex
                    },
                    paintSize = new double[] {scaledCellSize, scaledCellSize};

            boolean isActiveCell = isActiveCell(isActiveSection, activeTab, activeCellLocation, lengthIndex, widthIndex);
            paintCell(g, paintLocation, paintSize, cell, isActiveCell);
        });
    }

    private boolean isActiveCell(boolean activeSection, @NotNull UserData.HabitatTabEnum activeTab,
                                 int @Nullable [] activeCellLocation,
                                 int lengthIndex, int widthIndex) {
        return activeSection
                && (activeTab == UserData.HabitatTabEnum.CELLS || activeTab == UserData.HabitatTabEnum.MODULES)
                && isActiveCellLocation(activeCellLocation, lengthIndex, widthIndex);
    }

    private boolean isActiveCellLocation(int @Nullable [] activeCellLocation, int lengthIndex, int widthIndex) {
        return activeCellLocation != null
                && lengthIndex == activeCellLocation[1]
                && widthIndex == activeCellLocation[2];
    }

    private int @NotNull [] getSectionInfoPaintLocation(double @NotNull [] sectionPaintLocation,
                                                        double sectionPaintHeight) {
        return new int[] {
                (int) (sectionPaintLocation[0] + SECTION_INFO_OFFSET[0]),
                (int) (sectionPaintLocation[1] + sectionPaintHeight + SECTION_INFO_OFFSET[1])
        };
    }
}
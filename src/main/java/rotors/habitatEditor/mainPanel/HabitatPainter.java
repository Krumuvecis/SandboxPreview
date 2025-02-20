package rotors.habitatEditor.mainPanel;

import java.util.List;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.modularHabitat.HabitatSectionCell;
import rotors.modularHabitat.habitatSection.HabitatSection;
import rotors.modularHabitat.ModularHabitat;
import rotors.habitatEditor.window.UserData;
import rotors.habitatEditor.mainPanel.sectionPainting.HabitatSectionPainter;

//
interface HabitatPainter extends HabitatSectionPainter {
    //
    @SuppressWarnings("SameParameterValue")
    default void paintHabitat(@NotNull Graphics g, @NotNull UserData.HabitatTabData habitatTabData, double @NotNull [] panelCenter, double scale,
                              double @NotNull [] viewLocationActual, @NotNull ModularHabitat habitat) {
        @NotNull List<@NotNull HabitatSection> sections = habitat.getSections();
        for (int i = 0; i < sections.size(); i++) {
            double
                    sectionStartX_actualAbsolute = habitat.getLength(i - 1) * HabitatSectionCell.CELL_SIZE.getSI(),
                    sectionStartX_actualRelative = sectionStartX_actualAbsolute - viewLocationActual[0],
                    sectionCenterY_actualRelative = -viewLocationActual[1],

                    sectionStartX_drawable = panelCenter[0] + sectionStartX_actualRelative * scale,
                    sectionCenterY_drawable = panelCenter[1] + sectionCenterY_actualRelative * scale;

            @NotNull HabitatSection section = sections.get(i);
            int @Nullable [] activeCellLocation = habitatTabData.getActiveCellLocation();
            boolean activeSection = isActiveSection(activeCellLocation, i);
            paintSection(g, sectionStartX_drawable, sectionCenterY_drawable, scale, section,
                    habitatTabData.getSectionInfoDisplayAmount().getValue(), habitatTabData.getActiveTab(),
                    activeSection, activeCellLocation);
        }
    }

    private boolean isActiveSection(int @Nullable [] activeCellLocation, int index) {
        return activeCellLocation != null && index == activeCellLocation[0];
    }
}
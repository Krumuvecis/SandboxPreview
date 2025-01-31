package rotors.habitatEditor.mainPanel;

import java.util.List;
import java.awt.Point;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import rotors.modularHabitat.HabitatModule;
import rotors.modularHabitat.HabitatSectionCell;
import rotors.modularHabitat.HabitatSection;
import rotors.modularHabitat.ModularHabitat;
import rotors.habitatEditor.window.UserData;
import rotors.habitatEditor.window.ObserverData;

//
final class MainPanelMouseListener extends AbstractMouseListener {
    private final @NotNull ModularHabitat habitat;
    private final @NotNull UserData userData;
    private final @NotNull ObserverData observerData;
    private final @NotNull MainPanel mainPanel;
    private int
            mouseX,
            mouseY;

    //
    MainPanelMouseListener(@NotNull ModularHabitat habitat, @NotNull UserData userData,
                           @NotNull ObserverData observerData, @NotNull MainPanel mainPanel) {
        super();
        this.habitat = habitat;
        this.userData = userData;
        this.observerData = observerData;
        this.mainPanel = mainPanel;
        mouseX = 0;
        mouseY = 0;
    }

    //
    public int getMouseX() {
        return mouseX;
    }

    //
    public int getMouseY() {
        return mouseY;
    }

    //
    public int @NotNull [] getMouseLocation() {
        return new int[] {mouseX, mouseY};
    }

    double @NotNull [] getMouseLocation_relativeScaled() {
        double @NotNull [] panelCenter = mainPanel.getCenter();
        return new double[] {
                mouseX - panelCenter[0],
                mouseY - panelCenter[1]};
    }

    double @NotNull [] getMouseLocation_absoluteUnscaled() {
        return observerData.getOffsetLocationActual(getMouseLocation_relativeScaled());
    }

    //gets called when the mouse is clicked
    @Override
    void mouseClickedAction(@NotNull Point location, int button) {
        mouseMovedAction(location);
        printLine("mouseClicked, button: " + button + ", x: " + mouseX + ", y: " + mouseY);
        switch (button) {
            case 1 -> leftClickAction();
            case 2 -> { //middle
                //TODO: handle middle-click actions here
            }
            case 3 -> { //right
                //TODO: handle right-click actions here
            }
            default -> printLine("unrecognized mouse button");
        }
    }

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    private void leftClickAction() {
        int @Nullable [] activeCellLocation = userData.getActiveCellLocation();
        if (activeCellLocation != null) {
            @NotNull UserData.EditModeData editModeData = userData.getEditModeData();
            switch (editModeData.getEditMode()) {
                case EDIT_MODULES -> leftClickAction_editModule(editModeData, activeCellLocation);
                //TODO: add more edit modes here
                default -> {}
            }
        }
    }

    private void leftClickAction_editModule(@NotNull UserData.EditModeData editModeData, int @NotNull [] activeCellLocation) {
        @NotNull HabitatSectionCell cell = habitat.getSection(activeCellLocation[0]).getCell(activeCellLocation[1], activeCellLocation[2]);
        @Nullable HabitatModule module = editModeData.getModuleByTemplate();
        if (module == null) {
            cell.removeModule();
        } else {
            try {
                cell.setModule(module);
            } catch (@NotNull HabitatSectionCell.CellTakenException e) {
                printLine(e.getMessage());
            }
        }
    }

    //gets called when the mouse is moved
    @Override
    void mouseMovedAction(@NotNull Point location) {
        mouseX = location.x;
        mouseY = location.y;
        checkActiveCell();
    }

    private void checkActiveCell() {
        double @NotNull [] mouseActualLocation_absolute = getMouseLocation_absoluteUnscaled();
        int activeSectionIndex = getActiveSectionIndex(mouseActualLocation_absolute);
        if (activeSectionIndex < 0) {
            userData.resetActiveCellLocation();
        } else {
            //is within a section, find active cell
            @NotNull List<@NotNull HabitatSection> sections = habitat.getSections();
            @NotNull HabitatSection section = sections.get(activeSectionIndex);
            double
                    cellSize = HabitatSectionCell.CELL_SIZE.getSI(),
                    sectionStartX_actual = habitat.getLength(activeSectionIndex - 1) * cellSize,
                    sectionWidth_actual = section.getRowCount() * cellSize,
                    sectionStartY_actual = -sectionWidth_actual / 2;
            double @NotNull [] mouseActualLocation_relative = new double[] {
                    mouseActualLocation_absolute[0] - sectionStartX_actual,
                    mouseActualLocation_absolute[1] - sectionStartY_actual};
            int @NotNull [] estimatedIndexes = new int[] {
                    (int) Math.floor(mouseActualLocation_relative[0] / cellSize),
                    (int) Math.floor(mouseActualLocation_relative[1] / cellSize)};
            userData.setActiveCellLocation(activeSectionIndex, estimatedIndexes[0], estimatedIndexes[1]);
        }
    }

    //by both x and y; -1 means not found
    private int getActiveSectionIndex(double @NotNull [] mouseActualLocation_absolute) {
        int activeSectionIndex_byX = getActiveSectionIndex_byX(mouseActualLocation_absolute);
        if (activeSectionIndex_byX >= 0) {
            @NotNull List<@NotNull HabitatSection> sections = habitat.getSections();
            @NotNull HabitatSection section = sections.get(activeSectionIndex_byX);
            double
                    sectionWidth_actual = section.getRowCount() * HabitatSectionCell.CELL_SIZE.getSI(),
                    sectionStartY_actual = -sectionWidth_actual / 2;
            if (mouseActualLocation_absolute[1] >= sectionStartY_actual
                    && mouseActualLocation_absolute[1] < sectionStartY_actual + sectionWidth_actual) {
                return activeSectionIndex_byX;
            }
        }
        return -1;
    }

    //-1 means not found
    private int getActiveSectionIndex_byX(double @NotNull [] mouseActualLocation_absolute) {
        if (mouseActualLocation_absolute[0] >= 0) { //negative x-coordinates not supported
            @NotNull List<@NotNull HabitatSection> sections = habitat.getSections();
            for (int sectionIndex = 0; sectionIndex < sections.size(); sectionIndex ++) {
                double
                        cellSize = HabitatSectionCell.CELL_SIZE.getSI(),
                        sectionStartX = habitat.getLength(sectionIndex - 1) * cellSize,
                        sectionEndX = habitat.getLength(sectionIndex) * cellSize;
                if (mouseActualLocation_absolute[0] >= sectionStartX) {
                    if (mouseActualLocation_absolute[0] < sectionEndX) {
                        return sectionIndex;
                    }
                }
            }
        }
        return -1;
    }
}
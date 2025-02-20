package rotors.habitatEditor.mainPanel;

import java.util.List;
import java.awt.Point;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import rotors.modularHabitat.HabitatModule;
import rotors.modularHabitat.HabitatSectionCell;
import rotors.modularHabitat.habitatSection.HabitatSection;
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
        printLine("Mouse clicked, button: " + button + ", x: " + mouseX + ", y: " + mouseY); //for debugging, removable
        switch (button) {
            case 1 -> leftClickAction();
            case 2 -> { //middle
                //TODO: handle middle-click actions here
            }
            case 3 -> { //right
                //TODO: handle right-click actions here
            }
            default -> printLine("Unrecognized mouse button.");
        }
    }

    private void leftClickAction() {
        @NotNull UserData.HabitatSystemTabData habitatSystemTabData = userData.getTabData();
        switch (habitatSystemTabData.getActiveTab()) {
            case OVERALL -> {
                @NotNull UserData.HabitatSystemOverallTabData overallTabData = habitatSystemTabData.getOverallTabData();
                //TODO: add actions here, as needed
            }
            case ROTORS -> {
                @NotNull UserData.RotorsTabData rotorsTabData = habitatSystemTabData.getRotorsTabData();
                switch (rotorsTabData.getActiveTab()) {
                    case AXIS -> {
                        @NotNull UserData.AxisTabData axisTabData = rotorsTabData.getAxisTabData();
                        //TODO: add actions here, as needed
                    }
                    case HABITAT -> {
                        @NotNull UserData.HabitatTabData habitatTabData = rotorsTabData.getHabitatTabData();
                        int @Nullable [] activeCellLocation = habitatTabData.getActiveCellLocation();
                        if (activeCellLocation != null) {
                            switch (habitatTabData.getActiveTab()) {
                                case SECTIONS -> leftClickAction_sectionsTab(activeCellLocation,
                                        habitatTabData.getSectionsTabData());
                                case CELLS -> leftClickAction_cellsTab(activeCellLocation,
                                        habitatTabData.getCellsTabData());
                                case MODULES -> leftClickAction_modulesTab(activeCellLocation,
                                        habitatTabData.getModulesTabData());
                                default -> {
                                    //unrecognized habitat sub-tab
                                }
                            }
                        }
                    }
                    case BALANCING -> {
                        @NotNull UserData.BalancingTabData balancingTabData = rotorsTabData.getBalancingTabData();
                        //TODO: add actions here, as needed
                    }
                    default -> {
                        //unrecognized rotors sub-tab
                    }
                }
            }
            case MISCELLANEOUS -> {
                @NotNull UserData.HabitatSystemMiscellaneousTabData
                        miscellaneousTabData = habitatSystemTabData.getMiscellaneousTabData();
                //TODO: add actions here, as needed
            }
            default -> {
                //unrecognized habitat system sub-tab
            }
        }
    }

    private void leftClickAction_sectionsTab(int @NotNull [] activeCellLocation,
                                             @NotNull UserData.SectionsTabData sectionsTabData) {
        //TODO: add actions here, as needed
    }

    private void leftClickAction_cellsTab(int @NotNull [] activeCellLocation,
                                          @NotNull UserData.CellsTabData cellsTabData) {
        @NotNull HabitatSectionCell
                cell = habitat.getSection(activeCellLocation[0]).getCell(activeCellLocation[1], activeCellLocation[2]);
        @Nullable HabitatModule module = cellsTabData.getModuleByTemplate();
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

    private void leftClickAction_modulesTab(int @NotNull [] activeCellLocation,
                                            @NotNull UserData.ModulesTabData modulesTabData) {
        //TODO: add actions here, as needed
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
        @NotNull UserData.HabitatTabData habitatTabData = userData.getTabData().getRotorsTabData().getHabitatTabData();
        if (activeSectionIndex < 0) {
            habitatTabData.resetActiveCellLocation();
        } else {
            //is within a section, find active cell
            @NotNull List<@NotNull HabitatSection> sections = habitat.getSections();
            @NotNull HabitatSection section = sections.get(activeSectionIndex);
            double
                    cellSize = HabitatSectionCell.CELL_SIZE.getSI(),
                    sectionStartX_actual = habitat.getLength(activeSectionIndex - 1) * cellSize,
                    sectionWidth_actual = section.getSize()[1] * cellSize,
                    sectionStartY_actual = -sectionWidth_actual / 2;
            double @NotNull [] mouseActualLocation_relative = new double[] {
                    mouseActualLocation_absolute[0] - sectionStartX_actual,
                    mouseActualLocation_absolute[1] - sectionStartY_actual};
            int @NotNull [] estimatedIndexes = new int[] {
                    (int) Math.floor(mouseActualLocation_relative[0] / cellSize),
                    (int) Math.floor(mouseActualLocation_relative[1] / cellSize)};
            habitatTabData.setActiveCellLocation(
                    activeSectionIndex, estimatedIndexes[0], estimatedIndexes[1]);
        }
    }

    //by both x and y; -1 means not found
    private int getActiveSectionIndex(double @NotNull [] mouseActualLocation_absolute) {
        int activeSectionIndex_byX = getActiveSectionIndex_byX(mouseActualLocation_absolute);
        if (activeSectionIndex_byX >= 0) {
            @NotNull List<@NotNull HabitatSection> sections = habitat.getSections();
            @NotNull HabitatSection section = sections.get(activeSectionIndex_byX);
            double
                    sectionWidth_actual = section.getSize()[1] * HabitatSectionCell.CELL_SIZE.getSI(),
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
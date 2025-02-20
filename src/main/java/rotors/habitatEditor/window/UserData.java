package rotors.habitatEditor.window;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.modularHabitat.HabitatModule;
import rotors.modularHabitat.HabitatSectionCell;
import rotors.modularHabitat.habitatSection.HabitatSection;
import rotors.modularHabitat.particularModules.SampleModule_medium;
import utils.DropdownData;
import utils.DropdownableInterface;

//
public final class UserData {
    private final @NotNull HabitatSystemTabData tabData;

    //
    UserData() {
        tabData = new HabitatSystemTabData();
    }

    //
    public @NotNull HabitatSystemTabData getTabData() {
        return tabData;
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public enum InfoDisplayAmount implements DropdownableInterface {
        NONE("None"),
        MINIMAL("Minimal"),
        NORMAL("Normal"),
        FULL("Full");

        private final @NotNull String displayText;

        InfoDisplayAmount(@NotNull String displayText) {
            this.displayText = displayText;
        }

        //for graphical purposes
        @Override
        public final @NotNull String toString() {
            return displayText;
        }

        //
        @Override
        public @NotNull DropdownableInterface @NotNull [] getAll() {
            return InfoDisplayAmount.values();
        }
    }

    //
    public interface TabSelectionEnumInterface {}

    //
    public static abstract class AbstractTabContainerData<T extends @NotNull TabSelectionEnumInterface> {
        private final T defaultTab;
        private T activeTab;

        //
        AbstractTabContainerData(T defaultTab) {
            this.defaultTab = defaultTab;
            activeTab = defaultTab;
        }

        //
        public T getDefaultTab() {
            return defaultTab;
        }

        //
        public T getActiveTab() {
            return activeTab;
        }

        //null - default
        public void setActiveTab(@Nullable T activeTab) {
            this.activeTab = Objects.requireNonNullElse(activeTab, defaultTab);
        }
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class HabitatSystemTabData extends AbstractTabContainerData<@NotNull HabitatSystemTabEnum> {
        private static final @NotNull HabitatSystemTabEnum DEFAULT_TAB = HabitatSystemTabEnum.ROTORS;
        private final @NotNull HabitatSystemOverallTabData overallTabData;
        private final @NotNull RotorsTabData rotorsTabData;
        private final @NotNull HabitatSystemMiscellaneousTabData miscellaneousTabData;

        HabitatSystemTabData() {
            super(DEFAULT_TAB);
            overallTabData = new HabitatSystemOverallTabData();
            rotorsTabData = new RotorsTabData();
            miscellaneousTabData = new HabitatSystemMiscellaneousTabData();
        }

        public @NotNull HabitatSystemOverallTabData getOverallTabData() {
            return overallTabData;
        }

        public @NotNull RotorsTabData getRotorsTabData() {
            return rotorsTabData;
        }

        public @NotNull HabitatSystemMiscellaneousTabData getMiscellaneousTabData() {
            return miscellaneousTabData;
        }
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public enum HabitatSystemTabEnum implements TabSelectionEnumInterface {
        OVERALL,
        ROTORS,
        MISCELLANEOUS
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class HabitatSystemOverallTabData {
        //add stuff here
        HabitatSystemOverallTabData() {}
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class RotorsTabData extends AbstractTabContainerData<@NotNull RotorsTabEnum> {
        private static final @NotNull RotorsTabEnum DEFAULT_TAB = RotorsTabEnum.HABITAT;
        private final @NotNull AxisTabData axisTabData;
        private final @NotNull HabitatTabData habitatTabData;
        private final @NotNull BalancingTabData balancingTabData;

        RotorsTabData() {
            super(DEFAULT_TAB);
            axisTabData = new AxisTabData();
            habitatTabData = new HabitatTabData();
            balancingTabData = new BalancingTabData();
        }

        public @NotNull AxisTabData getAxisTabData() {
            return axisTabData;
        }

        public @NotNull HabitatTabData getHabitatTabData() {
            return habitatTabData;
        }

        public @NotNull BalancingTabData getBalancingTabData() {
            return balancingTabData;
        }
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public enum RotorsTabEnum implements TabSelectionEnumInterface {
        AXIS,
        HABITAT,
        BALANCING
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class AxisTabData {
        //add stuff here
        AxisTabData() {}
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class HabitatTabData extends AbstractTabContainerData<@NotNull HabitatTabEnum> {
        private static final @NotNull InfoDisplayAmount DEFAULT_SECTION_INFO_DISPLAY_AMOUNT = InfoDisplayAmount.NORMAL;
        private static final @NotNull HabitatTabEnum DEFAULT_TAB = HabitatTabEnum.CELLS;
        private final @NotNull DropdownData<@NotNull InfoDisplayAmount> sectionInfoDisplayAmount;
        private final @NotNull SectionsTabData sectionsTabData;
        private final @NotNull CellsTabData cellsTabData;
        private final @NotNull ModulesTabData modulesTabData;
        private int @Nullable [] activeCellLocation; // {section, x, y}

        HabitatTabData() {
            super(DEFAULT_TAB);
            sectionInfoDisplayAmount = new DropdownData<>(DEFAULT_SECTION_INFO_DISPLAY_AMOUNT);
            sectionsTabData = new SectionsTabData();
            cellsTabData = new CellsTabData();
            modulesTabData = new ModulesTabData();
            activeCellLocation = null;
        }

        //
        public @NotNull DropdownData<@NotNull InfoDisplayAmount> getSectionInfoDisplayAmount() {
            return sectionInfoDisplayAmount;
        }

        public @NotNull SectionsTabData getSectionsTabData() {
            return sectionsTabData;
        }

        public @NotNull CellsTabData getCellsTabData() {
            return cellsTabData;
        }

        public @NotNull ModulesTabData getModulesTabData() {
            return modulesTabData;
        }

        public int @Nullable [] getActiveCellLocation() {
            return activeCellLocation;
        }

        public void resetActiveCellLocation() {
            activeCellLocation = null;
        }

        public void setActiveCellLocation(int sectionIndex, int lengthIndex, int widthIndex) {
            activeCellLocation = new int[] {sectionIndex, lengthIndex, widthIndex};
        }
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public enum HabitatTabEnum implements TabSelectionEnumInterface {
        SECTIONS,
        CELLS,
        MODULES
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class SectionsTabData {
        private static final @Nullable HabitatSection DEFAULT_TEMPLATE_SECTION = null;
        private @Nullable HabitatSection
                selectedSection, //for keeping a particular section focused
                templateSection; //for adding/removing sections

        SectionsTabData() {
            selectedSection = null;
            templateSection = DEFAULT_TEMPLATE_SECTION;
        }

        public @Nullable HabitatSection getSelectedSection() {
            return selectedSection;
        }

        public void setSelectedSection(@Nullable HabitatSection selectedSection) {
            this.selectedSection = selectedSection;
        }

        //copy a new section from template
        public @Nullable HabitatSection getSectionByTemplate() {
            if (templateSection == null) {
                return null;
            } else {
                return templateSection.copy();
            }
        }

        public void setTemplateSection(@Nullable HabitatSection section) {
            templateSection = section;
        }
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class CellsTabData {
        private static final @Nullable HabitatModule DEFAULT_TEMPLATE_MODULE = new SampleModule_medium();
        private @Nullable HabitatModule templateModule; //for adding/removing modules

        CellsTabData() {
            templateModule = DEFAULT_TEMPLATE_MODULE;
        }

        //copy a new module from template
        public @Nullable HabitatModule getModuleByTemplate() {
            if (templateModule == null) {
                return null;
            } else {
                return templateModule.copy();
            }
        }

        public void setTemplateModule(@Nullable HabitatModule module) {
            templateModule = module;
        }
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class ModulesTabData {
        private @Nullable HabitatSectionCell selectedCell; //for keeping a particular cell focused

        ModulesTabData() {
            selectedCell = null;
        }

        public @Nullable HabitatSectionCell getSelectedCell() {
            return selectedCell;
        }

        public void setSelectedCell(@Nullable HabitatSectionCell cell) {
            selectedCell = cell;
        }
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class BalancingTabData {
        //add stuff here
        BalancingTabData() {}
    }

    //
    @SuppressWarnings("MissingJavadoc")
    public static final class HabitatSystemMiscellaneousTabData {
        //add stuff here
        HabitatSystemMiscellaneousTabData() {}
    }
}
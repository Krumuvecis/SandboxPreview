package rotors.habitatEditor.sidePanel.tabbedPanes.habitatPane;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.event.ChangeEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.habitatEditor.window.UserData.HabitatTabEnum;
import rotors.habitatEditor.window.UserData.HabitatTabData;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractTabbedPane;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractPaneChangeListener;
import rotors.habitatEditor.sidePanel.tabbedPanes.habitatPane.HabitatTabbedPane.HabitatPaneChangeListener;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public final class HabitatTabbedPane extends AbstractTabbedPane<
        @NotNull HabitatTabEnum, @NotNull HabitatTabData,
        @NotNull HabitatTabSubtab, @Nullable HabitatPaneChangeListener> {
    //
    public HabitatTabbedPane(@NotNull Dimension size, @NotNull Color backgroundColor,
                             @NotNull HabitatTabData tabData) {
        super(size, backgroundColor, tabData);
    }

    //
    @Override
    public void addTabs(@NotNull Dimension paneSize, @NotNull Color backgroundColor, @NotNull HabitatTabData tabData) {
        @NotNull Component
                sectionsTab = new SectionsTab(backgroundColor, tabData.getSectionsTabData(), tabData),
                cellsTab = new CellsTab(backgroundColor, tabData.getCellsTabData()),
                modulesTab = new ModulesTab(backgroundColor, tabData.getModulesTabData());
        addTab("Sections", sectionsTab);
        addTab("Cells", cellsTab);
        addTab("Modules", modulesTab);
        switch (tabData.getDefaultTab()) {
            case SECTIONS -> setSelectedComponent(sectionsTab);
            case CELLS -> setSelectedComponent(cellsTab);
            case MODULES -> setSelectedComponent(modulesTab);
            default -> throw new RuntimeException("unrecognized default tab");
        }
    }

    //
    @Override
    public @NotNull HabitatPaneChangeListener getNewPaneChangeListener(@NotNull HabitatTabData tabData) {
        return new HabitatPaneChangeListener(tabData, this);
    }

    //
    public static final class HabitatPaneChangeListener extends AbstractPaneChangeListener<@NotNull HabitatTabEnum, @NotNull HabitatTabData, @NotNull HabitatTabSubtab> {
        private final @NotNull HabitatTabData tabData;
        private final @NotNull HabitatTabbedPane tabbedPane;

        //
        HabitatPaneChangeListener(@NotNull HabitatTabData tabData, @NotNull HabitatTabbedPane tabbedPane) {
            super();
            this.tabData = tabData;
            this.tabbedPane = tabbedPane;
        }

        /**
         * Invoked when the target of the listener has changed its state.
         *
         * @param e a ChangeEvent object
         */
        @Override
        public void stateChanged(@NotNull ChangeEvent e) {
            @NotNull Component selected = tabbedPane.getSelectedComponent();
            switch (selected) {
                case SectionsTab tab -> setActiveTab(tabData, tab);
                case CellsTab tab -> setActiveTab(tabData, tab);
                case ModulesTab tab -> setActiveTab(tabData, tab);
                default -> throw new RuntimeException("unrecognized pane");
            }
        }
    }
}
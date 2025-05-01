package rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.event.ChangeEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.habitatEditor.window.UserData.HabitatSystemTabEnum;
import rotors.habitatEditor.window.UserData.HabitatSystemTabData;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractTabbedPane;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractPaneChangeListener;
import rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane.HabitatSystemTabbedPane.HabitatSystemPaneChangeListener;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public final class HabitatSystemTabbedPane extends AbstractTabbedPane<
        @NotNull HabitatSystemTabEnum, @NotNull HabitatSystemTabData,
        @NotNull HabitatSystemSubtab, @Nullable HabitatSystemPaneChangeListener> {
    //
    public HabitatSystemTabbedPane(@NotNull Dimension size, @NotNull Color backgroundColor,
                                   @NotNull HabitatSystemTabData tabData) {
        super(size, backgroundColor, tabData);
    }

    //
    @Override
    public void addTabs(@NotNull Dimension paneSize, @NotNull Color backgroundColor,
                        @NotNull HabitatSystemTabData tabData) {
        @NotNull Component
                overallTab = new HabitatSystemTab_overall(backgroundColor, tabData.getOverallTabData()),
                rotorsTab = new RotorsTab(paneSize, backgroundColor, tabData.getRotorsTabData()),
                miscellaneousTab = new HabitatSystemTab_miscellaneous(backgroundColor, Color.white, tabData.getMiscellaneousTabData());
        addTab("Overall", overallTab);
        addTab("Rotors", rotorsTab);
        addTab("Miscellaneous", miscellaneousTab);
        switch (tabData.getDefaultTab()) {
            case OVERALL -> setSelectedComponent(overallTab);
            case ROTORS -> setSelectedComponent(rotorsTab);
            case MISCELLANEOUS -> setSelectedComponent(miscellaneousTab);
            default -> throw new RuntimeException("unrecognized default tab");
        }
    }

    //
    @Override
    public @NotNull HabitatSystemPaneChangeListener getNewPaneChangeListener(@NotNull HabitatSystemTabData tabData) {
        return new HabitatSystemPaneChangeListener(tabData, this);
    }

    //
    public static final class HabitatSystemPaneChangeListener extends AbstractPaneChangeListener<
            @NotNull HabitatSystemTabEnum, @NotNull HabitatSystemTabData, @NotNull HabitatSystemSubtab> {
        private final @NotNull HabitatSystemTabData tabData;
        private final @NotNull HabitatSystemTabbedPane tabbedPane;

        //
        HabitatSystemPaneChangeListener(@NotNull HabitatSystemTabData tabData,
                                        @NotNull HabitatSystemTabbedPane tabbedPane) {
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
                case HabitatSystemTab_overall tab -> setActiveTab(tabData, tab);
                case RotorsTab tab -> setActiveTab(tabData, tab);
                case HabitatSystemTab_miscellaneous tab -> setActiveTab(tabData, tab);
                default -> throw new RuntimeException("unrecognized pane");
            }
        }
    }
}
package rotors.habitatEditor.sidePanel.tabbedPanes.rotorsPane;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.event.ChangeEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.habitatEditor.window.UserData.RotorsTabEnum;
import rotors.habitatEditor.window.UserData.RotorsTabData;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractTabbedPane;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractPaneChangeListener;
import rotors.habitatEditor.sidePanel.tabbedPanes.rotorsPane.RotorsTabbedPane.RotorsPaneChangeListener;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public final class RotorsTabbedPane extends AbstractTabbedPane<
        @NotNull RotorsTabEnum, @NotNull RotorsTabData,
        @NotNull RotorsTabSubtab, @Nullable RotorsPaneChangeListener> {
    //
    public RotorsTabbedPane(@NotNull Dimension size, @NotNull Color backgroundColor,
                            @NotNull RotorsTabData tabData) {
        super(size, backgroundColor, tabData);
    }

    //
    @Override
    public void addTabs(@NotNull Dimension paneSize, @NotNull Color backgroundColor, @NotNull RotorsTabData tabData) {
        @NotNull Component
                axisTab = new AxisTab(backgroundColor, tabData.getAxisTabData()),
                habitatTab = new HabitatTab(paneSize, backgroundColor, tabData.getHabitatTabData()),
                balancingTab = new BalancingTab(backgroundColor, tabData.getBalancingTabData());
        addTab("Axis", axisTab);
        addTab("Habitat", habitatTab);
        addTab("Balancing", balancingTab);
        switch (tabData.getDefaultTab()) {
            case AXIS -> setSelectedComponent(axisTab);
            case HABITAT -> setSelectedComponent(habitatTab);
            case BALANCING -> setSelectedComponent(balancingTab);
            default -> throw new RuntimeException("unrecognized default tab");
        }
    }

    //
    @Override
    public @NotNull RotorsPaneChangeListener getNewPaneChangeListener(@NotNull RotorsTabData tabData) {
        return new RotorsPaneChangeListener(tabData, this);
    }

    //
    public static final class RotorsPaneChangeListener extends AbstractPaneChangeListener<
            @NotNull RotorsTabEnum, @NotNull RotorsTabData, @NotNull RotorsTabSubtab> {
        private final @NotNull RotorsTabData tabData;
        private final @NotNull RotorsTabbedPane tabbedPane;

        //
        RotorsPaneChangeListener(@NotNull RotorsTabData tabData, @NotNull RotorsTabbedPane tabbedPane) {
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
                case AxisTab tab -> setActiveTab(tabData, tab);
                case HabitatTab tab -> setActiveTab(tabData, tab);
                case BalancingTab tab -> setActiveTab(tabData, tab);
                default -> throw new RuntimeException("unrecognized pane");
            }
        }
    }
}
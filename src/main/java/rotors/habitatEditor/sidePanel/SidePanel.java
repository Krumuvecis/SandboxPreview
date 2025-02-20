package rotors.habitatEditor.sidePanel;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane.HabitatSystemTabbedPane;
import rotors.habitatEditor.window.UserData.HabitatSystemTabData;

//
public final class SidePanel extends JPanel {
    private static final int
            PANEL_WIDTH = 250,
            TABBED_PANE_HEIGHT = 500;
    private static final @NotNull Color BACKGROUND_COLOR = Color.darkGray;

    //
    public SidePanel(@NotNull HabitatSystemTabData tabData) {
        super();
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(new Dimension(PANEL_WIDTH, Integer.MAX_VALUE));
        add(new HabitatSystemTabbedPane(new Dimension(PANEL_WIDTH, TABBED_PANE_HEIGHT), BACKGROUND_COLOR, tabData));
    }
}
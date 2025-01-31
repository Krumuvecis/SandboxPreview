package rotors.habitatEditor.sidePanel;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.sidePanel.tabbedPane.TabbedPane;
import rotors.habitatEditor.window.UserData;

//
public final class SidePanel extends JPanel {
    private static final @NotNull Dimension
            PANEL_SIZE = new Dimension(250, Integer.MAX_VALUE),
            TABBED_PANE_SIZE = new Dimension(PANEL_SIZE.width, 500);
    private static final @NotNull Color BACKGROUND_COLOR = Color.darkGray;

    //
    public SidePanel(@NotNull UserData.EditModeData editModeData) {
        super();
        setBackground(BACKGROUND_COLOR);
        setPreferredSize(PANEL_SIZE);
        add(new TabbedPane(TABBED_PANE_SIZE, BACKGROUND_COLOR, editModeData));
    }
}
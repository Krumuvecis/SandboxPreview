package rotors.habitatEditor.sidePanel.tabbedPane;

import java.awt.Color;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.EditMode;

//
public abstract class AbstractTab extends JPanel {
    //
    AbstractTab(@NotNull Color backgroundColor) {
        super();
        setBackground(backgroundColor);
    }

    //
    public abstract @NotNull EditMode getTabEditMode();
}
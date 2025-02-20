package rotors.habitatEditor.sidePanel.tabbedPanes;

import java.awt.Color;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import static rotors.habitatEditor.window.UserData.TabSelectionEnumInterface;

//
public abstract class AbstractTab<T extends @NotNull TabSelectionEnumInterface> extends JPanel {
    //
    public AbstractTab(@NotNull Color backgroundColor) {
        super();
        setBackground(backgroundColor);
    }

    //
    public abstract @NotNull T getTabEnum();
}
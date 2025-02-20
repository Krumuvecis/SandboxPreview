package rotors.habitatEditor.sidePanel.tabbedPanes.rotorsPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.RotorsTabEnum;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractTab;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
abstract class RotorsTabSubtab extends AbstractTab<@NotNull RotorsTabEnum> {
    //
    RotorsTabSubtab(@NotNull Color backgroundColor) {
        super(backgroundColor);
    }
}
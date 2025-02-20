package rotors.habitatEditor.sidePanel.tabbedPanes.habitatPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.HabitatTabEnum;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractTab;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
abstract class HabitatTabSubtab extends AbstractTab<@NotNull HabitatTabEnum> {
    //
    HabitatTabSubtab(@NotNull Color backgroundColor) {
        super(backgroundColor);
    }
}
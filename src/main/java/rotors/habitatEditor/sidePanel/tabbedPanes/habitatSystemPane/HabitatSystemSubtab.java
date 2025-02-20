package rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.HabitatSystemTabEnum;
import rotors.habitatEditor.sidePanel.tabbedPanes.AbstractTab;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
abstract class HabitatSystemSubtab extends AbstractTab<@NotNull HabitatSystemTabEnum> {
    //
    HabitatSystemSubtab(@NotNull Color backgroundColor) {
        super(backgroundColor);
    }
}
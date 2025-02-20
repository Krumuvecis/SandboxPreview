package rotors.habitatEditor.sidePanel.tabbedPanes.rotorsPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.RotorsTabEnum;
import rotors.habitatEditor.window.UserData.AxisTabData;

//TODO:
// * add functionality
// * display relavant stuff on main panel
// * add docs
@SuppressWarnings("MissingJavadoc")
final class AxisTab extends RotorsTabSubtab {
    //
    AxisTab(@NotNull Color backgroundColor, @NotNull AxisTabData axisTabData) {
        super(backgroundColor);
    }

    //
    @Override
    public @NotNull RotorsTabEnum getTabEnum() {
        return RotorsTabEnum.AXIS;
    }
}
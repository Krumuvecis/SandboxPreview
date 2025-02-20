package rotors.habitatEditor.sidePanel.tabbedPanes.rotorsPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.RotorsTabEnum;
import rotors.habitatEditor.window.UserData.BalancingTabData;

//TODO:
// * add functionality
// * display relavant stuff on main panel
// * add docs
@SuppressWarnings("MissingJavadoc")
final class BalancingTab extends RotorsTabSubtab {
    //
    BalancingTab(@NotNull Color backgroundColor, @NotNull BalancingTabData tabData) {
        super(backgroundColor);
    }

    //
    @Override
    public @NotNull RotorsTabEnum getTabEnum() {
        return RotorsTabEnum.BALANCING;
    }
}
package rotors.habitatEditor.sidePanel.tabbedPanes.habitatPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.HabitatTabEnum;
import rotors.habitatEditor.window.UserData.ModulesTabData;

//TODO:
// * add functionality
// * display relavant stuff on main panel
// * add docs
@SuppressWarnings("MissingJavadoc")
final class ModulesTab extends HabitatTabSubtab {
    //
    ModulesTab(@NotNull Color backgroundColor, @NotNull ModulesTabData tabData) {
        super(backgroundColor);
    }

    //
    @Override
    public @NotNull HabitatTabEnum getTabEnum() {
        return HabitatTabEnum.MODULES;
    }
}
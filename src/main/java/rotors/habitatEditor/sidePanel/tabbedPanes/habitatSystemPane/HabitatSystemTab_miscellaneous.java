package rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.HabitatSystemTabEnum;
import rotors.habitatEditor.window.UserData.HabitatSystemMiscellaneousTabData;

//TODO:
// * add functionality
// * display relavant stuff on main panel
// * add docs
@SuppressWarnings("MissingJavadoc")
final class HabitatSystemTab_miscellaneous extends HabitatSystemSubtab {
    //
    HabitatSystemTab_miscellaneous(@NotNull Color backgroundColor, @NotNull HabitatSystemMiscellaneousTabData tabData) {
        super(backgroundColor);
    }

    //
    @Override
    public @NotNull HabitatSystemTabEnum getTabEnum() {
        return HabitatSystemTabEnum.MISCELLANEOUS;
    }
}
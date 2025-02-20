package rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.HabitatSystemTabEnum;
import rotors.habitatEditor.window.UserData.HabitatSystemOverallTabData;

//TODO:
// * add functionality
// * display relavant stuff on main panel
// * add docs
@SuppressWarnings("MissingJavadoc")
final class HabitatSystemTab_overall extends HabitatSystemSubtab {
    //
    HabitatSystemTab_overall(@NotNull Color backgroundColor, @NotNull HabitatSystemOverallTabData tabData) {
        super(backgroundColor);
    }

    //
    @Override
    public @NotNull HabitatSystemTabEnum getTabEnum() {
        return HabitatSystemTabEnum.OVERALL;
    }
}
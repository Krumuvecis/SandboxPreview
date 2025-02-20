package rotors.habitatEditor.sidePanel.tabbedPanes.rotorsPane;

import java.awt.Dimension;
import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.RotorsTabEnum;
import rotors.habitatEditor.window.UserData.HabitatTabData;
import rotors.habitatEditor.sidePanel.tabbedPanes.habitatPane.HabitatTabbedPane;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
final class HabitatTab extends RotorsTabSubtab {
    //
    HabitatTab(@NotNull Dimension size, @NotNull Color backgroundColor, @NotNull HabitatTabData tabData) {
        super(backgroundColor);
        add(new HabitatTabbedPane(size, backgroundColor, tabData));
    }

    //
    @Override
    public @NotNull RotorsTabEnum getTabEnum() {
        return RotorsTabEnum.HABITAT;
    }
}
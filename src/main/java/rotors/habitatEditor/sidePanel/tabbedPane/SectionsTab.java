package rotors.habitatEditor.sidePanel.tabbedPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.EditMode;

//
final class SectionsTab extends AbstractTab {
    //
    SectionsTab(@NotNull Color backgroundColor) {
        super(backgroundColor);
    }

    //
    @Override
    public @NotNull EditMode getTabEditMode() {
        return EditMode.EDIT_SECTIONS;
    }
}
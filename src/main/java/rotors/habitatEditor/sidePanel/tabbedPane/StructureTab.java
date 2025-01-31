package rotors.habitatEditor.sidePanel.tabbedPane;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.EditMode;

//
final class StructureTab extends AbstractTab {
    //
    StructureTab(@NotNull Color backgroundColor) {
        super(backgroundColor);
    }

    //
    @Override
    public @NotNull EditMode getTabEditMode() {
        return EditMode.EDIT_STRUCTURE;
    }
}
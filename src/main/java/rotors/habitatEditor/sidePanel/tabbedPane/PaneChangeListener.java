package rotors.habitatEditor.sidePanel.tabbedPane;

import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData;

//
final class PaneChangeListener implements ChangeListener {
    private final @NotNull UserData.EditModeData editModeData;
    private final @NotNull JTabbedPane tabbedPane;

    //
    PaneChangeListener(@NotNull UserData.EditModeData editModeData, @NotNull JTabbedPane tabbedPane) {
        this.editModeData = editModeData;
        this.tabbedPane = tabbedPane;
    }

    /**
     * Invoked when the target of the listener has changed its state.
     *
     * @param e a ChangeEvent object
     */
    @Override
    public void stateChanged(@NotNull ChangeEvent e) {
        @NotNull Component selected = tabbedPane.getSelectedComponent();
        switch (selected) {
            case SectionsTab tab -> setEditMode(editModeData, tab);
            case StructureTab tab -> setEditMode(editModeData, tab);
            case ModulesTab tab -> setEditMode(editModeData, tab);
            default -> throw new RuntimeException("unrecognized pane");
        }
    }

    private void setEditMode(@NotNull UserData.EditModeData editModeData, @NotNull AbstractTab tab) {
        editModeData.setEditMode(tab.getTabEditMode());
    }
}
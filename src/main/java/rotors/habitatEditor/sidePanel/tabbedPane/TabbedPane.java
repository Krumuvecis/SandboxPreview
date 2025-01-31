package rotors.habitatEditor.sidePanel.tabbedPane;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTabbedPane;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData;

//
public final class TabbedPane extends JTabbedPane {
    //
    public TabbedPane(@NotNull Dimension size, @NotNull Color backgroundColor,
                      @NotNull UserData.EditModeData editModeData) {
        super();
        setPreferredSize(size);
        addTabs(backgroundColor, editModeData);
        addChangeListener(new PaneChangeListener(editModeData, this));
        setFocusable(false);
    }

    private void addTabs(@NotNull Color backgroundColor, @NotNull UserData.EditModeData editModeData) {
        @NotNull Component
                sectionsTab = new SectionsTab(backgroundColor),
                cellsTab = new StructureTab(backgroundColor),
                modulesTab = new ModulesTab(backgroundColor, editModeData);
        addTab("Sections", sectionsTab);
        addTab("Structure", cellsTab);
        addTab("Modules", modulesTab);
        switch (editModeData.getEditMode()) {
            case EDIT_SECTIONS -> setSelectedComponent(sectionsTab);
            case EDIT_STRUCTURE -> setSelectedComponent(cellsTab);
            case EDIT_MODULES -> setSelectedComponent(modulesTab);
            default -> throw new RuntimeException("unrecognized edit mode");
        }
    }
}
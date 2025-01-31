package rotors.habitatEditor.sidePanel.tabbedPane;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import rotors.modularHabitat.HabitatModule;
import rotors.modularHabitat.particularModules.SampleModule_green;
import rotors.modularHabitat.particularModules.SampleModule_magenta;
import rotors.habitatEditor.window.UserData;

//
final class ModulesTab extends AbstractTab {
    //
    ModulesTab(@NotNull Color backgroundColor, @NotNull UserData.EditModeData editModeData) {
        super(backgroundColor);
        addRadioButtons(backgroundColor, editModeData);
    }

    //
    @Override
    public @NotNull UserData.EditMode getTabEditMode() {
        return UserData.EditMode.EDIT_MODULES;
    }

    private void addRadioButtons(@NotNull Color backgroundColor, @NotNull UserData.EditModeData editModeData) {
        @NotNull JRadioButton
                radioButton0 = new ModulesTabRadioButton("Delete module", backgroundColor, editModeData,
                null),
                radioButton1 = new ModulesTabRadioButton("Add green module", backgroundColor, editModeData,
                        new SampleModule_green(), true),
                radioButton2 = new ModulesTabRadioButton("Add magenta module", backgroundColor, editModeData,
                        new SampleModule_magenta());

        @NotNull ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton0);
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(radioButton0);
        add(radioButton1);
        add(radioButton2);
    }

    private static class ModulesTabRadioButton extends JRadioButton {
        ModulesTabRadioButton(@NotNull String label, @NotNull Color backgroundColor,
                              @NotNull UserData.EditModeData editModeData, @Nullable HabitatModule templateModule,
                              boolean selected) {
            super(label, selected);
            setBackground(backgroundColor);
            setForeground(Color.white);
            addActionListener(e -> {
                editModeData.setTemplateModule(templateModule);
                printLine(label + " action");
            });
            setFocusable(false);
        }

        ModulesTabRadioButton(@NotNull String label, @NotNull Color backgroundColor,
                              @NotNull UserData.EditModeData editModeData, @Nullable HabitatModule templateModule) {
            this(label, backgroundColor, editModeData, templateModule, false);
        }
    }
}

package rotors.habitatEditor.sidePanel.tabbedPanes.habitatPane;

import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import rotors.rotatingHabitat.modularHabitat.HabitatModule;
import rotors.rotatingHabitat.modularHabitat.particularModules.SampleModule_light;
import rotors.rotatingHabitat.modularHabitat.particularModules.SampleModule_medium;
import rotors.rotatingHabitat.modularHabitat.particularModules.SampleModule_heavy;
import rotors.rotatingHabitat.modularHabitat.particularModules.SampleModule_blue;
import rotors.habitatEditor.window.UserData.HabitatTabEnum;
import rotors.habitatEditor.window.UserData.CellsTabData;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
final class CellsTab extends HabitatTabSubtab {
    //
    CellsTab(@NotNull Color backgroundColor, @NotNull CellsTabData tabData) {
        super(backgroundColor);
        addRadioButtons(backgroundColor, tabData);
    }

    //
    @Override
    public @NotNull HabitatTabEnum getTabEnum() {
        return HabitatTabEnum.CELLS;
    }

    private void addRadioButtons(@NotNull Color backgroundColor, @NotNull CellsTabData tabData) {
        @NotNull JRadioButton
                radioButton0 = new CellsTabRadioButton("Delete module", backgroundColor, tabData, null),
                radioButton1 = new CellsTabRadioButton("Add light module", backgroundColor, tabData,
                        new SampleModule_light()),
                radioButton2 = new CellsTabRadioButton("Add medium module", backgroundColor, tabData,
                        new SampleModule_medium(), true),
                radioButton3 = new CellsTabRadioButton("Add heavy module", backgroundColor, tabData,
                        new SampleModule_heavy()),
                radioButton4 = new CellsTabRadioButton("Add blue module", backgroundColor, tabData,
                        new SampleModule_blue());

        @NotNull ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton0);
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        buttonGroup.add(radioButton3);
        buttonGroup.add(radioButton4);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(radioButton0);
        add(radioButton1);
        add(radioButton2);
        add(radioButton3);
        add(radioButton4);
    }

    private static class CellsTabRadioButton extends JRadioButton {
        CellsTabRadioButton(@NotNull String label, @NotNull Color backgroundColor,
                            @NotNull CellsTabData tabData, @Nullable HabitatModule templateModule,
                            boolean selected) {
            super(label, selected);
            setBackground(backgroundColor);
            setForeground(Color.white);
            addActionListener(e -> {
                tabData.setTemplateModule(templateModule);
                printLine(label + " action");
            });
            setFocusable(false);
        }

        CellsTabRadioButton(@NotNull String label, @NotNull Color backgroundColor,
                            @NotNull CellsTabData tabData, @Nullable HabitatModule templateModule) {
            this(label, backgroundColor, tabData, templateModule, false);
        }
    }
}
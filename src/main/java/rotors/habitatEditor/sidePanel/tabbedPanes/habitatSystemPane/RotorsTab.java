package rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;

import utils.DropdownData;
import utils.DropdownableInterface;
import rotors.habitatEditor.window.UserData.HabitatSystemTabEnum;
import rotors.habitatEditor.window.UserData.RotorsTabData;
import rotors.habitatEditor.sidePanel.LabeledDropdown;
import rotors.habitatEditor.sidePanel.LabeledDropdown.AbstractComboBox;
import rotors.habitatEditor.sidePanel.tabbedPanes.rotorsPane.RotorsTabbedPane;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
final class RotorsTab extends HabitatSystemSubtab {
    //
    RotorsTab(@NotNull Dimension tabSize, @NotNull Color backgroundColor, @NotNull RotorsTabData tabData) {
        super(backgroundColor);
        add(new RotorsDropdown(backgroundColor, Color.white, "Select rotor: "));
        add(new RotorsTabbedPane(tabSize, backgroundColor, tabData));
    }

    //
    @Override
    public @NotNull HabitatSystemTabEnum getTabEnum() {
        return HabitatSystemTabEnum.ROTORS;
    }

    //
    private static class RotorsDropdown extends LabeledDropdown<
            @NotNull RotorEnum, @NotNull RotorData,
            @NotNull AbstractComboBox<@NotNull RotorEnum, @NotNull RotorData>> {
        private static final @NotNull RotorData ROTOR_DATA = new RotorData();

        RotorsDropdown(@NotNull Color backgroundColor, @NotNull Color textColor, @NotNull String text) {
            super(backgroundColor, textColor, text, ROTOR_DATA, ROTOR_DATA.getDefaultValue());
        }

        @Override
        public @NotNull JLabel getNewLabel(@NotNull String text, @NotNull Color textColor) {
            return new JLabel(text) {{
                setForeground(textColor);
            }};
        }

        @Override
        public @NotNull RotorComboBox getNewComboBox(@NotNull RotorData data, @NotNull RotorEnum defaultValue) {
            return new RotorComboBox(data, defaultValue);
        }
    }

    private enum RotorEnum implements DropdownableInterface<@NotNull RotorEnum> {
        ROTOR_1("Rotor 1"),
        ROTOR_2("Rotor 2");

        private final @NotNull String displayText;

        RotorEnum(@NotNull String displayText) {
            this.displayText = displayText;
        }

        //for graphical purposes
        @Override
        public final @NotNull String toString() {
            return displayText;
        }

        //
        @Override
        public final @NotNull RotorEnum @NotNull [] getAll() {
            return RotorEnum.values();
        }
    }

    private static final class RotorData extends DropdownData<@NotNull RotorEnum> {
        private static final @NotNull RotorEnum DEFAULT_ROTOR = RotorEnum.ROTOR_1;

        //
        RotorData() {
            super(DEFAULT_ROTOR);
        }
    }

    private static final class RotorComboBox extends AbstractComboBox<@NotNull RotorEnum, @NotNull RotorData>{
        //
        public RotorComboBox(@NotNull RotorData data, @NotNull RotorEnum defaultValue) {
            super(data, defaultValue);

            addActionListener(new ActionListener() {
                @SuppressWarnings("rawtypes")
                @Override
                public void actionPerformed(@NotNull ActionEvent e) {
                    @NotNull JComboBox comboBox = (JComboBox) e.getSource();
                    if (comboBox != null) {
                        printLine(comboBox.getSelectedItem() + " selected");
                    }
                }
            });
        }
    }
}
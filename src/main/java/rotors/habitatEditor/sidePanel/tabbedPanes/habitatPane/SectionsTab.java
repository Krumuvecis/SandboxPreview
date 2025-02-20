package rotors.habitatEditor.sidePanel.tabbedPanes.habitatPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.jetbrains.annotations.NotNull;

import utils.DropdownData;
import rotors.habitatEditor.window.UserData.InfoDisplayAmount;
import rotors.habitatEditor.window.UserData.HabitatTabEnum;
import rotors.habitatEditor.window.UserData.HabitatTabData;
import rotors.habitatEditor.window.UserData.SectionsTabData;
import rotors.habitatEditor.sidePanel.LabeledDropdown;
import rotors.habitatEditor.sidePanel.LabeledDropdown.AbstractComboBox;
import utils.DropdownableInterface;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
final class SectionsTab extends HabitatTabSubtab {
    //
    SectionsTab(@NotNull Color backgroundColor, @NotNull SectionsTabData tabData,
                @NotNull HabitatTabData habitatTabData) {
        super(backgroundColor);
        add(new RotorSelector(backgroundColor, Color.white, "Section info: ", habitatTabData));
    }

    //
    @Override
    public @NotNull HabitatTabEnum getTabEnum() {
        return HabitatTabEnum.SECTIONS;
    }

    private static final class RotorSelector extends LabeledDropdown<
            @NotNull InfoDisplayAmount, @NotNull DropdownData<@NotNull InfoDisplayAmount>, @NotNull ComboBox> {
        //
        RotorSelector(@NotNull Color backgroundColor, @NotNull Color textColor, @NotNull String text,
                      @NotNull HabitatTabData habitatTabData) {
            super(backgroundColor, textColor, text,
                    habitatTabData.getSectionInfoDisplayAmount(),
                    habitatTabData.getSectionInfoDisplayAmount().getDefaultValue());
        }

        //TODO: finish this
        @Override
        public @NotNull JLabel getNewLabel(@NotNull String text, @NotNull Color textColor) {
            return new JLabel("Section info: ") {{
                setForeground(Color.white);
            }};
        }

        @Override
        public @NotNull ComboBox getNewComboBox(@NotNull DropdownData<@NotNull InfoDisplayAmount> data,
                                                @NotNull InfoDisplayAmount defaultValue) {
            return new ComboBox(data);
        }
    }

    //
    private static final class ComboBox
            extends AbstractComboBox<@NotNull InfoDisplayAmount, @NotNull DropdownData<@NotNull InfoDisplayAmount>> {
        //
        ComboBox(@NotNull DropdownData<@NotNull InfoDisplayAmount> sectionInfoDisplayAmount) {
            super(sectionInfoDisplayAmount, sectionInfoDisplayAmount.getDefaultValue());
            setSelectedItem(sectionInfoDisplayAmount.getDefaultValue());
            addActionListener(new ComboBoxActionListener(sectionInfoDisplayAmount));
            setFocusable(false);
        }
    }

    private static final class ComboBoxActionListener implements ActionListener {
        private final @NotNull DropdownData<@NotNull InfoDisplayAmount> sectionInfoDisplayAmount;

        //
        ComboBoxActionListener(@NotNull DropdownData<@NotNull InfoDisplayAmount> sectionInfoDisplayAmount) {
            this.sectionInfoDisplayAmount = sectionInfoDisplayAmount;
        }

        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(@NotNull ActionEvent e) {
            @NotNull ComboBox comboBox = (ComboBox) e.getSource();
            sectionInfoDisplayAmount.setValue((InfoDisplayAmount) comboBox.getSelectedItem());
        }
    }
}
package rotors.habitatEditor.sidePanel;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import org.jetbrains.annotations.NotNull;

import utils.DropdownableInterface;
import utils.DropdownData;
import rotors.habitatEditor.sidePanel.LabeledDropdown.AbstractComboBox;

//TODO: finish this
@SuppressWarnings("MissingJavadoc")
public abstract class LabeledDropdown<
            T extends @NotNull DropdownableInterface, U extends @NotNull DropdownData<T>,
            V extends @NotNull AbstractComboBox<T, U>>
        extends JPanel {
    //
    public LabeledDropdown(@NotNull Color backgroundColor, @NotNull Color textColor, @NotNull String text,
                           U data, T defaultValue) {
        super();
        setBackground(backgroundColor);
        add(getNewLabel(text, textColor));
        add(getNewComboBox(data, defaultValue));
    }

    //
    public abstract @NotNull JLabel getNewLabel(@NotNull String text, @NotNull Color textColor);

    //
    public abstract V getNewComboBox(U data, T defaultValue);

    //
    public static abstract class AbstractComboBox<
                T extends @NotNull DropdownableInterface, U extends @NotNull DropdownData<T>>
            extends JComboBox<T> {
        //
        public AbstractComboBox(U data, T defaultValue) {
            super(defaultValue.getAll());
            setSelectedItem(defaultValue);
            //addActionListener(new SectionsTab.ComboBoxActionListener(habitatTabData));
            setFocusable(false);
        }
    }
}
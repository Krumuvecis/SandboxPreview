package rotors.habitatEditor.sidePanel.tabbedPanes.habitatSystemPane;

import java.awt.Color;
import javax.swing.JTextArea;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.HabitatSystemTabEnum;
import rotors.habitatEditor.window.UserData.HabitatSystemMiscellaneousTabData;

//TODO:
// * add functionality
// * display relavant stuff on main panel
// * add docs
@SuppressWarnings("MissingJavadoc")
final class HabitatSystemTab_miscellaneous extends HabitatSystemSubtab {
    //
    HabitatSystemTab_miscellaneous(@NotNull Color backgroundColor, @NotNull Color textColor,
                                   @NotNull HabitatSystemMiscellaneousTabData tabData) {
        super(backgroundColor);
        add(new JTextArea("Some common non-rotating parts will be here.") {{
            setLineWrap(true);
            setWrapStyleWord(true);
            setBounds(0, 0, 230, 100);
            setBackground(backgroundColor);
            setForeground(textColor);
        }});
        add(new JTextArea("Some common non-rotating parts will be here.") {{
            setLineWrap(true);
            setWrapStyleWord(true);
            setBounds(0, 0, 230, 100);
            setBackground(backgroundColor);
            setForeground(textColor);
        }});
    }

    //
    @Override
    public @NotNull HabitatSystemTabEnum getTabEnum() {
        return HabitatSystemTabEnum.MISCELLANEOUS;
    }
}
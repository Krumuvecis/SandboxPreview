package rotors.habitatEditor.sidePanel.tabbedPanes;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JTabbedPane;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.habitatEditor.window.UserData.TabSelectionEnumInterface;
import rotors.habitatEditor.window.UserData.AbstractTabContainerData;

//TODO: add docs
@SuppressWarnings("MissingJavadoc")
public abstract class AbstractTabbedPane<
            T extends @NotNull TabSelectionEnumInterface, U extends @NotNull AbstractTabContainerData<T>,
            V extends @NotNull AbstractTab<T>, W extends @Nullable AbstractPaneChangeListener<T, U, V>>
        extends JTabbedPane {
    //
    public AbstractTabbedPane(@NotNull Dimension size, @NotNull Color backgroundColor, U tabData) {
        super();
        setPreferredSize(size);
        addTabs(size, backgroundColor, tabData);
        W paneChangeListener = getNewPaneChangeListener(tabData);
        if (paneChangeListener != null) addChangeListener(paneChangeListener);
        setFocusable(false);
    }

    //
    public abstract void addTabs(@NotNull Dimension paneSize, @NotNull Color backgroundColor, U tabData);

    //null - no listener
    public abstract W getNewPaneChangeListener(U tabData);
}
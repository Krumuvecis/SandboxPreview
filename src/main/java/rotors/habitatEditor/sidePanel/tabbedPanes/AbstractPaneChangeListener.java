package rotors.habitatEditor.sidePanel.tabbedPanes;

import javax.swing.event.ChangeListener;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.UserData.TabSelectionEnumInterface;
import rotors.habitatEditor.window.UserData.AbstractTabContainerData;

//
public abstract class AbstractPaneChangeListener<
        T extends @NotNull TabSelectionEnumInterface,
        U extends @NotNull AbstractTabContainerData<T>,
        V extends @NotNull AbstractTab<T>> implements ChangeListener {
    //
    public AbstractPaneChangeListener() {}

    public final void setActiveTab(U tabData, V tab) {
        tabData.setActiveTab(tab.getTabEnum());
    }
}
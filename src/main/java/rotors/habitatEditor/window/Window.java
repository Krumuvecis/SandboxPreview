package rotors.habitatEditor.window;

import java.awt.BorderLayout;
import javax.swing.WindowConstants;
import javax.swing.JFrame;

import org.jetbrains.annotations.NotNull;

import rotors.modularHabitat.HabitatSectionCell;
import rotors.modularHabitat.ModularHabitat;
import rotors.habitatEditor.sidePanel.SidePanel;
import rotors.habitatEditor.mainPanel.MainPanel;

//
public final class Window extends JFrame {
    private static final long KEYBOARD_CHECKER_DELAY = 30;
    private final @NotNull UserData userData;
    private final @NotNull ObserverData observerData;

    //
    public Window(@NotNull ModularHabitat habitat, long fps) {
        super();
        userData = new UserData();
        double cellSizeSI = HabitatSectionCell.CELL_SIZE.getSI();
        observerData = new ObserverData(habitat.getLength() * cellSizeSI);

        configureWindow();

        @NotNull KeyboardChecker keyboardChecker = new KeyboardChecker(KEYBOARD_CHECKER_DELAY, observerData);
        addPanels(habitat, keyboardChecker);

        addKeyListener(keyboardChecker);
        keyboardChecker.start();

        setVisible(true);
        new WindowUpdater(this, fps).start();
    }

    private void configureWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Habitat editor");
        setLocation(100, 100);
        setSize(1200, 700);
    }

    private void addPanels(@NotNull ModularHabitat habitat, @NotNull KeyboardChecker keyboardChecker) {
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new SidePanel(userData.getTabData()), BorderLayout.WEST);
        getContentPane().add(new MainPanel(habitat, userData, observerData, keyboardChecker));
    }
}
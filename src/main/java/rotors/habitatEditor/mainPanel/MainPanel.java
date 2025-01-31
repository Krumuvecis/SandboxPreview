package rotors.habitatEditor.mainPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.KeyboardChecker;
import rotors.modularHabitat.ModularHabitat;
import rotors.habitatEditor.window.UserData;
import rotors.habitatEditor.window.ObserverData;

//
public class MainPanel extends JPanel implements DebugInfoPainter, HabitatPainter {
    private final @NotNull ModularHabitat habitat;
    private final @NotNull UserData userData;
    private final @NotNull ObserverData observerData;
    private final @NotNull KeyboardChecker keyboardChecker;
    private final @NotNull MainPanelMouseListener mouseListener;

    //
    public MainPanel(@NotNull ModularHabitat habitat, @NotNull UserData userData, @NotNull ObserverData observerData, @NotNull KeyboardChecker keyboardChecker) {
        super(null);
        setBackground(Color.BLACK);
        this.habitat = habitat;
        this.userData = userData;
        this.observerData = observerData;
        this.keyboardChecker = keyboardChecker;
        mouseListener = new MainPanelMouseListener(habitat, userData, observerData, this);
        addMouseMotionListener(mouseListener);
        addMouseListener(mouseListener);
        addTestPanel();
    }

    //deletable; testing null layout and absolute coordinates
    private void addTestPanel() {
        JPanel testPanel = new JPanel();
        testPanel.setBackground(Color.cyan);
        testPanel.setSize(new Dimension(50, 50));
        testPanel.setLocation(300, 50);
        add(testPanel);
    }

    //
    @Override
    public void paint(@NotNull Graphics g) {
        super.paint(g);
        double @NotNull [] panelCenter = getCenter();
        double scale = observerData.getScale();
        double @NotNull [] viewLocationActual = observerData.getViewLocationActual();
        paintHabitat(g, userData, panelCenter, scale, viewLocationActual, habitat);
        paintCenterCross(g, panelCenter);
        paintDebugInfo(g, userData, observerData,
                this, mouseListener.getMouseLocation(),
                scale, mouseListener.getMouseLocation_absoluteUnscaled(),
                keyboardChecker.getPressedKeys());
    }

    //
    double @NotNull [] getCenter() {
        return new double[] {getWidth() / 2.0, getHeight() / 2.0};
    }

    private void paintCenterCross(@NotNull Graphics g, double @NotNull [] panelCenter) {
        int size = 20;
        g.setColor(Color.white);
        g.drawLine( //horizontal line
                (int) (panelCenter[0] - size / 2), (int) panelCenter[1],
                (int) (panelCenter[0] + size / 2), (int) panelCenter[1]);
        g.drawLine( //vertical line
                (int) panelCenter[0], (int) (panelCenter[1] - size / 2),
                (int) panelCenter[0], (int) (panelCenter[1] + size / 2));
    }
}
package rotors.habitatEditor.mainPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import org.jetbrains.annotations.NotNull;

import rotors.rotatingHabitat.modularHabitat.ModularHabitat;
import rotors.habitatEditor.window.UserData;
import rotors.habitatEditor.window.ObserverData;
import rotors.habitatEditor.window.KeyboardChecker;

//
public class MainPanel extends JPanel implements DebugInfoPainter, HabitatPainter {
    private static final int CENTER_CROSS_SIZE = 20;
    private static final @NotNull Color CENTER_CROSS_COLOR = Color.white;
    private final @NotNull ModularHabitat habitat;
    private final @NotNull UserData userData;
    private final @NotNull ObserverData observerData;
    private final @NotNull KeyboardChecker keyboardChecker;
    private final @NotNull MainPanelMouseListener mouseListener;

    //
    public MainPanel(@NotNull ModularHabitat habitat, @NotNull UserData userData, @NotNull ObserverData observerData,
                     @NotNull KeyboardChecker keyboardChecker) {
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
    @SuppressWarnings("MagicNumber")
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
        @NotNull UserData.HabitatSystemTabData habitatSystemTabData = userData.getTabData();
        switch (habitatSystemTabData.getActiveTab()) {
            case OVERALL -> paintHabitatSystemTab_overall(g, panelCenter, habitatSystemTabData.getOverallTabData());
            case ROTORS -> paintHabitatSystemTab_rotors(g, panelCenter, habitatSystemTabData.getRotorsTabData());
            case MISCELLANEOUS ->
                    paintHabitatSystemTab_miscellaneous(g, panelCenter, habitatSystemTabData.getMiscellaneousTabData());
            default -> {
                //unrecognized habitat system sub-tab
                //TODO: add some visual message
            }
        }
        paintCenterCross(g, panelCenter);
    }

    //
    double @NotNull [] getCenter() {
        return new double[] {(double) getWidth() / 2, (double) getHeight() / 2};
    }

    private void paintCenterCross(@NotNull Graphics g, double @NotNull [] panelCenter) {
        int size = CENTER_CROSS_SIZE;
        g.setColor(CENTER_CROSS_COLOR);
        g.drawLine( //horizontal line
                (int) (panelCenter[0] - size / 2), (int) panelCenter[1],
                (int) (panelCenter[0] + size / 2), (int) panelCenter[1]);
        g.drawLine( //vertical line
                (int) panelCenter[0], (int) (panelCenter[1] - size / 2),
                (int) panelCenter[0], (int) (panelCenter[1] + size / 2));
    }

    private void paintHabitatSystemTab_overall(@NotNull Graphics g, double @NotNull [] panelCenter,
                                               @NotNull UserData.HabitatSystemOverallTabData overallTabData) {
        //TODO: paint some stuff here
    }

    private void paintHabitatSystemTab_rotors(@NotNull Graphics g, double @NotNull [] panelCenter,
                                              @NotNull UserData.RotorsTabData rotorsTabData) {

        double scale = observerData.getScale();
        switch (rotorsTabData.getActiveTab()) {
            case AXIS -> {
                @NotNull UserData.AxisTabData axisTabData = rotorsTabData.getAxisTabData();
                //TODO: paint some stuff here
            }
            case HABITAT -> {
                double @NotNull [] viewLocationActual = observerData.getViewLocationActual();
                paintHabitat(g, rotorsTabData.getHabitatTabData(), panelCenter, scale, viewLocationActual, habitat);
            }
            case BALANCING -> {
                @NotNull UserData.BalancingTabData balancingTabData = rotorsTabData.getBalancingTabData();
                //TODO: paint some stuff here
            }
            default -> {
                //unrecognized rotors sub-tab
                //TODO: add some visual message
            }
        }
        paintDebugInfo(g, userData, observerData,
                this, mouseListener.getMouseLocation(),
                scale, mouseListener.getMouseLocation_absoluteUnscaled(),
                keyboardChecker.getPressedKeys());
    }

    @SuppressWarnings("LongLine")
    private void paintHabitatSystemTab_miscellaneous(@NotNull Graphics g, double @NotNull [] panelCenter,
                                                     @NotNull UserData.HabitatSystemMiscellaneousTabData miscellaneousTabData) {
        //TODO: paint some stuff here
    }
}
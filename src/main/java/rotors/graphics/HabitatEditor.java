package rotors.graphics;

import delayCalculator.delayOptions.DelayType;
import delayCalculator.delayOptions.DelayOptions;
import ThreadAbstraction.AbstractUpdater;
import org.jetbrains.annotations.NotNull;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import static consoleUtils.SimplePrinting.printLine;

public class HabitatEditor {
    @SuppressWarnings("InstantiationOfUtilityClass")
    public static void main(String[] args) {
        new HabitatEditor();
    }

    HabitatEditor() {
        new WindowUpdater(new Window()).start();
    }

    //
    static class Window extends JFrame {
        //
        Window() {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Habitat editor");
            setLocation(100, 100);
            setSize(600, 600);

            MouseManager mouseManager = new MouseManager();
            getContentPane().add(new MainPanel(mouseManager));
            //MyMouseListener mouseListener = new MyMouseListener(mouseManager);
            //addMouseMotionListener(mouseListener);
            //addMouseListener(mouseListener);
            setVisible(true);
        }
    }

    //
    static class WindowUpdater extends AbstractUpdater {
        private static final long FPS = 50;
        private static final DelayOptions DELAY_OPTIONS = new DelayOptions(DelayType.FPS, FPS);
        private final Window window;

        //
        WindowUpdater(Window window) {
            super(DELAY_OPTIONS);
            this.window = window;
        }

        @Override
        public void update() {
            TestData.x ++;
            window.repaint();
        }
    }

    //
    static class TestData {
        public static int x = 0;
        public static final @NotNull List<int @NotNull []> points = new ArrayList<>();
    }

    //
    static class MainPanel extends JPanel {
        private final MouseManager mouseManager;

        //
        MainPanel(MouseManager mouseManager) {
            setBackground(Color.BLACK);
            this.mouseManager = mouseManager;
            MyMouseListener mouseListener = new MyMouseListener(mouseManager);
            addMouseMotionListener(mouseListener);
            addMouseListener(mouseListener);
        }

        //
        @Override
        public void paint(Graphics g) {
            super.paintComponent(g);
            paintPoints(g);
            paintText(g);
        }

        private void paintText(Graphics g) {
            g.setColor(Color.WHITE);
            g.drawString("Habitat Editor", 20, 35);
            g.drawString("x: " + TestData.x, 20, 50);
            int @NotNull [] mouseLocation = mouseManager.getMouseLocation();
            g.drawString("mouse x: " + mouseLocation[0] + ", y: " + mouseLocation[1], 20, 65);
        }

        private void paintPoints(Graphics g) {
            int pointSize = 10;
            g.setColor(Color.RED);
            for (int @NotNull [] point : TestData.points) {
                g.fillOval(point[0] - pointSize / 2, point[1] - pointSize / 2, pointSize, pointSize);
            }
        }
    }

    static class MouseManager {
        int
                mouseX,
                mouseY;

        //
        MouseManager() {
            mouseX = 0;
            mouseY = 0;
            printLine("console works. new mouse manager");
        }

        int @NotNull [] getMouseLocation() {
            return new int [] {mouseX, mouseY};
        }

        //
        @SuppressWarnings("unused")
        void mouseClicked(Point location, int button) {
            mouseMoved(location);
            TestData.points.add(getMouseLocation());
            printLine("mouseClicked, x: " + mouseX + ", y: " + mouseY);
            //TODO: handle actions here by button
        }

        //
        void mouseMoved(Point location) {
            mouseX = location.x;
            mouseY = location.y;
        }
    }

    //
    static class MyMouseListener implements MouseMotionListener, MouseInputListener {
        private final @NotNull MouseManager mouseManager;

        //
        MyMouseListener(@NotNull MouseManager mouseManager) {
            super();
            this.mouseManager = mouseManager;
        }

        //
        @Override
        public void mouseMoved(@NotNull MouseEvent e) {
            mouseManager.mouseMoved(e.getPoint());
        }

        //
        @Override
        public void mouseDragged(@NotNull MouseEvent e) {
            mouseManager.mouseMoved(e.getPoint());
        }

        //
        @Override
        public void mouseClicked(@NotNull MouseEvent e) {
            //mouseManager.mouseClicked(e.getPoint(), e.getButton());
        }

        //
        @Override
        public void mousePressed(@NotNull MouseEvent e) {
            mouseManager.mouseClicked(e.getPoint(), e.getButton());
        }

        //
        @Override
        public void mouseReleased(@NotNull MouseEvent e) {
            //mouseManager.mouseClicked(e.getPoint(), e.getButton());
        }

        //
        @Override
        public void mouseEntered(@NotNull MouseEvent e) {
            //
        }

        //
        @Override
        public void mouseExited(@NotNull MouseEvent e) {
            //
        }
    }
}
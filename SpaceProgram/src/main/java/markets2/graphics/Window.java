package markets2.graphics;

import java.awt.Color;
import javax.swing.JFrame;

import org.jetbrains.annotations.NotNull;

import delayCalculator.delayOptions.DelayType;
import delayCalculator.delayOptions.DelayOptions;
import ThreadAbstraction.AbstractUpdater;

import markets2.World;
import markets2.graphics.mainPanel.MainPanel;

//
public final class Window extends JFrame {
    private static final int WINDOW_FPS = 35;
    private static final @NotNull Color BACKGROUND_COLOR = Color.black;

    //
    public Window(@NotNull World world) {
        super();
        setTitle("Markets 2 graphics");
        setSize(1250, 800);
        setLocation(50, 50);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBackground(BACKGROUND_COLOR);

        add(new MainPanel(world));
        //add more panels, listeners, etc here

        setVisible(true);
        new WindowUpdater(this).start();
    }

    private static final class WindowUpdater extends AbstractUpdater {
        private final @NotNull Window window;

        //
        WindowUpdater(@NotNull Window window) {
            super(new DelayOptions(DelayType.FPS, WINDOW_FPS));
            this.window = window;
        }

        //
        @Override
        public void update() {
            window.repaint();
        }
    }
}
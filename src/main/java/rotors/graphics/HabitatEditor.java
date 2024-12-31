package rotors.graphics;

import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;
import delayCalculator.delayOptions.DelayType;
import delayCalculator.delayOptions.DelayOptions;
import ThreadAbstraction.AbstractUpdater;

import dimensions.distance.Distance;

//
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
            setSize(1000, 700);

            MouseManager mouseManager = new MouseManager();
            getContentPane().add(new MainPanel(mouseManager));
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
        public static final @NotNull TestGrid grid = new TestGrid();
        static {
            @NotNull TestGridSection
                    section1 = new TestGridSection(7, 7),
                    section2 = new TestGridSection(5, 3),
                    section3 = new TestGridSection(5, 5);
            try {
                section1.setCell(2, 2, new TestGridCell(Color.green));
                section2.setCell(2, 2, new TestGridCell(Color.green));
                section3.setCell(2, 2, new TestGridCell(Color.green));
            } catch (@NotNull TestGridSection.CellTakenException ignored) {}
            grid.sections.add(section1);
            grid.sections.add(section2);
            grid.sections.add(section3);
        }
        public static final @NotNull List<int @NotNull []> points = new ArrayList<>();
        public static double scale = 30;
        public static int @NotNull [] activeCellLocation = new int[3]; //section, x, y
        public static int x = 0;
    }

    static class TestGrid {
        @NotNull List<@NotNull TestGridSection> sections;

        //
        TestGrid() {
            sections = new ArrayList<>();
        }
    }

    static class TestGridSection {
        @Nullable TestGridCell @NotNull [] @NotNull [] cells;

        //
        TestGridSection(int length, int width) {
            cells = new TestGridCell[length][width];
        }

        public @Nullable TestGridCell getCell(int lengthIndex, int widthIndex) {
            return cells[lengthIndex][widthIndex];
        }

        public void setCell(int lengthIndex, int widthIndex, @Nullable TestGridCell cell) throws CellTakenException {
            if (cell != null && getCell(lengthIndex, widthIndex) != null) {
                throw new CellTakenException();
            }
            cells[lengthIndex][widthIndex] = cell;
        }

        public void setCell(int lengthIndex, int widthIndex) {
            try {
                setCell(lengthIndex, widthIndex, null);
            } catch (@NotNull CellTakenException ignored) {}
        }

        public static class CellTakenException extends Exception {}
    }

    static class TestGridCell {
        public static final @NotNull Distance CELL_SIZE = new Distance(1.5);
        Color color;

        //
        TestGridCell(Color color) {
            this.color = color;
        }
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
        public void paint(@NotNull Graphics g) {
            super.paintComponent(g);
            paintGrid(g);
            paintPoints(g);
            paintText(g);
        }

        private void paintGrid(@NotNull Graphics g) {
            int @NotNull [] commonOffset = new int[] {30, 300};
            double scale = TestData.scale;
            @NotNull List<@NotNull TestGridSection> sections = TestData.grid.sections;
            double takenDrawLength = 0;
            for (int i = 0; i < sections.size(); i++) {
                int @NotNull [] offset = new int[] {
                        (int) (commonOffset[0] + takenDrawLength),
                        commonOffset[1]};
                @NotNull TestGridSection section = sections.get(i);
                boolean activeSection = i == TestData.activeCellLocation[0];
                takenDrawLength += paintSection(g, offset, scale, section, activeSection);
            }
        }

        //returns draw length for section synchronization
        private double paintSection(@NotNull Graphics g, int @NotNull [] offset, double scale,
                                    @NotNull TestGridSection section, boolean activeSection) {
            @NotNull Color sectionBorderColor = Color.gray;
            int
                    sectionLengthCells = section.cells.length,
                    sectionWidthCells = section.cells[0].length;
            g.setColor(sectionBorderColor);
            double
                    sectionLengthDraw = sectionLengthCells * TestGridCell.CELL_SIZE.getSI() * scale,
                    sectionWidthDraw = sectionWidthCells * TestGridCell.CELL_SIZE.getSI() * scale;
            int
                    drawX = offset[0],
                    drawY = (int) (offset[1] - sectionWidthDraw / 2);
            g.drawRect(drawX, drawY, (int) sectionLengthDraw, (int) sectionWidthDraw);
            paintCellGrid(g, new int[] {drawX, drawY}, scale, section, activeSection);
            return sectionLengthDraw;
        }

        private void paintCellGrid(@NotNull Graphics g, int @NotNull [] offset, double scale,
                                   @NotNull TestGridSection section, boolean activeSection) {
            @NotNull Color
                    cellBorderColor = Color.darkGray,
                    activeCellColor = Color.yellow;
            for (int i = 0; i < section.cells.length; i++) {
                @Nullable TestGridCell @NotNull [] column = section.cells[i];
                for (int j = 0; j < column.length; j++) {
                    @Nullable TestGridCell cell = column[j];
                    double scaledCellSize = TestGridCell.CELL_SIZE.getSI() * scale;
                    int borderInset = 1;
                    if (activeSection && i == TestData.activeCellLocation[1] && j == TestData.activeCellLocation[2]) {
                        g.setColor(activeCellColor);
                    } else {
                        g.setColor(cellBorderColor);
                    }
                    int
                            cellX = offset[0] + (int) (scaledCellSize * i),
                            cellY = offset[1] + (int) (scaledCellSize * j);
                    g.drawRect(
                             cellX + borderInset, cellY + borderInset,
                            (int) (scaledCellSize - borderInset * 2),
                            (int) (scaledCellSize - borderInset * 2));
                    int contentsInset = 3;
                    if (cell != null) {
                        g.setColor(cell.color);
                        g.fillRect(
                                cellX + contentsInset, cellY + contentsInset,
                                (int) (scaledCellSize - contentsInset * 2),
                                (int) (scaledCellSize - contentsInset * 2));
                    }
                }
            }
        }

        private void paintPoints(@NotNull Graphics g) {
            int pointSize = 10;
            g.setColor(Color.RED);
            for (int @NotNull [] point : TestData.points) {
                g.fillOval(point[0] - pointSize / 2, point[1] - pointSize / 2, pointSize, pointSize);
            }
        }

        private void paintText(@NotNull Graphics g) {
            g.setColor(Color.WHITE);
            g.drawString("Habitat Editor", 20, 35);
            g.drawString("x: " + TestData.x, 20, 50);
            int @NotNull [] mouseLocation = mouseManager.getMouseLocation();
            g.drawString("mouse x: " + mouseLocation[0] + ", y: " + mouseLocation[1], 20, 65);
        }
    }

    static class MouseManager {
        private static final int
                SECTION_START_X = 30,
                GRID_CENTER_Y = 300;
        int
                mouseX,
                mouseY;

        //
        MouseManager() {
            mouseX = 0;
            mouseY = 0;
        }

        int @NotNull [] getMouseLocation() {
            return new int [] {mouseX, mouseY};
        }

        //
        @SuppressWarnings("unused")
        void mouseClicked(Point location, int button) {
            mouseMoved(location);
            printLine("mouseClicked, x: " + mouseX + ", y: " + mouseY);
            TestData.points.add(getMouseLocation());
            int @NotNull [] activeCellLocation = TestData.activeCellLocation;
            @NotNull TestGridCell newCell = new TestGridCell(Color.green);
            try {
                TestData.grid.sections.get(activeCellLocation[0]).setCell(activeCellLocation[1], activeCellLocation[2], newCell);
            } catch (@NotNull TestGridSection.CellTakenException ignored) {
                TestData.grid.sections.get(activeCellLocation[0]).setCell(activeCellLocation[1], activeCellLocation[2]);
            }
            //TODO: handle actions here by button
        }

        //
        void mouseMoved(Point location) {
            mouseX = location.x;
            mouseY = location.y;
            checkClosestCell();
        }

        private void checkClosestCell() {
            //get active section
            double sectionStartX = SECTION_START_X;
            for (int i = 0; i < TestData.grid.sections.size(); i++) {
                @NotNull TestGridSection section = TestData.grid.sections.get(i);
                double scaledCellSize = TestGridCell.CELL_SIZE.getSI() * TestData.scale;
                double sectionDrawLength = section.cells.length * scaledCellSize;
                double sectionEndX = sectionStartX + sectionDrawLength;
                if (mouseX >= sectionStartX && mouseX < sectionEndX) {
                    TestData.activeCellLocation[0] = i;

                    //get active column within the section
                    double columnStartX = sectionStartX;
                    for (int j = 0; j < section.cells.length; j++) {
                        @Nullable TestGridCell @NotNull [] column = section.cells[j];
                        double columnEndX = columnStartX + scaledCellSize;
                        if (mouseX >= columnStartX && mouseX < columnEndX) {
                            TestData.activeCellLocation[1] = j;

                            //get active cell within the column
                            double sectionStartY = GRID_CENTER_Y - column.length * scaledCellSize / 2;
                            for (int k = 0; k < column.length; k++) {
                                double cellStartY = sectionStartY + k * scaledCellSize;
                                if (mouseY >= cellStartY && mouseY < cellStartY + scaledCellSize) {
                                    TestData.activeCellLocation[2] = k;
                                    @Nullable TestGridCell cell = column[k];
                                    break;
                                }
                            }
                            break;
                        }
                        columnStartX = columnEndX;
                    }
                    break;
                }
                sectionStartX = sectionEndX;
            }
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
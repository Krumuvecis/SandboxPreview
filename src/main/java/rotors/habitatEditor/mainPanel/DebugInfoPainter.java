package rotors.habitatEditor.mainPanel;

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import rotors.habitatEditor.window.UserData;
import rotors.habitatEditor.window.ObserverData;

//
interface DebugInfoPainter {
    @NotNull Color TEXT_COLOR = Color.WHITE;
    int @NotNull []
            TEXT_LOCATION_1 = new int[] {20, 20},
            TEXT_LOCATION_2 = new int[] {420, 20};
    int TEXT_HEIGHT = 15;

    //
    default void paintDebugInfo(@NotNull Graphics g, @NotNull UserData userData,
                                @NotNull ObserverData observerData, @NotNull MainPanel mainPanel,
                                int @NotNull [] mouseLocation, double scale, double @NotNull [] mouseLocation_absoluteUnscaled,
                                @NotNull List<@NotNull KeyEvent> pressedKeys) {
        paintLines(g, TEXT_LOCATION_1, new ArrayList<>() {{
            addAll(getIntroLines());
            addAll(getPanelInfoLines(mainPanel, mouseLocation));
            addAll(getUnscaledViewInfoLines(observerData.getViewLocationActual(), scale, mouseLocation_absoluteUnscaled));
            addAll(getActiveCellInfoLines(userData.getTabData().getRotorsTabData().getHabitatTabData().getActiveCellLocation()));
        }});
        paintLines(g, TEXT_LOCATION_2, new ArrayList<>() {{
            addAll(getPressedKeysInfoLines(pressedKeys));
        }});
    }

    private void paintLines(@NotNull Graphics g, int @NotNull [] location, @NotNull List<@Nullable String> lines) {
        g.setColor(TEXT_COLOR);
        for (int i = 0; i < lines.size(); i++) {
            @Nullable String line = lines.get(i);
            if (line != null) {
                g.drawString(line, location[0], location[1] + TEXT_HEIGHT * (i + 1));
            }
        }
    }

    private @NotNull List<@Nullable String> getIntroLines() {
        return new ArrayList<>() {{
            add("Habitat Editor");
            add("");
        }};
    }

    private @NotNull List<@Nullable String> getPanelInfoLines(@NotNull MainPanel mainPanel, int @NotNull [] mouseLocation) {
        return new ArrayList<>() {{
            add("panel size x: " + mainPanel.getWidth() + ", y: " + mainPanel.getHeight());
            double @NotNull [] panelCenter = mainPanel.getCenter();
            int decimalPlaces = 2;
            add("panel center " +
                    "x: " + doubleToString(panelCenter[0], decimalPlaces) + ", " +
                    "y: " + doubleToString(panelCenter[1], decimalPlaces));
            add("mouse on panel x: " + mouseLocation[0] + ", y: " + mouseLocation[1]);
            add("");
        }};
    }

    private @NotNull List<@Nullable String> getUnscaledViewInfoLines(double @NotNull [] viewLocationActual,
                                                                     double scale,
                                                                     double @NotNull [] mouseLocation_absoluteUnscaled) {
        return new ArrayList<>() {{
            int decimalPlaces = 2;
            add("view location actual " +
                    "x: " + doubleToString(viewLocationActual[0], decimalPlaces) + ", " +
                    "y: " + doubleToString(viewLocationActual[1], decimalPlaces));
            add("scale: " + doubleToString(scale, decimalPlaces));
            add("mouse actual absolute " +
                    "x: " + doubleToString(mouseLocation_absoluteUnscaled[0], decimalPlaces) + ", " +
                    "y: " + doubleToString(mouseLocation_absoluteUnscaled[1], decimalPlaces));
            add("");
        }};
    }

    private @NotNull List<@Nullable String> getActiveCellInfoLines(int @Nullable [] activeCellLocation) {
        return new ArrayList<>() {{
            @NotNull String activeCellString;
            if (activeCellLocation == null) {
                activeCellString = ": null";
            } else {
                activeCellString = " section: " + activeCellLocation[0]
                        + ", column: " + activeCellLocation[1]
                        + ", cell: " + activeCellLocation[2];
            }
            add("active cell" + activeCellString);
            add("");
        }};
    }

    private @NotNull List<@Nullable String> getPressedKeysInfoLines(@NotNull List<@NotNull KeyEvent> pressedKeys) {
        return new ArrayList<>() {{
            add("Pressed keys: ");
            for (@NotNull KeyEvent e : pressedKeys) {
                int keyCode = e.getKeyCode();
                add(keyCode + " - " + KeyEvent.getKeyText(keyCode));
            }
        }};
    }
}
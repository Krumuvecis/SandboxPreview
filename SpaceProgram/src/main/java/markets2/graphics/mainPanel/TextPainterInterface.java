package markets2.graphics.mainPanel;

import java.util.Objects;
import java.awt.Color;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//
interface TextPainterInterface {
    int TEXT_HEIGHT = 15;
    @NotNull Color
            TEXT_COLOR = new Color(255, 255, 255),
            TEXT_COLOR_GOOD = new Color(120, 255, 100),
            TEXT_COLOR_NORMAL = new Color(240, 240, 0),
            TEXT_COLOR_BAD = new Color(255, 60, 40);

    //
    default @NotNull Color determineColor(double value, double badRange, double goodRange) {
        boolean
                isGood = false,
                isBad = false;
        if (badRange < goodRange) { //ascending to good
            if (value >= goodRange) isGood = true;
            if (value <= badRange) isBad = true;
        }
        if (goodRange < badRange) { //descending to good
            if (value <= goodRange) isGood = true;
            if (value >= badRange) isBad = true;
        }

        if (isGood) return TEXT_COLOR_GOOD;
        if (isBad) return TEXT_COLOR_BAD;
        return TEXT_COLOR_NORMAL;
    }

    //
    default void drawColoredString(@NotNull Graphics g, @Nullable Color color, @NotNull String string, int x, int y) {
        g.setColor(Objects.requireNonNullElse(color, TEXT_COLOR));
        g.drawString(string, x, y);
    }
}
package rotors.habitatEditor.mainPanel.sectionPainting;

import java.awt.Color;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;

//contains functionality for painting a border and a semi-transparent overlay over a rectangle
interface BorderAndOverlayPainter {
    double DEFAULT_HIGHLIGHT_OVERLAY_OPACITY = 0.2;
    @NotNull Color
            DEFAULT_HIGHLIGHT_COLOR_OPAQUE = Color.yellow,
            DEFAULT_HIGHLIGHT_COLOR_TRANSPARENT = new Color(
                    DEFAULT_HIGHLIGHT_COLOR_OPAQUE.getRed(),
                    DEFAULT_HIGHLIGHT_COLOR_OPAQUE.getGreen(),
                    DEFAULT_HIGHLIGHT_COLOR_OPAQUE.getBlue(),
                    (int) (255 * DEFAULT_HIGHLIGHT_OVERLAY_OPACITY));

    //fully-custom colors
    default void paintBorderAndOverlay(@NotNull Graphics g, double @NotNull [] paintLocation, double @NotNull [] paintSize,
                                       int inset, boolean active, @NotNull Color inactiveBorderColor,
                                       @NotNull Color highlightColor_opaque, @NotNull Color highlightColor_transparent) {
        @NotNull Color borderColor = inactiveBorderColor;
        if (active) {
            paintOverlay(g, paintLocation, paintSize, inset, highlightColor_transparent);
            borderColor = highlightColor_opaque;
        }
        paintBorder(g, paintLocation, paintSize, inset, borderColor);
    }

    //default highlight colors
    default void paintBorderAndOverlay(@NotNull Graphics g, double @NotNull [] paintLocation, double @NotNull [] paintSize,
                                       int inset, boolean active, @NotNull Color inactiveBorderColor) {
        paintBorderAndOverlay(g, paintLocation, paintSize, inset, active,
                inactiveBorderColor, DEFAULT_HIGHLIGHT_COLOR_OPAQUE, DEFAULT_HIGHLIGHT_COLOR_TRANSPARENT);
    }

    //
    default void paintBorder(@NotNull Graphics g, double @NotNull [] paintLocation, double @NotNull [] paintSize,
                             int inset, @NotNull Color color) {
        g.setColor(color);
        g.drawRect(
                (int) (paintLocation[0] + inset), (int) (paintLocation[1] + inset),
                (int) (paintSize[0] - inset * 2), (int) (paintSize[1] - inset * 2));
    }

    //
    default void paintOverlay(@NotNull Graphics g, double @NotNull [] paintLocation, double @NotNull [] paintSize,
                              int inset, @NotNull Color color) {
        g.setColor(color);
        g.fillRect(
                (int) (paintLocation[0] + inset), (int) (paintLocation[1] + inset),
                (int) (paintSize[0] - inset * 2), (int) (paintSize[1] - inset * 2));
    }
}
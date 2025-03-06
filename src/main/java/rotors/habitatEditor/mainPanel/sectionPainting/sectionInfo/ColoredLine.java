package rotors.habitatEditor.mainPanel.sectionPainting.sectionInfo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

//
final class ColoredLine {
    private final @NotNull String line;
    private final @Nullable Color color;

    //
    ColoredLine(@NotNull String line, @Nullable Color color) {
        this.line = line;
        this.color = color;
    }

    //
    ColoredLine(@NotNull String line) {
        this(line, null);
    }

    //
    public @NotNull String line() {
        return line;
    }

    //
    public @Nullable Color color() {
        return color;
    }
}

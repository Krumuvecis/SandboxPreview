package rotors.habitatEditor.mainPanel.sectionPainting.sectionInfo;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import rotors.modularHabitat.habitatSection.HabitatSection;
import rotors.habitatEditor.window.UserData;
import rotors.modularHabitat.habitatSection.SectionRigging;

//
public interface SectionInfoPainter {
    int TEXT_HEIGHT = 15;
    @NotNull Color DEFAULT_TEXT_COLOR = Color.white;

    //
    @SuppressWarnings("DuplicateBranchesInSwitch")
    default void paintSectionInfo(@NotNull Graphics g, int @NotNull [] paintLocation, @NotNull HabitatSection section,
                                  @NotNull UserData.InfoDisplayAmount infoAmount) {
        @NotNull ArrayList<@Nullable ColoredLine> lines = new ArrayList<>();
        //get some common variables here
        double acceleration = 9.8; //TODO: this should come from the habitat, from its radius and omega
        @NotNull SectionRigging rigging = section.getRigging();

        switch (infoAmount) {
            case NONE -> {}
            case MINIMAL -> {
                SectionInfoLinePopulator.populateLines_minimal(lines, section, acceleration, rigging);
            }
            case NORMAL -> {
                SectionInfoLinePopulator.populateLines_normal(lines, section, acceleration, rigging);
            }
            case FULL -> {
                SectionInfoLinePopulator.populateLines_full(lines, section, acceleration, rigging);
            }
            default -> {}
        }
        paintSectionInfoLines(g, paintLocation, lines);
    }

    private void paintSectionInfoLines(@NotNull Graphics g, int @NotNull [] paintLocation,
                                       @NotNull List<@Nullable ColoredLine> lines) {
        for (int i = 0; i < lines.size(); i++) {
            @Nullable ColoredLine line = lines.get(i);
            if (line != null) {
                g.setColor(Objects.requireNonNullElse(line.color(), DEFAULT_TEXT_COLOR));
                g.drawString(line.line(), paintLocation[0], paintLocation[1] + i * TEXT_HEIGHT);
            }
        }
    }
}
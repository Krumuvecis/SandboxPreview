package rotors.habitatEditor.mainPanel.sectionPainting;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import rotors.modularHabitat.habitatSection.SectionRigging;
import rotors.modularHabitat.habitatSection.HabitatSection;
import rotors.habitatEditor.window.UserData;
import rotors.habitatEditor.mainPanel.sectionPainting.SectionInfoLines.ColoredLine;

//
interface SectionInfoPainter {
    int TEXT_HEIGHT = 15;
    @NotNull Color DEFAULT_TEXT_COLOR = Color.white;

    //
    default void paintSectionInfo(@NotNull Graphics g, int @NotNull [] paintLocation, @NotNull HabitatSection section,
                                  @NotNull UserData.InfoDisplayAmount infoAmount) {
        @NotNull ArrayList<@Nullable ColoredLine> lines = new ArrayList<>();
        double acceleration = 9.8; //TODO: this should come from the habitat, from its radius and omega
        @NotNull SectionRigging rigging = section.getRigging();
        @NotNull Mass
                riggingMass = rigging.getMass(),
                carryableMass = rigging.getCarryableMass(acceleration),
                dryCarriedMass = section.getDryCarriedMass(),
                totalDryMass = section.getDryMass();

        populateLines_emptySection(lines, infoAmount, section,
                riggingMass, carryableMass, dryCarriedMass, totalDryMass);
        lines.add(null);

        populateLines_cells(lines, infoAmount, section, carryableMass, dryCarriedMass, totalDryMass);
        lines.add(null);

        populateLines_finalTotals(lines, infoAmount, section, carryableMass);

        paintSectionInfoLines(g, paintLocation, lines);
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    private void populateLines_emptySection(@NotNull ArrayList<@Nullable ColoredLine> lines,
                                            @NotNull UserData.InfoDisplayAmount infoAmount,
                                            @NotNull HabitatSection section,
                                            @NotNull Mass riggingMass, @NotNull Mass carryableMass,
                                            @NotNull Mass dryCarriedMass, @NotNull Mass totalDryMass) {
        switch (infoAmount) {
            case NONE -> {}
            case MINIMAL -> {
                //TODO: add some info here
            }
            case NORMAL -> {
                //TODO: add some info here
            }
            case FULL -> {
                lines.add(SectionInfoLines.riggingMass(riggingMass, carryableMass));
                @NotNull Mass structureMass = section.getStructure().getMass();
                lines.add(SectionInfoLines.structureMass(structureMass));
                lines.add(SectionInfoLines.dryCarriedMass(dryCarriedMass, carryableMass));
                lines.add(SectionInfoLines.totalDryMass(totalDryMass));
            }
            default -> {}
        }
    }

    @SuppressWarnings("DuplicateBranchesInSwitch")
    private void populateLines_cells(@NotNull ArrayList<@Nullable ColoredLine> lines,
                                     @NotNull UserData.InfoDisplayAmount infoAmount,
                                     @NotNull HabitatSection section, @NotNull Mass carryableMass,
                                     @NotNull Mass dryCarriedMass, @NotNull Mass totalDryMass) {
        switch (infoAmount) {
            case NONE -> {}
            case MINIMAL -> {
                //TODO: add some info here
            }
            case NORMAL -> {
                //TODO: add some info here
            }
            case FULL -> {
                @NotNull Mass cellsMassCapacity_total = new Mass(carryableMass.getSI() - dryCarriedMass.getSI());
                @NotNull Mass cellsMass = section.getCellsMass();
                boolean cellsMassExceeded = cellsMass.getSI() > cellsMassCapacity_total.getSI();
                lines.add(SectionInfoLines.cellsMass(cellsMass, cellsMassCapacity_total, cellsMassExceeded));
                @NotNull Mass cellsMassCapacity_remaining = new Mass(
                        cellsMassCapacity_total.getSI() - cellsMass.getSI());
                lines.add(SectionInfoLines.remainingCellsMass(
                        cellsMassCapacity_remaining, cellsMassCapacity_total, cellsMassExceeded));
                double
                        cellsMassEfficiency_max = cellsMassCapacity_total.getSI() / totalDryMass.getSI(),
                        cellsMassEfficiency_actual = cellsMass.getSI() / totalDryMass.getSI();
                lines.add(SectionInfoLines.cellsMassEfficiency(
                        cellsMassEfficiency_max, cellsMassEfficiency_actual, 2, cellsMassExceeded));
            }
            default -> {}
        }
    }

    @SuppressWarnings({"DuplicateBranchesInSwitch", "RedundantLabeledSwitchRuleCodeBlock"})
    private void populateLines_finalTotals(@NotNull ArrayList<@Nullable ColoredLine> lines,
                                           @NotNull UserData.InfoDisplayAmount infoAmount,
                                           @NotNull HabitatSection section, @NotNull Mass carryableMass) {
        @NotNull Mass totalMass = section.getMass();
        switch (infoAmount) {
            case NONE -> {}
            case MINIMAL -> {
                lines.add(SectionInfoLines.totalMass(totalMass));
            }
            case NORMAL -> {
                //TODO: remove some lines from here
                @NotNull Mass carriedMass = section.getCarriedMass();
                lines.add(SectionInfoLines.totalCarriedMass(carriedMass, carryableMass));
                lines.add(SectionInfoLines.totalMass(totalMass));
            }
            case FULL -> {
                @NotNull Mass carriedMass = section.getCarriedMass();
                lines.add(SectionInfoLines.totalCarriedMass(carriedMass, carryableMass));
                lines.add(SectionInfoLines.totalMass(totalMass));
            }
            default -> {}
        }
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
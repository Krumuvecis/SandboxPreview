package rotors.habitatEditor.mainPanel.sectionPainting.sectionInfo;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import rotors.rotatingHabitat.modularHabitat.habitatSection.SectionRigging;
import rotors.rotatingHabitat.modularHabitat.habitatSection.HabitatSection;


//TODO: finish this
@SuppressWarnings("MissingJavadoc")
class SectionInfoLinePopulator {
    static void populateLines_minimal(@NotNull ArrayList<@Nullable ColoredLine> lines,
                                      @NotNull HabitatSection section, double acceleration,
                                      @NotNull SectionRigging rigging) {
        @NotNull Mass
                carryableMass = rigging.getCarryableMass(acceleration),
                dryCarriedMass = section.getDryCarriedMass(),
                cellsMass = section.getCellsMass(),
                cellsMassCapacity_total = getMassCapacity(carryableMass, dryCarriedMass);
        lines.add(SectionInfoLines.cellsMass(
                cellsMass, cellsMassCapacity_total,
                massExceeded(cellsMass, cellsMassCapacity_total)));
        lines.add(SectionInfoLines.totalMass(section.getMass()));
    }

    static void populateLines_normal(@NotNull ArrayList<@Nullable ColoredLine> lines,
                                     @NotNull HabitatSection section, double acceleration,
                                     @NotNull SectionRigging rigging) {
        @NotNull Mass
                riggingMass = rigging.getMass(),
                carryableMass = rigging.getCarryableMass(acceleration),
                dryCarriedMass = section.getDryCarriedMass();

        lines.add(SectionInfoLines.riggingMass(riggingMass, carryableMass));
        @NotNull Mass structureMass = section.getStructure().getMass();
        lines.add(SectionInfoLines.structureMass(structureMass));

        @NotNull Mass cellsMass = section.getCellsMass();
        @NotNull Mass cellsMassCapacity_total = getMassCapacity(carryableMass, dryCarriedMass);
        lines.add(SectionInfoLines.cellsMass(
                cellsMass, cellsMassCapacity_total,
                massExceeded(cellsMass, cellsMassCapacity_total)));
        @NotNull Mass carriedMass = section.getCarriedMass();
        lines.add(SectionInfoLines.totalCarriedMass(carriedMass, carryableMass));
        lines.add(null);
        @NotNull Mass totalMass = section.getMass();
        lines.add(SectionInfoLines.totalMass(totalMass));
    }

    static void populateLines_full(@NotNull ArrayList<@Nullable ColoredLine> lines,
                                   @NotNull HabitatSection section, double acceleration,
                                   @NotNull SectionRigging rigging) {
        @NotNull Mass
                riggingMass = rigging.getMass(),
                carryableMass = rigging.getCarryableMass(acceleration),
                dryCarriedMass = section.getDryCarriedMass(),
                totalDryMass = section.getDryMass();

        lines.add(SectionInfoLines.riggingMass(riggingMass, carryableMass));
        @NotNull Mass structureMass = section.getStructure().getMass();
        lines.add(SectionInfoLines.structureMass(structureMass));
        lines.add(SectionInfoLines.dryCarriedMass(dryCarriedMass, carryableMass));
        lines.add(SectionInfoLines.totalDryMass(totalDryMass));
        lines.add(null);

        @NotNull Mass cellsMassCapacity_total = getMassCapacity(carryableMass, dryCarriedMass);
        @NotNull Mass cellsMass = section.getCellsMass();
        boolean cellsMassExceeded = massExceeded(cellsMass, cellsMassCapacity_total);
        lines.add(SectionInfoLines.cellsMass(cellsMass, cellsMassCapacity_total, cellsMassExceeded));
        @NotNull Mass cellsMassCapacity_remaining = new Mass(
                cellsMassCapacity_total.getInBase() - cellsMass.getInBase());
        lines.add(SectionInfoLines.remainingCellsMass(
                cellsMassCapacity_remaining, cellsMassCapacity_total, cellsMassExceeded));
        double
                cellsMassEfficiency_max = cellsMassCapacity_total.getInBase() / totalDryMass.getInBase(),
                cellsMassEfficiency_actual = cellsMass.getInBase() / totalDryMass.getInBase();
        lines.add(SectionInfoLines.cellsMassEfficiency(
                cellsMassEfficiency_max, cellsMassEfficiency_actual, 2, cellsMassExceeded));
        lines.add(null);

        @NotNull Mass totalMass = section.getMass();
        @NotNull Mass carriedMass = section.getCarriedMass();
        lines.add(SectionInfoLines.totalCarriedMass(carriedMass, carryableMass));
        lines.add(SectionInfoLines.totalMass(totalMass));
    }

    private static @NotNull Mass getMassCapacity(@NotNull Mass maxCarryable, @NotNull Mass dryCarried) {
        return new Mass(maxCarryable.getInBase() - dryCarried.getInBase());
    }

    private static boolean massExceeded(@NotNull Mass mass, @NotNull Mass capacity) {
        return mass.getInBase() > capacity.getInBase();
    }
}
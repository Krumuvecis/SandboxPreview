package rotors.habitatEditor.mainPanel.sectionPainting.sectionInfo;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.stringTools.NumberFormatter.doubleToString;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
final class SectionInfoLines {
    private static final @NotNull Color WARNING_TEXT_COLOR = Color.red;

    //
    static @NotNull ColoredLine riggingMass(@NotNull Mass riggingMass, @NotNull Mass carryableMass) {
        @Nullable Color color = null;
        if (carryableMass.getValue() <= 0) { //unit is irrelevant
            color = WARNING_TEXT_COLOR; //red, if carryable mass is negative - inappropriate rigging
        }
        return new ColoredLine("Rigging mass: " + getMassString(riggingMass), color);
    }

    //
    static @NotNull ColoredLine structureMass(@NotNull Mass structureMass) {
        //TODO: colors?
        return new ColoredLine("Structure mass: " + getMassString(structureMass));
    }

    //
    static @NotNull ColoredLine dryCarriedMass(@NotNull Mass dryCarriedMass, @NotNull Mass carryableMass) {
        //TODO: colors?
        double dryVsCarryableRatio = dryCarriedMass.getInBase() / carryableMass.getInBase();
        return new ColoredLine("Dry carried mass: " +
                getMassString(dryCarriedMass) + " / " + getMassString(carryableMass) + ", " +
                doubleToString(dryVsCarryableRatio * 100, 1) + " %");
    }

    //
    static @NotNull ColoredLine totalDryMass(@NotNull Mass totalDryMass) {
        //TODO:
        // * whole section, including rigging, but excluding cells
        // * red, if cells' mass capacity is negative - dry mass too big
        return new ColoredLine("Total dry mass: " + getMassString(totalDryMass));
    }

    //
    static @NotNull ColoredLine cellsMass(@NotNull Mass cellsMass, @NotNull Mass cellsMassCapacity_total,
                                          boolean cellsMassExceeded) {
        double cellsMassCapacityFullness = cellsMass.getInBase() / cellsMassCapacity_total.getInBase();
        @NotNull String line = "Cells' mass: " +
                getMassString(cellsMass) + " / " + getMassString(cellsMassCapacity_total) + ", " +
                doubleToString(cellsMassCapacityFullness * 100, 1) + " %";
        @Nullable Color color = null;
        if (cellsMassExceeded) color = WARNING_TEXT_COLOR;
        return new ColoredLine(line, color);
    }

    //
    static @NotNull ColoredLine remainingCellsMass(@NotNull Mass cellsMassCapacity_remaining,
                                                   @NotNull Mass cellsMassCapacity_total,
                                                   boolean cellsMassExceeded) {
        double remainingCellsMassCapacityRatio = cellsMassCapacity_remaining.getInBase() / cellsMassCapacity_total.getInBase();
        @NotNull String line = "Remaining cells' mass capacity: " +
                getMassString(cellsMassCapacity_remaining) + " ," +
                doubleToString(remainingCellsMassCapacityRatio * 100, 1) + " %";
        @Nullable Color color = null;
        if (cellsMassExceeded) color = WARNING_TEXT_COLOR;
        return new ColoredLine(line, color);
    }

    //
    @SuppressWarnings("SameParameterValue")
    static @NotNull ColoredLine cellsMassEfficiency(double cellsMassEfficiency_max, double cellsMassEfficiency_actual,
                                                    int decimalPlaces, boolean cellsMassExceeded) {
        @NotNull String line = "Cells' mass efficiency: " +
                doubleToString(cellsMassEfficiency_actual, decimalPlaces) + " / " +
                doubleToString(cellsMassEfficiency_max, decimalPlaces);
        @Nullable Color color = null;
        if (cellsMassExceeded) color = WARNING_TEXT_COLOR;
        return new ColoredLine(line, color);
    }

    //
    static @NotNull ColoredLine totalCarriedMass(@NotNull Mass carriedMass, @NotNull Mass carryableMass) {
        double carriedVsCarryableRatio = carriedMass.getInBase() / carryableMass.getInBase();
        @Nullable Color color = null;
        if (carriedVsCarryableRatio > 1) {
            //too much overall carried mass
            color = WARNING_TEXT_COLOR;
        }
        return new ColoredLine("Total carried mass: " + getMassString(carriedMass) + " / " +
                getMassString(carryableMass) + ", " +
                doubleToString(carriedVsCarryableRatio * 100, 1) + " %", color);
    }

    //
    static @NotNull ColoredLine totalMass(@NotNull Mass totalMass) {
        return new ColoredLine("Total mass: " + getMassString(totalMass));
    }

    private static @NotNull String getMassString(@NotNull Mass mass) {
        @NotNull MassUnit commonMassUnit = MassUnit.T;
        int decimalPlaces = 1;
        return mass.getValueAndShortUnit(commonMassUnit, decimalPlaces);
    }
}
package rotors.modularHabitat.habitatSection;

import org.jetbrains.annotations.NotNull;

import utils.Copyable;
import dimensions.mass.Mass;
import basicParts.MassivePart;

//
public class SectionStructure implements MassivePart, Copyable<@NotNull SectionStructure> {
    private final int
            length, //column count, tangential axis
            width; //row count, axial axis
    private final @NotNull Mass
            massPerCell, //variable mass; hidden; kept for copying
            additionalMass, //fixed mass; hidden; kept for copying
            totalMass; //cached total; visible

    //even-width sections not supported
    public SectionStructure(int rowCount, int columnCount, @NotNull Mass massPerCell, @NotNull Mass additionalMass) {
        EvenException.checkEvenness(rowCount);
        this.length = columnCount;
        this.width = rowCount;
        this.massPerCell = massPerCell;
        this.additionalMass = additionalMass;
        totalMass = massPerCell.getMultiplied(getTotalCellCount()).getSum(additionalMass);
    }

    //
    public final int getColumnCount() {
        return length;
    }

    //
    public final int getRowCount() {
        return width;
    }

    private int getTotalCellCount() {
        return getColumnCount() * getRowCount();
    }

    //
    @Override
    public @NotNull Mass getMass() {
        return totalMass;
    }

    //
    @Override
    public @NotNull SectionStructure copy() {
        return new SectionStructure(getRowCount(), getColumnCount(), massPerCell, additionalMass);
    }
}
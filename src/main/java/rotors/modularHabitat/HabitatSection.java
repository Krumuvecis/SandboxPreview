package rotors.modularHabitat;

import java.util.Arrays;

import org.jetbrains.annotations.NotNull;
import rotors.modularHabitat.sectionStructure.SectionStructure;
import rotors.modularHabitat.sectionStructure.centralSubSection.StructureCentralSubSection;

//
public class HabitatSection extends SectionStructure {
    private final @NotNull HabitatSectionCell @NotNull [] @NotNull [] cells;
    private double
            maxCellMass, //contents only
            cellBaseMass, //structural floor to hold the contents
            baseMass, // total structural floor
            maxSectionMass; // total, including structural floor

    //
    public HabitatSection(@NotNull StructureCentralSubSection structureCenter,
                          @NotNull TriangularRib triangularRib_positive,
                          @NotNull TriangularRib triangularRib_negative) {
        super(structureCenter, triangularRib_positive, triangularRib_negative);
        int
                width = getRowCount(),
                length = getColumnCount();
        if (isEven(width)) {
            //even-width sections not supported
            throw new RuntimeException(new EvenWidthException());
        }

        cells = new HabitatSectionCell[length][width]; //list of columns
        //populate cells
        for (@NotNull HabitatSectionCell @NotNull [] column : cells) {
            Arrays.setAll(column, i -> new HabitatSectionCell());
        }
    }

    private static boolean isEven(int number) {
        return (number % 2) == 0;
    }

    //
    public final @NotNull HabitatSectionCell @NotNull [] getColumn(int index) {
        return cells[index];
    }

    //
    public final @NotNull HabitatSectionCell getCell(int lengthIndex, int widthIndex) {
        return cells[lengthIndex][widthIndex];
    }

    //gets called, when the corresponding parent section changes its index
    final void incrementSectionIndex() {
        for (@NotNull HabitatSectionCell [] column : cells) {
            for (@NotNull HabitatSectionCell cell : column) {
                cell.incrementSectionIndex();
            }
        }
    }

    //gets called, when the corresponding parent section changes its index
    final void decrementSectionIndex() {
        for (@NotNull HabitatSectionCell [] column : cells) {
            for (@NotNull HabitatSectionCell cell : column) {
                cell.decrementSectionIndex();
            }
        }
    }

    //
    public static final class EvenWidthException extends Exception {
        EvenWidthException() {
            super("Even-width sections not supported, unable to create a section");
        }
    }

    //
    public static final class SampleSection_7x7 extends HabitatSection {
        //
        public SampleSection_7x7() {
            super(
                    new DoubleSpanStructureCenter(
                            new CentralSpan.CentralSpan_7_10000t(),
                            new CentralRib.CentralRib_1_2000t()),
                    new TriangularRib.TriangularRib_3_300t(),
                    new TriangularRib.TriangularRib_3_300t());
        }
    }

    //
    public static final class SampleSection_5x3 extends HabitatSection {
        //
        public SampleSection_5x3() {
            super(
                    new DoubleSpanStructureCenter(
                            new CentralSpan.CentralSpan_3_2000t(),
                            new CentralRib.CentralRib_1_1000t()),
                    new TriangularRib.TriangularRib_2_200t(),
                    new TriangularRib.TriangularRib_2_200t());
        }
    }

    //
    public static final class SampleSection_5x5 extends HabitatSection {
        //
        public SampleSection_5x5() {
            super(
                    new DoubleSpanStructureCenter(
                            new CentralSpan.CentralSpan_5_5000t(),
                            new CentralRib.CentralRib_1_1000t()),
                    new TriangularRib.TriangularRib_2_200t(),
                    new TriangularRib.TriangularRib_2_200t());
        }
    }
}
package rotors.rotatingHabitat.modularHabitat.habitatSection;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import utils.Copyable;
import dimensions.distance.Distance;
import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import basicParts.MassivePart;
import basicParts.cables.CableMaterial;
import basicParts.cables.Cable;
import rotors.rotatingHabitat.modularHabitat.HabitatSectionCell;

//
public class HabitatSection implements MassivePart, Copyable<@NotNull HabitatSection> {
    private @NotNull SectionRigging rigging;
    private @NotNull SectionStructure structure;
    private @NotNull SectionCellContainer cells;
    private @NotNull Mass additionalSectionMass; //placeholder mass

    //
    public HabitatSection(@NotNull SectionRigging rigging, @NotNull SectionStructure structure,
                          @NotNull Mass additionalSectionMass) {
        this.rigging = rigging;
        this.structure = structure;
        this.cells = new SectionCellContainer(structure.getRowCount(), structure.getColumnCount());
        this.additionalSectionMass = additionalSectionMass;
    }

    //
    public final @NotNull SectionRigging getRigging() {
        return rigging;
    }

    //
    public final @NotNull SectionStructure getStructure() {
        return structure;
    }

    //gets the size of the section in number of cells {column count, row count}
    public final int @NotNull [] getSize() {
        return new int[] {structure.getColumnCount(), structure.getRowCount()};
    }

    //
    public final @NotNull HabitatSectionCell getCell(int lengthIndex, int widthIndex) {
        return cells.getCell(lengthIndex, widthIndex);
    }

    public final void iterateCells(@Nullable CellActionInterface cellAction) {
        cells.iterate(cellAction);
    }

    //gets called, when the corresponding parent section changes its index
    @SuppressWarnings("CommentedOutCode")
    public final void incrementSectionIndex() {
        //TODO: finish this
        /*for (@NotNull HabitatSectionCell [] column : cells) {
            for (@NotNull HabitatSectionCell cell : column) {
                cell.incrementSectionIndex();
            }
        }*/
    }

    //gets called, when the corresponding parent section changes its index
    @SuppressWarnings("CommentedOutCode")
    public final void decrementSectionIndex() {
        //TODO: finish this
        /*for (@NotNull HabitatSectionCell [] column : cells) {
            for (@NotNull HabitatSectionCell cell : column) {
                cell.decrementSectionIndex();
            }
        }*/
    }

    //carried mass without the cells
    public @NotNull Mass getDryCarriedMass() {
        return structure.getMass().getSum(additionalSectionMass);
    }

    //mass of all the cells
    public @NotNull Mass getCellsMass() {
        return cells.getMass();
    }

    //total, that's hanging from the rigging
    public @NotNull Mass getCarriedMass() {
        return getDryCarriedMass().getSum(getCellsMass());
    }

    //whole section, including rigging, but excluding cells
    public @NotNull Mass getDryMass() {
        return getDryCarriedMass().getSum(rigging.getMass());
    }

    //complete total mass; carried mass + rigging; total dry mass + cells
    @Override
    public @NotNull Mass getMass() {
        return getCarriedMass().getSum(rigging.getMass());
    }

    @Override
    public @NotNull HabitatSection copy() {
        return new HabitatSection(getRigging().copy(), getStructure().copy(), additionalSectionMass);
    }

    //
    public static final class SampleSection extends HabitatSection {
        private static final @NotNull Mass
                RIGGING_ADDITIONAL_MASS = new Mass(5, MassUnit.T),
                STRUCTURE_ADDITIONAL_MASS = new Mass(10, MassUnit.T),
                STRUCTURE_MASS_PER_CELL = new Mass(2, MassUnit.T),
                SECTION_ADDITIONAL_MASS = new Mass(10, MassUnit.T);

        //
        public SampleSection(@NotNull CableMaterial cableMaterial, @NotNull Distance cableDiameter,
                             @NotNull Distance rotorRadius,
                             int rowCount, int columnCount) {
            super(
                    new SectionRigging(new ArrayList<>() {{
                        add(new Cable[] {
                                getNewCable(cableMaterial, cableDiameter, rotorRadius),
                                getNewCable(cableMaterial, cableDiameter, rotorRadius)
                        });
                        add(new Cable[] {
                                getNewCable(cableMaterial, cableDiameter, rotorRadius),
                                getNewCable(cableMaterial, cableDiameter, rotorRadius)
                        });
                    }}, RIGGING_ADDITIONAL_MASS),
                    new SectionStructure(rowCount, columnCount, STRUCTURE_MASS_PER_CELL, STRUCTURE_ADDITIONAL_MASS),
                    SECTION_ADDITIONAL_MASS);
        }

        private static @NotNull Cable getNewCable(@NotNull CableMaterial cableMaterial, @NotNull Distance cableDiameter,
                                                  @NotNull Distance rotorRadius) {
            return new Cable(cableMaterial, cableDiameter, rotorRadius);
        }
    }
}
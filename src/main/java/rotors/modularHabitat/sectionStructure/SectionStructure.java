package rotors.modularHabitat.sectionStructure;

import org.jetbrains.annotations.NotNull;

import rotors.modularHabitat.sectionStructure.centralSubSection.StructureCentralSubSection;
import rotors.modularHabitat.sectionStructure.overhangSubSection.StructureOverhangSubSection;

//
public class SectionStructure {
    private final @NotNull StructureCentralSubSection structureCenter;
    private final @NotNull StructureOverhangSubSection
            negativeStructureOverhang, //towards negative x
            positiveStructureOverhang; //towards positive x
    private final int
            columnCount,
            rowCount;

    //
    public SectionStructure(@NotNull StructureCentralSubSection structureCenter) {
        this.structureCenter = structureCenter;
        rowCount = structureCenter.getRowCount();
        positiveStructureOverhang = new StructureOverhangSubSection(rowCount);
        negativeStructureOverhang = new StructureOverhangSubSection(rowCount);
        columnCount = (structureCenter.getCentralSpanCount() - 1)
                + positiveStructureOverhang.getColumnCount()
                + negativeStructureOverhang.getColumnCount();
    }

    //
    public final int getColumnCount() {
        return columnCount;
    }

    //
    public final int getRowCount() {
        return rowCount;
    }
}
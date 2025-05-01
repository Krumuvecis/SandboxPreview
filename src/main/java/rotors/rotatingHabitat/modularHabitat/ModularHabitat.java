package rotors.rotatingHabitat.modularHabitat;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import dimensions.distance.DistanceUnit;
import dimensions.distance.Distance;
import basicParts.cables.CableMaterial;
import rotors.rotatingHabitat.modularHabitat.particularModules.SampleModule_medium;
import rotors.rotatingHabitat.modularHabitat.habitatSection.HabitatSection;

//a modular habitat consisting of sections
public class ModularHabitat extends SectionContainer {
    private final @NotNull Distance radius;

    //
    public ModularHabitat(@NotNull Distance radius) {
        super();
        this.radius = radius;
    }

    //
    public @NotNull Distance getRadius() {
        return radius;
    }

    //gets length in number of cells for sections from 0 to index (included)
    public final int getLength(int index) {
        int sum = 0;
        @NotNull List<@NotNull HabitatSection> sections = getSections();
        for (int i = 0; i <= index && i < sections.size(); i++) {
            sum += sections.get(i).getSize()[0];
        }
        return sum;
    }

    //gets total length in number of cells
    public final int getLength() {
        return getLength(getSections().size() - 1);
    }

    //a sample habitat with sample sections
    public static final class SampleHabitat extends ModularHabitat {
        private static final @NotNull CableMaterial CABLE_MATERIAL = CableMaterial.NYLON;
        private static final @NotNull Distance
                CABLE_DIAMETER = new Distance(0.03, DistanceUnit.M),
                ROTOR_RADIUS = new Distance(10, DistanceUnit.KM);

        //
        public SampleHabitat() {
            super(ROTOR_RADIUS);

            //create some sample sections
            @NotNull HabitatSection
                    section1 = new HabitatSection.SampleSection(CABLE_MATERIAL, CABLE_DIAMETER, getRadius(), 7, 7),
                    section2 = new HabitatSection.SampleSection(CABLE_MATERIAL, CABLE_DIAMETER, getRadius(), 3, 5),
                    section3 = new HabitatSection.SampleSection(CABLE_MATERIAL, CABLE_DIAMETER, getRadius(), 5, 5);
            addSection_atEnd(section1);
            addSection_atEnd(section2);
            addSection_atEnd(section3);

            //fill some cells with sample modules
            @NotNull HabitatModule sampleModule = new SampleModule_medium();
            try {
                section1.getCell(2, 2).setModule(sampleModule.copy());
                section2.getCell(2, 2).setModule(sampleModule.copy());
                section3.getCell(2, 2).setModule(sampleModule.copy());
            } catch (@NotNull HabitatSectionCell.CellTakenException ignored) {}
        }
    }
}
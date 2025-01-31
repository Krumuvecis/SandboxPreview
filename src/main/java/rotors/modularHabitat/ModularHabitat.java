package rotors.modularHabitat;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import rotors.modularHabitat.particularModules.SampleModule_green;

//
public class ModularHabitat {
    private final @NotNull List<@NotNull HabitatSection> sections;

    //
    public ModularHabitat() {
        sections = new ArrayList<>();
    }

    //
    public final @NotNull List<@NotNull HabitatSection> getSections() {
        return Collections.unmodifiableList(sections);
    }

    //
    public final @NotNull HabitatSection getSection(int index) throws IndexOutOfBoundsException {
        return sections.get(index);
    }

    //gets length in number of cells for sections from 0 to index (included)
    public final int getLength(int index) {
        int sum = 0;
        for (int i = 0; i <= index && i < sections.size(); i++) {
            sum += sections.get(i).getColumnCount();
        }
        return sum;
    }

    //gets total length in number of cells
    public final int getLength() {
        return getLength(sections.size() - 1);
    }

    //
    public final void addSection_atEnd(@NotNull HabitatSection section) {
        sections.add(section);
    }

    //
    public final void addSection_atStart(@NotNull HabitatSection section) {
        insertSection(section, 0);
    }

    //
    public final void insertSection(@NotNull HabitatSection section, int index) throws IndexOutOfBoundsException {
        //TODO: finish this
        incrementSectionIndex(index);
        throw new RuntimeException("section inserting not supported yet");
    }

    //
    public final void removeSection(int index) throws IndexOutOfBoundsException {
        //TODO: finish this
        decrementSectionIndex(index + 1);
        throw new RuntimeException("section removal not supported yet");
    }

    //increments indexes for sections starting from startIndex
    private void incrementSectionIndex(int startIndex) {
        for (int i = startIndex; i < sections.size(); i++) {
            sections.get(i).incrementSectionIndex();
        }
    }

    //decrements indexes for sections starting from startIndex
    private void decrementSectionIndex(int startIndex) {
        for (int i = startIndex; i < sections.size(); i++) {
            sections.get(i).decrementSectionIndex();
        }
    }

    //
    public static final class SampleHabitat extends ModularHabitat {
        //
        public SampleHabitat() {
            super();
            @NotNull HabitatSection
                    section1 = new HabitatSection.SampleSection_7x7(),
                    section2 = new HabitatSection.SampleSection_5x3(),
                    section3 = new HabitatSection.SampleSection_5x5();
            try {
                section1.getCell(2, 2).setModule(new SampleModule_green());
                section2.getCell(2, 2).setModule(new SampleModule_green());
                section3.getCell(2, 2).setModule(new SampleModule_green());
            } catch (@NotNull HabitatSectionCell.CellTakenException ignored) {}
            addSection_atEnd(section1);
            addSection_atEnd(section2);
            addSection_atEnd(section3);
        }
    }
}
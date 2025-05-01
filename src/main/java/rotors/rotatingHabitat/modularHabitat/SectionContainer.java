package rotors.rotatingHabitat.modularHabitat;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import rotors.rotatingHabitat.modularHabitat.habitatSection.HabitatSection;

//
public abstract class SectionContainer {
    private final @NotNull List<@NotNull HabitatSection> sections;

    //
    SectionContainer() {
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

    //adds a section at the end
    public final void addSection_atEnd(@NotNull HabitatSection section) {
        sections.add(section);
    }

    //adds a section at the beginning
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
}
package chemistry.molecules;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import common.NamedInterface;
import chemistry.AtomicInterface;
import chemistry.Element;
import chemistry.SuperElementalContainer;

//
public class Molecule extends SuperElementalContainer<@NotNull Element, @NotNull Integer> implements NamedInterface {
    private final @NotNull String formula;
    private @Nullable String name;

    //
    public Molecule(@Nullable String name, @NotNull Map<@NotNull Element, @NotNull Integer> elements) {
        super(elements);
        formula = determineFormula();
        this.name = name;
    }

    //for unnamed molecules, uses formula for name
    public Molecule(@NotNull Map<@NotNull Element, @NotNull Integer> elements) {
        this(null, elements);
    }

    //for internal operations with constituents' values
    @Override
    protected final @NotNull Integer newValue() {
        return 0;
    }

    //for internal operations with constituents' values
    @Override
    protected final @NotNull Integer addToValue(@NotNull Integer v, @NotNull Integer d) {
        return v + d;
    }

    private @NotNull String determineFormula() {
        @NotNull Map<@NotNull Element, @NotNull Integer> elements = getConstituents();
        @NotNull StringBuilder formula = new StringBuilder();
        for (@NotNull Element element : elements.keySet()) {
            formula.append(element.getSymbol());
            int index = elements.get(element);
            if (index > 1) {
                formula.append(index);
            }
        }
        return formula.toString();
    }

    //
    public final @NotNull String getFormula() {
        return formula;
    }

    //
    @Override
    public final @NotNull String getName() {
        if (name == null) { //on-demand creates a new name
            name = determineNewName();
        }
        return name;
    }

    private @NotNull String determineNewName() {
        return getFormula();
    }

    //for internal operations of calculating constituent molar fractions
    @Override
    protected final <T extends @NotNull AtomicInterface> double getMolarFraction_withinConstituent(@NotNull Element constituent, T target) {
        if (constituent == target) {
            return 1;
        } else return 0;
    }
}
package chemistry.molecules;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import chemistry.Element;
import chemistry.Compound;

//
public class Molecule extends Compound<@NotNull Element, @NotNull Integer> {
    private final @NotNull String formula;

    //
    public Molecule(@Nullable String name, @NotNull Map<@NotNull Element, @NotNull Integer> elements) {
        super(name, elements);
        formula = determineFormula();
    }

    //for unnamed molecules, uses formula for name
    public Molecule(@NotNull Map<@NotNull Element, @NotNull Integer> elements) {
        this(null, elements);
    }

    //
    @Override
    public final @NotNull String determineNewName() {
        return getFormula();
    }

    //for operations with constituents' values
    @Override
    public final @NotNull Integer newValue() {
        return 0;
    }

    //for operations with constituents' values
    @Override
    public final @NotNull Integer addToValue(@NotNull Integer v, @NotNull Integer d) {
        return v + d;
    }

    private @NotNull String determineFormula() {
        @NotNull Map<@NotNull Element, @NotNull Integer> elements = getConstituents();
        @NotNull StringBuilder formula = new StringBuilder();
        for (@NotNull Element element : elements.keySet()) {
            formula.append(element.getSymbol());
            int index = elements.get(element);
            formula.append(index);
        }
        return formula.toString();
    }

    //
    public final @NotNull String getFormula() {
        return formula;
    }
}
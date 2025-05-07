package chemistry;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;

//
public abstract class SuperElementalContainer<K extends @NotNull AtomicInterface, V extends @NotNull Number>
        implements AtomicInterface {
    private final @NotNull Map<K, V> constituents;
    private final V constituentTotalMolarWeight;
    private final @NotNull Mass atomicMass;

    //
    public SuperElementalContainer(@NotNull Map<K, V> constituents) {
        this.constituents = constituents;
        constituentTotalMolarWeight = calculateConstituentTotalMolarWeight();
        atomicMass = calculateAtomicMass();
    }

    private V calculateConstituentTotalMolarWeight() {
        V totalMolarWeight = newValue();
        for (K constituent : constituents.keySet()) {
            totalMolarWeight = addToValue(totalMolarWeight, constituents.get(constituent));
        }
        return totalMolarWeight;
    }

    //for internal operations with constituents' values
    protected abstract V newValue();

    //for internal operations with constituents' values
    protected abstract V addToValue(V value, V addend);

    private @NotNull Mass calculateAtomicMass() {
        @NotNull Mass atomicMass = new Mass(0, MassUnit.G);
        for (K constituent : constituents.keySet()) {
            atomicMass.sum(constituent.getAtomicMass().getMultiplied(parseToDouble(constituents.get(constituent))));
        }
        return atomicMass;
    }

    //
    public final @NotNull Map<K, V> getConstituents() {
        return constituents;
    }

    //
    public final V getConstituentTotalMolarWeight() {
        return constituentTotalMolarWeight;
    }

    //
    @Override
    public final @NotNull Mass getAtomicMass() {
        return atomicMass;
    }

    //returns 0, if target not found
    public final <T extends @NotNull AtomicInterface> double getMolarFraction_generic(T target) {
        double molarFraction = 0;
        for (K constituent : constituents.keySet()) {
            double molarFractionWithinConstituent = getMolarFraction_withinConstituent(constituent, target);
            if (molarFractionWithinConstituent == 0) {
                continue;
            }
            double constituentMolarFraction = parseToDouble(constituents.get(constituent)) / parseToDouble(getConstituentTotalMolarWeight());
            molarFraction += molarFractionWithinConstituent * constituentMolarFraction;
        }
        return molarFraction;
    }

    private double parseToDouble(V value) {
        return Double.parseDouble(String.valueOf(value));
    }

    //for internal operations of calculating constituent molar fractions
    protected abstract <T extends @NotNull AtomicInterface> double getMolarFraction_withinConstituent(K constituent, T target);

    //returns 0, if constituent not found
    public final <T extends @NotNull AtomicInterface> double getMassFraction_generic(T target) {
        double molarFraction = getMolarFraction_generic(target);
        @NotNull Mass fractionMolarMass = target.getAtomicMass().getMultiplied(molarFraction * parseToDouble(getConstituentTotalMolarWeight()));
        return fractionMolarMass.getInBase() / getAtomicMass().getInBase();
    }

    //returns 0, if element not found
    public final double getMolarFraction_element(@NotNull Element element) {
        return getMolarFraction_generic(element);
    }

    //returns 0, if element not found
    public final double getMassFraction_element(@NotNull Element element) {
        return getMassFraction_generic(element);
    }

    //
    public final @NotNull Set<@NotNull Element> getAll_elements() {
        @NotNull Set<@NotNull Element> elements = new HashSet<>();
        for (K constituent : getConstituents().keySet()) {
            if (constituent instanceof @NotNull Element element) {
                elements.add(element);
            } else if (constituent instanceof @NotNull SuperElementalContainer<?, ?> compound) {
                elements.addAll(compound.getAll_elements());
            }
        }
        return elements;
    }

    //
    public final @NotNull List<@NotNull ConstituentFraction<@NotNull Element>> getElementsByMolarFraction() {
        @NotNull List<@NotNull ConstituentFraction<@NotNull Element>> orderedElements = new ArrayList<>();

        //gets all elements unordered
        @NotNull Set<@NotNull Element> unorderedElements = getAll_elements();

        //sorts the unsorted elements
        while (!unorderedElements.isEmpty()) {
            double biggestFraction = 0;
            @Nullable Element biggestElement = null;
            for (@NotNull Element element : unorderedElements) {
                double fraction = getMolarFraction_generic(element);
                if (fraction > biggestFraction) {
                    biggestFraction = fraction;
                    biggestElement = element;
                }
            }
            if (biggestElement == null) {
                //TODO: better error-handling
                break;
            }
            orderedElements.add(new ConstituentFraction<@NotNull Element>(biggestElement, biggestFraction));
            unorderedElements.remove(biggestElement);
        }
        return orderedElements;
    }

    //
    public final @NotNull List<@NotNull ConstituentFraction<@NotNull Element>> getElementsByMassFraction() {
        @NotNull List<@NotNull ConstituentFraction<@NotNull Element>> orderedElements = new ArrayList<>();

        //gets all elements unordered
        @NotNull Set<@NotNull Element> unorderedElements = getAll_elements();

        //sorts the unsorted elements
        while (!unorderedElements.isEmpty()) {
            double biggestFraction = 0;
            @Nullable Element biggestElement = null;
            for (@NotNull Element element : unorderedElements) {
                double fraction = getMassFraction_generic(element);
                if (fraction > biggestFraction) {
                    biggestFraction = fraction;
                    biggestElement = element;
                }
            }
            if (biggestElement == null) {
                //TODO: better error-handling
                break;
            }
            orderedElements.add(new ConstituentFraction<@NotNull Element>(biggestElement, biggestFraction));
            unorderedElements.remove(biggestElement);
        }
        return orderedElements;
    }

    //
    public static class ConstituentFraction<T extends @NotNull AtomicInterface> {
        private final T constituent;
        private final double fraction;

        //
        public ConstituentFraction(T constituent, double fraction) {
            this.constituent = constituent;
            this.fraction = fraction;
        }

        //
        public final T getConstituent() {
            return constituent;
        }

        //
        public final double getFraction() {
            return fraction;
        }
    }
}
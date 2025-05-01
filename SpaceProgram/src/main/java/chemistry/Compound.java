package chemistry;

import java.util.*;

import dimensions.mass.MassUnit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;

//
public abstract class Compound<K extends @NotNull AtomicInterface, V extends @NotNull Number> implements SuperElementalInterface {
    private @Nullable String name;
    private final @NotNull Map<K, V> constituents;
    private final V constituentTotalMolarWeight;
    private final @NotNull Mass atomicMass;

    //
    public Compound(@Nullable String name, @NotNull Map<K, V> constituents) {
        this.name = name;
        this.constituents = constituents;
        constituentTotalMolarWeight = calculateConstituentTotalMolarWeight();
        atomicMass = calculateAtomicMass();
    }

    //
    public abstract @NotNull String determineNewName();

    //
    private V calculateConstituentTotalMolarWeight() {
        V totalMolarWeight = newValue();
        for (K constituent : constituents.keySet()) {
            totalMolarWeight = addToValue(totalMolarWeight, constituents.get(constituent));
        }
        return totalMolarWeight;
    }

    //for operations with constituents' values
    public abstract V newValue();

    //for operations with constituents' values
    public abstract V addToValue(V v, V d);

    //
    private @NotNull Mass calculateAtomicMass() {
        @NotNull Mass atomicMass = new Mass(0, MassUnit.G);
        for (K constituent : constituents.keySet()) {
            atomicMass.sum(constituent.getAtomicMass().getMultiplied((double) constituents.get(constituent)));
        }
        return atomicMass;
    }

    //
    @Override
    public final @NotNull String getName() {
        if (name == null) { //on-demand creates a new name
            name = determineNewName();
        }
        return name;
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

    //returns 0, if element not found
    @Override
    public final double getElementMolarFraction(@NotNull Element element) {
        double elementMolarFraction = 0;
        for (K constituent : constituents.keySet()) {
            double elementMolarFractionWithinConstituent;
            if (constituent instanceof @NotNull Element) {
                elementMolarFractionWithinConstituent = 1;
            } else if (constituent instanceof @NotNull Compound<?, ?> constituentCompound) {
                elementMolarFractionWithinConstituent = constituentCompound.getElementMolarFraction(element);
            } else {
                continue;
            }
            double constituentMolarFraction = (double) constituents.get(constituent) / (double) getConstituentTotalMolarWeight();
            elementMolarFraction += elementMolarFractionWithinConstituent * constituentMolarFraction;
        }
        return elementMolarFraction;
    }

    //returns 0, if element not found
    @Override
    public final double getElementMassFraction(@NotNull Element element) {
        double elementMolarFraction = getElementMolarFraction(element);
        @NotNull Mass elementFractionMolarMass = element.getAtomicMass().getMultiplied(elementMolarFraction);
        return elementFractionMolarMass.getInBase() / getAtomicMass().getInBase();
    }

    //
    @Override
    public @NotNull Set<@NotNull Element> getAllElements() {
        @NotNull Set<@NotNull Element> elements = new HashSet<>();
        for (K constituent : getConstituents().keySet()) {
            if (constituent instanceof @NotNull Element element) {
                elements.add(element);
            } else if (constituent instanceof @NotNull SuperElementalInterface superElemental) {
                elements.addAll(superElemental.getAllElements());
            } else {
                //unrecognized constituent
            }
        }
        return elements;
    }

    //
    @Override
    public final @NotNull List<@NotNull ElementFraction> getElementsByMolarFraction() {
        @NotNull List<@NotNull ElementFraction> orderedElements = new ArrayList<>();

        //TODO: finish this

        //gets all elements unordered
        @NotNull Set<@NotNull Element> unorderedElements = getAllElements();

        //sorts the unsorted elements
        while (!unorderedElements.isEmpty()) {
            double biggestFraction = 0;
            @Nullable Element biggestElement = null;
            for (@NotNull Element element : unorderedElements) {
                double fraction = getElementMolarFraction(element);
                if (fraction > biggestFraction) {
                    biggestFraction = fraction;
                    biggestElement = element;
                }
            }
            if (biggestElement == null) {
                //TODO: better error-handling
                break;
            }
            orderedElements.add(new ElementFraction(biggestElement, biggestFraction));
            unorderedElements.remove(biggestElement);
        }
        return orderedElements;
    }

    //
    @Override
    public final @NotNull List<@NotNull ElementFraction> getElementsByMassFraction() {
        @NotNull List<@NotNull ElementFraction> orderedElements = new ArrayList<>();

        //TODO: finish this

        //gets all elements unordered
        @NotNull Set<@NotNull Element> unorderedElements = getAllElements();

        //sorts the unsorted elements
        while (!unorderedElements.isEmpty()) {
            double biggestFraction = 0;
            @Nullable Element biggestElement = null;
            for (@NotNull Element element : unorderedElements) {
                double fraction = getElementMassFraction(element);
                if (fraction > biggestFraction) {
                    biggestFraction = fraction;
                    biggestElement = element;
                }
            }
            if (biggestElement == null) {
                //TODO: better error-handling
                break;
            }
            orderedElements.add(new ElementFraction(biggestElement, biggestFraction));
            unorderedElements.remove(biggestElement);
        }
        return orderedElements;
    }
}
package markets2.resources;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

//
public interface ResourceCollection {
    //a set of all resource amounts
    @NotNull @Unmodifiable Set<@NotNull ResourceAmount<
            ? extends @NotNull ResourceInterface, ? extends @NotNull Number>> getAll();

    //
    default @NotNull @Unmodifiable List<@NotNull ResourceAmount<
            ? extends @NotNull ResourceInterface, ? extends @NotNull Number>> getAll_byMass() {
        return Collections.unmodifiableList(new ArrayList<>() {
            {
                @NotNull Set<@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>>
                        unsorted = new HashSet<>(getAll());
                while (!unsorted.isEmpty()) {
                    @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                            highest = getHighestUnsorted(unsorted);
                    if (highest == null) {
                        //some trouble happened while sorting
                        //TODO: better error handling
                        break;
                    } else {
                        add(highest);
                        unsorted.remove(highest);
                    }
                }
            }

            private @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> getHighestUnsorted(
                    @NotNull Set<@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>> unsorted) {
                @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                        highest = null;
                for (@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                        amount : unsorted) {
                    double mass = amount.getMass();
                    if (mass > 0) {
                        if (highest == null || mass > highest.getMass()) {
                            highest = amount;
                        }
                    }
                }
                return highest;
            }
        });
    }

    //
    default @NotNull @Unmodifiable List<@NotNull ResourceAmount<
            ? extends @NotNull ResourceInterface, ? extends @NotNull Number>> getAll_byVolume() {
        return Collections.unmodifiableList(new ArrayList<>() {
            {
                @NotNull Set<@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>>
                        unsorted = new HashSet<>(getAll());
                while (!unsorted.isEmpty()) {
                    @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                            highest = getHighestUnsorted(unsorted);
                    if (highest == null) {
                        //some trouble happened while sorting
                        //TODO: better error handling
                        break;
                    } else {
                        add(highest);
                        unsorted.remove(highest);
                    }
                }
            }

            private @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> getHighestUnsorted(
                    @NotNull Set<@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>> unsorted) {
                @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                        highest = null;
                for (@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                        amount : unsorted) {
                    double volume = amount.getVolume();
                    if (volume > 0) {
                        if (highest == null || volume > highest.getVolume()) {
                            highest = amount;
                        }
                    }
                }
                return highest;
            }
        });
    }

    //
    default double getTotalMass() {
        double sum = 0;
        for (@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                amount : getAll()) {
            sum += amount.getMass();
        }
        return sum;
    }

    //
    default double getTotalVolume() {
        double sum = 0;
        for (@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                amount : getAll()) {
            sum += amount.getVolume();
        }
        return sum;
    }

    //
    default @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> get(
            @NotNull ResourceInterface resource) {
        for (@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> amount : getAll()) {
            if (amount.getResource() == resource) {
                return amount;
            }
        }
        return null;
    }

    //directly puts into the set; must check if already contained
    void put(@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> amount);

    //removes, if contained; otherwise does nothing
    void remove(@NotNull ResourceInterface resource);
}
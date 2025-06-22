package markets2.resources;

import java.util.Collections;
import java.util.Set;
import java.util.HashSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

//
public class ResourceSet implements ResourceCollection {
    private final @NotNull Set<@NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>>
            resourceAmounts = new HashSet<>();
    //
    public ResourceSet() {}

    //
    @Override
    public final @NotNull @Unmodifiable Set<@NotNull ResourceAmount<
            ? extends @NotNull ResourceInterface, ? extends @NotNull Number>> getAll() {
        return Collections.unmodifiableSet(resourceAmounts);
    }

    //directly puts into the set; must check if already contained
    @Override
    public final void put(
            @NotNull ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number> amount) {
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                current = get(amount.getResource());
        if (current == null) {
            resourceAmounts.add(amount);
        } else {
            //TODO: finish this
            throw new RuntimeException("Resource amount already contained...");
        }
    }

    //removes, if contained; otherwise does nothing
    @Override
    public final void remove(@NotNull ResourceInterface resource) {
        @Nullable ResourceAmount<? extends @NotNull ResourceInterface, ? extends @NotNull Number>
                amount = get(resource);
        if (amount != null) {
            resourceAmounts.remove(amount);
        }
    }
}
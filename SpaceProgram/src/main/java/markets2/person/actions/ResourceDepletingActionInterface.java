package markets2.person.actions;

import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ResourceInterface;
import markets2.person.Person;

//
public interface ResourceDepletingActionInterface<K extends @NotNull ResourceInterface, V extends @NotNull Number> {
    //unadjusted base rate
    @NotNull Map<K, V> getBaseIngredients();

    //all factors taken into account
    default @NotNull Map<K, V> getAdjustedIngredients(@NotNull Person person) {
        return new HashMap<>() {{
            @NotNull Map<K, V> baseIngredients = getBaseIngredients();
            for (K ingredient : baseIngredients.keySet()) {
                put(ingredient, adjustIngredient(baseIngredients.get(ingredient), person));
            }
        }};
    }

    //
    V adjustIngredient(V ingredient, @NotNull Person person);
}
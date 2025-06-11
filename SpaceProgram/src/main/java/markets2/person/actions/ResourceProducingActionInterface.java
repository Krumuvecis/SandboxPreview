package markets2.person.actions;

import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ResourceInterface;
import markets2.person.Person;

//
public interface ResourceProducingActionInterface<K extends @NotNull ResourceInterface, V extends @NotNull Number> {
    //unadjusted base rate
    @NotNull Map<K, V> getBaseProducts();

    //all factors taken into account
    default @NotNull Map<K, V> getAdjustedProducts(@NotNull Person person) {
        return new HashMap<>() {{
            @NotNull Map<K, V> baseProducts = getBaseProducts();
            for (K product : baseProducts.keySet()) {
                put(product, adjustProduct(baseProducts.get(product), person));
            }
        }};
    }

    //
    V adjustProduct(V product, @NotNull Person person);
}
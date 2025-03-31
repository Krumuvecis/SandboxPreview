package nutrition.nutrients;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;

//
public interface MicroNutrientContainer<T extends @NotNull MicroNutrientContainer<T>> extends NutrientContainerInterface<T> {
    //
    @NotNull Map<@NotNull MicroNutrient, @Nullable Mass> getMicroNutrients();

    //
    default @Nullable Mass getMicroNutrient(@NotNull MicroNutrient microNutrient) {
        return getMicroNutrients().get(microNutrient);
    }
}
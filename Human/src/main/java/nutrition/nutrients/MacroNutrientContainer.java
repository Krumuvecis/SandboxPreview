package nutrition.nutrients;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;

//
public interface MacroNutrientContainer<T extends @NotNull MacroNutrientContainer<T>> extends NutrientContainerInterface<T> {
    //
    @NotNull Map<@NotNull MacroNutrient, @Nullable Mass> getMacroNutrients();

    //
    default @Nullable Mass getMacroNutrient(@NotNull MacroNutrient macroNutrient) {
        return getMacroNutrients().get(macroNutrient);
    }
}
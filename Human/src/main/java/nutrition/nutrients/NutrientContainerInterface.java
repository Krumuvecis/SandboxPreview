package nutrition.nutrients;

import org.jetbrains.annotations.NotNull;

import operands.Operable;

//for arithmetic purposes
public interface NutrientContainerInterface<T extends @NotNull NutrientContainerInterface<T>> extends Operable<T> {}
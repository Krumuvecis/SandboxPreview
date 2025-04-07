package nutrition.food.foodTypes;

import org.jetbrains.annotations.NotNull;

import nutrition.food.FoodInterface;

//
abstract class AbstractFood implements FoodInterface {
    private final @NotNull String name;

    //
    AbstractFood(@NotNull String name) {
        this.name = name;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }
}
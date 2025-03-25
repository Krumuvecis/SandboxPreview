package food;

import org.jetbrains.annotations.NotNull;

//
@SuppressWarnings("ClassCanBeRecord")
public class Food {
    private final @NotNull String name;
    private final @NotNull NutritionalValue nutritionalValue;

    //
    public Food(@NotNull String name, @NotNull NutritionalValue nutritionalValue) {
        this.name = name;
        this.nutritionalValue = nutritionalValue;
    }

    //
    public @NotNull String getName() {
        return name;
    }

    //
    public @NotNull NutritionalValue getNutritionalValue() {
        return nutritionalValue;
    }
}
package nutrition.nutrients;

import org.jetbrains.annotations.NotNull;

//
public enum MicroNutrient implements NutrientInterface {
    //vitamins
    VITAMIN_A("vitamin A"),
    VITAMIN_B1("vitamin B1"),
    VITAMIN_B2("vitamin B2"),
    VITAMIN_B3("vitamin B3"),
    VITAMIN_B5("vitamin B5"),
    VITAMIN_B6("vitamin B6"),
    VITAMIN_B7("vitamin B7"),
    VITAMIN_B9("vitamin B9"),
    VITAMIN_B12("vitamin B12"),
    VITAMIN_C("vitamin C"),
    VITAMIN_D("vitamin D"),
    VITAMIN_E("vitamin E"),
    VITAMIN_K("vitamin K"),

    //minerals
    //TODO: improve this
    MINERAL_X("mineral X");

    private final @NotNull String name;

    //
    MicroNutrient(@NotNull String name) {
        this.name = name;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }
}
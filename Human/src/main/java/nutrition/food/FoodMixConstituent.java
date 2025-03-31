package nutrition.food;

import org.jetbrains.annotations.NotNull;

//
public class FoodMixConstituent {
    private final @NotNull FoodMixConstituentTemplate template;
    private final @NotNull FoodMixInterface foodMix;

    //
    FoodMixConstituent(@NotNull FoodMixConstituentTemplate template, @NotNull FoodMixInterface foodMix) {
        this.template = template;
        this.foodMix = foodMix;
    }

    //
    public @NotNull FoodMixConstituentTemplate getTemplate() {
        return template;
    }

    //
    /*public @NotNull Mass getMass() {
        return foodMix.
    }*/
}
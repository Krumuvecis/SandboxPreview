package nutrition.food;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;

//
public class FoodMixConstituentTemplate {
    private final @NotNull SimpleFood food;

    //
    FoodMixConstituentTemplate(@NotNull SimpleFood food) {
        this.food = food;
    }

    //
    public final @NotNull SimpleFood getFood() {
        return food;
    }

    //
    public static final class FoodMixConstituentTemplate_limitedByRatio extends FoodMixConstituentTemplate {
        private final double maximumRatio;

        //
        public FoodMixConstituentTemplate_limitedByRatio(@NotNull SimpleFood food, double maximumRatio) {
            super(food);
            this.maximumRatio = maximumRatio;
        }

        //
        public double getMaximumRatio() {
            return maximumRatio;
        }
    }

    //
    public static final class FoodMixConstituentTemplate_limitedByMass extends FoodMixConstituentTemplate {
        private final @NotNull Mass maximumMass;

        //
        public FoodMixConstituentTemplate_limitedByMass(@NotNull SimpleFood food, @NotNull Mass maximumMass) {
            super(food);
            this.maximumMass = maximumMass;
        }

        //
        public @NotNull Mass getMaximumMass() {
            return maximumMass;
        }
    }
}
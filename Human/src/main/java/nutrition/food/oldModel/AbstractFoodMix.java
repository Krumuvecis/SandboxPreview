package nutrition.food.oldModel;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.NutrientInterface;
import nutrition.digestion.NutrientSufficiencyInterface;
import nutrition.digestion.DigestiveHumanInterface;
import static nutrition.food.oldModel.FoodMixConstituentTemplate.*;

//
public class AbstractFoodMix implements FoodMixInterface {
    private final @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> foodWeights; //type-weight

    //
    AbstractFoodMix() {
        foodWeights = new HashMap<>();
    }

    //
    @Override
    public final @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> getFoodWeights() {
        return foodWeights;
    }

    //
    @Override
    public void addConstituent(@NotNull FoodMixConstituent constituent, int weight) {
        if (foodWeights.containsKey(constituent)) {
            foodWeights.put(constituent, foodWeights.get(constituent) + weight);
        } else {
            foodWeights.put(constituent, weight);
        }
    }

    //
    public static class ManualFoodMix extends AbstractFoodMix {
        //
        public ManualFoodMix(@NotNull Map<@NotNull FoodMixConstituentTemplate, @NotNull Integer> foodWeights) {
            super();
            for (@NotNull FoodMixConstituentTemplate constituentTemplate : foodWeights.keySet()) {
                addConstituent(
                        new FoodMixConstituent(constituentTemplate, this),
                        foodWeights.get(constituentTemplate));
            }
        }
    }

    /*public static class AutoFoodMix extends AbstractFoodMix {
        private final @NotNull DigestiveHumanInterface human;

        //
        public AutoFoodMix(@NotNull DigestiveHumanInterface human,
                           @NotNull List<@NotNull FoodMixConstituentTemplate> constituents) {
            super();
            this.human = human;
            @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> foodWeights = getFoodWeights();
            for (@NotNull FoodMixConstituentTemplate constituentTemplate : constituents) {
                foodWeights.put(new FoodMixConstituent(constituentTemplate, this), 1);
            }
            adjustWeights();
        }

        private void adjustWeights() {
            @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> foodWeights = getFoodWeights();
            int
                    iteration = 0,
                    maxIterations = 10000;
            while (true) {
                iteration ++;
                if (iteration > maxIterations) {
                    break;
                }
                @NotNull NutrientSufficiencyInterface.SufficiencyAnalysis
                        sufficiencyAnalysis = human.getDigestiveSystem().getNutrientSufficiency(getNutritionalValue());
                @Nullable Mass minimumMass = sufficiencyAnalysis.getMinimumMass();
                @NotNull NutrientInterface leastSufficientNutrient = sufficiencyAnalysis.getLeastSufficientNutrient();
                @NotNull FoodMixConstituent
                        mostEfficientConstituent = getNonNullMostEfficientConstituent(leastSufficientNutrient, minimumMass);
                foodWeights.put(mostEfficientConstituent, foodWeights.get(mostEfficientConstituent) + 1);
            }
        }

        private @NotNull FoodMixConstituent getNonNullMostEfficientConstituent(@NotNull NutrientInterface nutrient,
                                                                               @NotNull Mass totalMass) {
            @Nullable FoodMixConstituent mostEfficientFood = getMostEfficientConstituent(nutrient, totalMass);
            if (mostEfficientFood == null) {
                throw new RuntimeException("Unable to find the most efficient food for nutrient: " + nutrient.getName());
            } else {
                return mostEfficientFood;
            }
        }

        private @Nullable FoodMixConstituent getMostEfficientConstituent(@NotNull NutrientInterface nutrient,
                                                                         @NotNull Mass totalMass) {
            double highestNutrientIBase = 0;
            @Nullable FoodMixConstituent mostEfficientFood = null;
            for (@NotNull FoodMixConstituent constituent : getFoodWeights().keySet()) { //check all constituents
                @NotNull SimpleFood food = constituent.getTemplate().getFood();

                //check if permitted
                boolean foodPermitted = true;
                if (constituent.getTemplate() instanceof FoodMixConstituentTemplate_limitedByRatio limitedConstituent) {
                    double
                            ratio = getFoodRatios().get(constituent),
                            maxRatio = limitedConstituent.getMaximumRatio();
                    foodPermitted = ratio < maxRatio;
                }
                if (constituent.getTemplate() instanceof FoodMixConstituentTemplate_limitedByMass limitedConstituent) {
                    double
                            ratio = getFoodRatios().get(constituent),
                            massInBase = ratio * totalMass.getInBase();
                    foodPermitted = massInBase < limitedConstituent.getMaximumMass().getInBase();
                }

                //identify
                if (foodPermitted) {
                    @Nullable Mass nutrientMass = food.getNutritionalValue().getAnyNutrient(nutrient);
                    if (nutrientMass != null) {
                        double nutrientInBase = nutrientMass.getInBase();
                        if (nutrientInBase > highestNutrientIBase) {
                            highestNutrientIBase = nutrientInBase;
                            mostEfficientFood = constituent;
                        }
                    }

                }
            }
            return mostEfficientFood;
        }
    }*/
}
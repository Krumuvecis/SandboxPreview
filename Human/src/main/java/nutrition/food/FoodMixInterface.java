package nutrition.food;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import dimensions.mass.Mass;
import nutrition.nutrients.MacroNutrient;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;

//
public interface FoodMixInterface {
    //
    @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> getFoodWeights();

    //
    void addConstituent(@NotNull FoodMixConstituent constituent, int weight);

    private int getTotalWeight() {
        @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> foodWeights = getFoodWeights();
        int sum = 0;
        for (@NotNull FoodMixConstituent constituent : foodWeights.keySet()) {
            sum += foodWeights.get(constituent);
        }
        return sum;
    }

    //type-ratio
    default @NotNull Map<@NotNull FoodMixConstituent, @NotNull Double> getFoodRatios() {
        @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> foodWeights = getFoodWeights();
        @NotNull Map<@NotNull FoodMixConstituent, @NotNull Double> foodRatios = new HashMap<>();
        int totalWeight = getTotalWeight();
        if (totalWeight > 0) {
            for (@NotNull FoodMixConstituent constituent : foodWeights.keySet()) {
                double ratio = (double) foodWeights.get(constituent) / totalWeight;
                foodRatios.put(constituent, ratio);
            }
        }
        return foodRatios;
    }

    //
    default @NotNull NutritionalValue getNutritionalValue() {
        @NotNull Map<@NotNull FoodMixConstituent, @NotNull Double> foodRatios = getFoodRatios();
        return new NutritionalValue(
                new EnumMap<>(MacroNutrient.class) {{
                    for (@NotNull MacroNutrient nutrient : MacroNutrient.values()) {
                        @NotNull Mass sum = new Mass(0);
                        for (@NotNull FoodMixConstituent constituent : foodRatios.keySet()) {
                            @Nullable Mass nutrientMass = constituent.getTemplate().getFood().getNutritionalValue().getMacroNutrient(nutrient);
                            if (nutrientMass != null) {
                                sum.sum(nutrientMass.getMultiplied(foodRatios.get(constituent)));
                            }
                        }
                        put(nutrient, sum);
                    }
                }}, new EnumMap<>(MicroNutrient.class) {{
                    for (@NotNull MicroNutrient nutrient : MicroNutrient.values()) {
                        @NotNull Mass sum = new Mass(0);
                        for (@NotNull FoodMixConstituent constituent : foodRatios.keySet()) {
                            @Nullable Mass nutrientMass = constituent.getTemplate().getFood().getNutritionalValue().getMicroNutrient(nutrient);
                            if (nutrientMass != null) {
                                sum.sum(nutrientMass.getMultiplied(foodRatios.get(constituent)));
                            }
                        }
                        put(nutrient, sum);
                    }
                }}
        );
    }

    //
    default @NotNull List<FoodMixConstituent> getSortedConstituents() {
        @NotNull List<FoodMixConstituent> sortedConstituents = new ArrayList<>();

        //TODO: sort constituents here
        //ratio & mass

        return sortedConstituents;
    }
}
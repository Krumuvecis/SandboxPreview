package humanRequirementTests.nutritionTests;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.EnumMap;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static consoleUtils.SimplePrinting.printLine;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import food.NutrientEnum;
import food.NutritionalValue;
import food.Food;
import static food.ParticularFoods.*;
import humanRequirements.nutritionRequirements.NutritionRequirements;

import static humanRequirementTests.nutritionTests.NutritionTestConstants.printIndentedLine;
import static humanRequirementTests.nutritionTests.NutritionTestConstants.printNutritionalValue;

//
public class FoodAnalysisTest {
    private static final @NotNull NutritionRequirements
            NUTRITION_REQUIREMENTS = new NutritionTestConstants.CustomNutritionRequirements();
    private static final @NotNull FoodMixInterface
            FOOD_MIX_MANUAL = new ManualFoodMix(new HashMap<>() {{
                put(new FoodMixConstituentTemplate(SAUSAGE), 4);
                put(new FoodMixConstituentTemplate(BREAD), 10);
            }}),
            FOOD_MIX_AUTO_1 = new AutoFoodMix(NUTRITION_REQUIREMENTS, new ArrayList<>() {{
                add(new FoodMixConstituentTemplate(SAUSAGE));
                add(new FoodMixConstituentTemplate(BREAD));
                add(new FoodMixConstituentTemplate(PEANUT_BUTTER));
            }}),
            FOOD_MIX_AUTO_2 = new AutoFoodMix(NUTRITION_REQUIREMENTS, new ArrayList<>() {{
                add(new FoodMixConstituentTemplate_limitedByMass(MEAT_PORK_CANNED, new Mass(150, MassUnit.G)));
                add(new FoodMixConstituentTemplate_limitedByMass(MEAT_TUNA_CANNED, new Mass(150, MassUnit.G)));
                add(new FoodMixConstituentTemplate_limitedByRatio(SAUSAGE, 0.2));
                add(new FoodMixConstituentTemplate_limitedByMass(SALO, new Mass(10, MassUnit.G)));
                add(new FoodMixConstituentTemplate_limitedByMass(PEANUT_BUTTER, new Mass(50, MassUnit.G)));
                add(new FoodMixConstituentTemplate_limitedByMass(BEANS_KIDNEY, new Mass(100, MassUnit.G)));
                add(new FoodMixConstituentTemplate_limitedByMass(CORN_CANNED, new Mass(200, MassUnit.G)));
                add(new FoodMixConstituentTemplate_limitedByRatio(BREAD, 0.4));
                add(new FoodMixConstituentTemplate_limitedByMass(CHOCOLATE, new Mass(30, MassUnit.G)));
                add(new FoodMixConstituentTemplate_limitedByMass(CONDENSED_MILK, new Mass(3, MassUnit.TABLESPOON)));
                add(new FoodMixConstituentTemplate_limitedByMass(SUGAR, new Mass(9, MassUnit.TEASPOON)));
            }});

    //
    public static void main(String[] args) {
        printLine("Food analysis test.");
        printLine("");
        printLine("Reference nutritional requirements:");
        NutritionTestConstants.printNutritionRequirements(NUTRITION_REQUIREMENTS, 1);
        printLine("");
        @NotNull NutrientAnalyzer nutrientAnalyzer = new NutrientAnalyzer(NUTRITION_REQUIREMENTS);

        analyzeSingleFood(nutrientAnalyzer, SAUSAGE);
        analyzeSingleFood(nutrientAnalyzer, BREAD);

        analyzeFoodMix(nutrientAnalyzer, FOOD_MIX_MANUAL);
        analyzeFoodMix(nutrientAnalyzer, FOOD_MIX_AUTO_1);
        analyzeFoodMix(nutrientAnalyzer, FOOD_MIX_AUTO_2);
    }

    @SuppressWarnings("SameParameterValue")
    private static void analyzeSingleFood(@NotNull NutrientAnalyzer nutrientAnalyzer, @NotNull Food food) {
        printLine("Analyzing single food: " + food.getName());
        analyzeNutrients(nutrientAnalyzer, food.getNutritionalValue(), 1);
        printLine("");
    }

    @SuppressWarnings("SameParameterValue")
    private static void analyzeFoodMix(@NotNull NutrientAnalyzer nutrientAnalyzer, @NotNull FoodMixInterface foodMix) {
        printLine("Analyzing food mix:");
        @NotNull Mass minimumMass = nutrientAnalyzer.getMinimumMass(foodMix.getNutritionalValue());

        printIndentedLine(1, "Constituents:");
        @NotNull Map<@NotNull FoodMixConstituent, @NotNull Double> foodRatios = foodMix.getFoodRatios();
        for (@NotNull FoodMixConstituent constituent : foodRatios.keySet()) {
            double ratio = foodRatios.get(constituent);
            @NotNull Mass mass = new Mass(ratio * minimumMass.getInBase());
            printIndentedLine(2, constituent.getTemplate().getFood().getName() +
                    " - " + getRoundedPercentageString(ratio, 1) +
                    " - " + mass.getValueAndShortUnit(MassUnit.G, 1));
        }
        analyzeNutrients(nutrientAnalyzer, foodMix.getNutritionalValue(), 1);
        printLine("");
    }

    //
    static class NutrientAnalyzer {
        private final @NotNull NutritionRequirements nutritionRequirements;

        //
        NutrientAnalyzer(@NotNull NutritionRequirements nutritionRequirements) {
            this.nutritionRequirements = nutritionRequirements;
        }

        //
        double getSufficiency(@NotNull NutritionalValue nutrients, @NotNull NutrientEnum nutrient) {
            return nutritionRequirements.compare(nutrients, nutrient);
        }

        //{overall, by protein, by fats, by carbs}
        @NotNull Mass getMinimumMass(@NotNull NutritionalValue nutrients) {
            double
                    minimumMassInBase_byProtein = 1 / getSufficiency(nutrients, NutrientEnum.PROTEIN),
                    minimumMassInBase_byFats = 1 / getSufficiency(nutrients, NutrientEnum.FAT),
                    minimumMassInBase_byCarbs = 1 / getSufficiency(nutrients, NutrientEnum.CARB),
                    minimumMassInBase = Math.max(
                            Math.max(minimumMassInBase_byProtein, minimumMassInBase_byFats),
                            minimumMassInBase_byCarbs);
            return new Mass(minimumMassInBase);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private static void analyzeNutrients(@NotNull NutrientAnalyzer nutrientAnalyzer,
                                         @NotNull NutritionalValue nutrients, int indent) {
        //nutritional value per 1kg
        printIndentedLine(indent, "Nutritional value (per 1 kg):");
        NutritionTestConstants.printNutritionalValue(nutrients, indent + 1, MassUnit.G, 0);

        //minimum total mass
        @NotNull Mass minimumMass = nutrientAnalyzer.getMinimumMass(nutrients);
        printIndentedLine(indent, "Minimum total mass to suffice for a day: " +
                minimumMass.getValueAndShortUnit(MassUnit.G, 0));

        //nutritional value per total mass
        printIndentedLine(indent, "Nutritional value (total mass):");
        @NotNull NutritionalValue totalNutritionalValue = new NutritionalValue(new EnumMap<>(NutrientEnum.class) {{
            double minimumMassInBase = minimumMass.getInBase();
            for (@NotNull NutrientEnum nutrient : NutrientEnum.values()) {
                put(nutrient, new Mass(nutrients.get(nutrient).getInBase() * minimumMassInBase));
            }
        }});
        printNutritionalValue(totalNutritionalValue, indent + 1, MassUnit.G, 0);

        //nutrient sufficiency per total mass
        printIndentedLine(indent, "Daily sufficiency (total mass):");
        double
                proteinSufficiency = nutrientAnalyzer.getSufficiency(totalNutritionalValue, NutrientEnum.PROTEIN),
                fatsSufficiency = nutrientAnalyzer.getSufficiency(totalNutritionalValue, NutrientEnum.FAT),
                carbsSufficiency = nutrientAnalyzer.getSufficiency(totalNutritionalValue, NutrientEnum.CARB);
        printIndentedLine(indent + 1, "Protein: " + getRoundedPercentageString(proteinSufficiency, 1));
        printIndentedLine(indent + 1, "Fats: " + getRoundedPercentageString(fatsSufficiency, 1));
        printIndentedLine(indent + 1, "Carbs: " + getRoundedPercentageString(carbsSufficiency, 1));

    }

    @SuppressWarnings("SameParameterValue")
    private static @NotNull String getRoundedPercentageString(double coefficient, int decimalPlaces) {
        return (((int) (coefficient * 100 * Math.pow(10, decimalPlaces))) / Math.pow(10, decimalPlaces)) + " %";
    }

    //
    static class FoodMixConstituentTemplate {
        private final @NotNull Food food;

        //
        FoodMixConstituentTemplate(@NotNull Food food) {
            this.food = food;
        }

        //
        public final @NotNull Food getFood() {
            return food;
        }
    }

    //
    static final class FoodMixConstituentTemplate_limitedByRatio extends FoodMixConstituentTemplate {
        private final double maximumRatio;

        //
        FoodMixConstituentTemplate_limitedByRatio(@NotNull Food food, double maximumRatio) {
            super(food);
            this.maximumRatio = maximumRatio;
        }

        //
        public double getMaximumRatio() {
            return maximumRatio;
        }
    }

    //
    static final class FoodMixConstituentTemplate_limitedByMass extends FoodMixConstituentTemplate {
        private final @NotNull Mass maximumMass;

        //
        FoodMixConstituentTemplate_limitedByMass(@NotNull Food food, @NotNull Mass maximumMass) {
            super(food);
            this.maximumMass = maximumMass;
        }

        //
        public @NotNull Mass getMaximumMass() {
            return maximumMass;
        }
    }

    //
    static class FoodMixConstituent {
        private final @NotNull FoodMixConstituentTemplate template;
        private final @NotNull FoodMixInterface foodMix;

        //
        FoodMixConstituent(@NotNull FoodMixConstituentTemplate template, @NotNull FoodMixInterface foodMix) {
            this.template = template;
            this.foodMix = foodMix;
        }

        public @NotNull FoodMixConstituentTemplate getTemplate() {
            return template;
        }
    }

    //
    interface FoodMixInterface {
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
            return new NutritionalValue(new EnumMap<>(NutrientEnum.class) {{
                for (@NotNull NutrientEnum nutrient : NutrientEnum.values()) {
                    double sum = 0;
                    for (@NotNull FoodMixConstituent constituent : foodRatios.keySet()) {
                        double ratio = foodRatios.get(constituent);
                        sum += ratio * constituent.getTemplate().getFood().getNutritionalValue().get(nutrient).getInBase();
                    }
                    put(nutrient, new Mass(sum));
                }
            }});
        }

        //
        default @NotNull List<FoodMixConstituent> getSortedConstituents() {
            @NotNull List<FoodMixConstituent> sortedConstituents = new ArrayList<>();

            //TODO: sort constituents here
            //ratio & mass

            return sortedConstituents;
        }
    }

    //
    static abstract class AbstractFoodMix implements FoodMixInterface {
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
    }

    //
    static class ManualFoodMix extends AbstractFoodMix {
        //
        ManualFoodMix(@NotNull Map<@NotNull FoodMixConstituentTemplate, @NotNull Integer> foodWeights) {
            super();
            for (@NotNull FoodMixConstituentTemplate constituentTemplate : foodWeights.keySet()) {
                addConstituent(
                        new FoodMixConstituent(constituentTemplate, this),
                        foodWeights.get(constituentTemplate));
            }
        }
    }

    static class AutoFoodMix extends AbstractFoodMix {
        private final @NotNull NutrientAnalyzer nutrientAnalyzer;

        //
        AutoFoodMix(@NotNull NutritionRequirements nutritionRequirements,
                    @NotNull List<@NotNull FoodMixConstituentTemplate> constituents) {
            super();
            @NotNull Map<@NotNull FoodMixConstituent, @NotNull Integer> foodWeights = getFoodWeights();
            for (@NotNull FoodMixConstituentTemplate constituentTemplate : constituents) {
                foodWeights.put(new FoodMixConstituent(constituentTemplate, this), 1);
            }
            nutrientAnalyzer = new NutrientAnalyzer(nutritionRequirements);
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
                @NotNull Mass minimumMass = nutrientAnalyzer.getMinimumMass(getNutritionalValue());
                @NotNull NutrientEnum leastSufficientNutrient = getLeastSufficientNutrient(getNutritionalValue());
                @NotNull FoodMixConstituent mostEfficientConstituent = getNonNullMostEfficientConstituent(leastSufficientNutrient, minimumMass);
                foodWeights.put(mostEfficientConstituent, foodWeights.get(mostEfficientConstituent) + 1);
            }
        }

        private @NotNull NutrientEnum getLeastSufficientNutrient(@NotNull NutritionalValue nutrients) {
            double minSufficiency = Double.MAX_VALUE;
            @Nullable NutrientEnum leastSufficientNutrient = null;
            for (@NotNull NutrientEnum nutrient : NutrientEnum.values()) {
                double sufficiency = nutrientAnalyzer.getSufficiency(nutrients, nutrient);
                if (sufficiency < minSufficiency) {
                    minSufficiency = sufficiency;
                    leastSufficientNutrient = nutrient;
                }
            }
            if (leastSufficientNutrient == null) {
                throw new RuntimeException("Unable to find the least sufficient nutrient.");
            } else {
                return leastSufficientNutrient;
            }
        }

        private @NotNull FoodMixConstituent getNonNullMostEfficientConstituent(@NotNull NutrientEnum nutrient,
                                                                               @NotNull Mass totalMass) {
            @Nullable FoodMixConstituent mostEfficientFood = getMostEfficientConstituent(nutrient, totalMass);
            if (mostEfficientFood == null) {
                throw new RuntimeException("Unable to find the most efficient food for nutrient: " + nutrient.getName());
            } else {
                return mostEfficientFood;
            }
        }

        private @Nullable FoodMixConstituent getMostEfficientConstituent(@NotNull NutrientEnum nutrient,
                                                                         @NotNull Mass totalMass) {
            double highestNutrientIBase = 0;
            @Nullable FoodMixConstituent mostEfficientFood = null;
            for (@NotNull FoodMixConstituent constituent : getFoodWeights().keySet()) { //check all constituents
                @NotNull Food food = constituent.getTemplate().getFood();

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
                    double nutrientInBase = food.getNutritionalValue().get(nutrient).getInBase();
                    if (nutrientInBase > highestNutrientIBase) {
                        highestNutrientIBase = nutrientInBase;
                        mostEfficientFood = constituent;
                    }
                }
            }
            return mostEfficientFood;
        }
    }
}
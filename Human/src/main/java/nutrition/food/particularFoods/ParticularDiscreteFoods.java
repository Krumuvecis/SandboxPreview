package nutrition.food.particularFoods;

import java.util.EnumMap;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;
import nutrition.food.foodTypes.SingleDiscreteFood;
import nutrition.food.foodTypes.CompoundDiscreteFood;
import static nutrition.food.particularFoods.ParticularLooseFoods.*;

//TODO: fix unit masses - currently 1kg everywhere
public class ParticularDiscreteFoods {
    public static final @NotNull SingleDiscreteFood
            BEANS_KIDNEY = new SingleDiscreteFood("Canned beans (kidney, boiled)",
                    new Mass(1), new NutritionalValue(
                            8.7, 0.50, 22.8,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, null);

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.16, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.06, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(0.58, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, new Mass(0.22, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.12, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, null);
                                put(MicroNutrient.VITAMIN_B9, new Mass(130, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, null);

                                put(MicroNutrient.VITAMIN_C, new Mass(1.2, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, null);
                                put(MicroNutrient.VITAMIN_E, new Mass(0.03, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(8.4, MassUnit.UG));
                            }})),
            EGG_BOILED = new SingleDiscreteFood("Egg (chicken, boiled)",
                    new Mass(55, MassUnit.G), new NutritionalValue(
                            12.58, 10.61, 1.12,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(149, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.066, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.513, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(0.064, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, new Mass(1.398, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.121, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, new Mass(21, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B9, new Mass(44, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, new Mass(1.11, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, new Mass(2.2, MassUnit.UG)); //25 ng = 1 IU
                                put(MicroNutrient.VITAMIN_E, new Mass(1.03, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(0.3, MassUnit.UG));
                            }})),
            MEAT_PORK_CANNED = new SingleDiscreteFood("Canned meat",
                    new Mass(1), new NutritionalValue(
                            13.9, 29.2, 0.5)),
            MEAT_TUNA_CANNED = new SingleDiscreteFood("Canned tuna (in oil)",
                    new Mass(130 / 4, MassUnit.G), new NutritionalValue(
                            29.13, 8.21, 0,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(23, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.038, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.120, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(13.3, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, new Mass(12.4, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.110, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, null);
                                put(MicroNutrient.VITAMIN_B9, new Mass(5.00, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, new Mass(2.20, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, new Mass(6.70, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_E, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(44.0, MassUnit.UG));
                            }})),
            MEAT_SALMON_CANNED = new SingleDiscreteFood("Canned salmon",
                    new Mass(130 / 4, MassUnit.G), new NutritionalValue(
                            29.13, 8.21, 0,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(23, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.038, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.120, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(13.3, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, new Mass(12.4, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.110, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, null);
                                put(MicroNutrient.VITAMIN_B9, new Mass(5.00, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, new Mass(2.20, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, new Mass(6.70, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_E, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(44.0, MassUnit.UG));
                            }})),
            SAUSAGE = new SingleDiscreteFood("Sausage kabanos",
                    new Mass(1), new NutritionalValue(
                            12, 20, 2)),
            PEANUT_BUTTER = new SingleDiscreteFood("Peanut butter",
                    new Mass(1), new NutritionalValue(
                            25, 50, 20,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.138, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.191, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(13.3, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, new Mass(1.1, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.44, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, null);
                                put(MicroNutrient.VITAMIN_B9, new Mass(86, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, null);

                                put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, null);
                                put(MicroNutrient.VITAMIN_E, new Mass(9.1, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(0.3, MassUnit.UG));
                            }})),
            PATE_CHICKEN = new SingleDiscreteFood("Chicken pate",
                    new Mass(1), new NutritionalValue(
                            13, 13, 6.6,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(217, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.052, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(1.401, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(7.517, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, new Mass(2.620, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.260, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, null);
                                put(MicroNutrient.VITAMIN_B9, new Mass(321, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_C, new Mass(10, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_E, new Mass(0.98, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(0, MassUnit.UG));
                            }})),
            CORN_CANNED = new SingleDiscreteFood("Canned corn",
                    new Mass(1), new NutritionalValue(
                            2.3, 1.2, 14,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(2, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.039, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.089, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(1.005, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, new Mass(0.209, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.037, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, new Mass(0.05, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B9, new Mass(39, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_C, new Mass(1.8, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_E, new Mass(0.09, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(0, MassUnit.UG));
                            }})),
            BREAD_WHITE = new SingleDiscreteFood("Bread (white)",
                    new Mass(1), new NutritionalValue(
                            9.43, 3.59, 49.2,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.507, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.240, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(4.760, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, null);
                                put(MicroNutrient.VITAMIN_B6, new Mass(0.092, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, null);
                                put(MicroNutrient.VITAMIN_B9, new Mass(171, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_E, new Mass(0.22, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(0.2, MassUnit.UG));
                            }})),
            CHOCOLATE = new SingleDiscreteFood("Chocolate (dark)",
                    new Mass(1), new NutritionalValue(
                            4.9, 31, 61)),
            CONDENSED_MILK = new SingleDiscreteFood("Condensed milk",
                    new Mass(1), new NutritionalValue(
                            8, 9, 54)),
            ENERGY_DRINK_MONSTER = new SingleDiscreteFood("Energy drink (Monster)",
                    new Mass(500, MassUnit.G), new NutritionalValue(
                            0.5, 0, 11,
                            new EnumMap<>(MicroNutrient.class) {{
                                put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_B1, new Mass(0.04, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B2, new Mass(0.810, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B3, new Mass(10.16, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B5, null);
                                put(MicroNutrient.VITAMIN_B6, new Mass(1.01, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_B7, null);
                                put(MicroNutrient.VITAMIN_B9, new Mass(0, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_B12, new Mass(2.57, MassUnit.UG));

                                put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                                put(MicroNutrient.VITAMIN_E, new Mass(0, MassUnit.MG));
                                put(MicroNutrient.VITAMIN_K, new Mass(0, MassUnit.UG));
                            }}));

    public static final @NotNull CompoundDiscreteFood
            DISCRETE_SALAD_1 = new CompoundDiscreteFood("Test discrete salad 1",
                    new HashMap<>() {{
                        put(LOOSE_SALAD_1, new Mass(50, MassUnit.G));
                        put(OIL_OLIVE, new Mass(2, MassUnit.TEASPOON));
                        put(CARROTS, new Mass(50.0, MassUnit.G));
                    }},
                    new HashMap<>() {{
                        //add discrete ingredients here
                    }}),
            DISCRETE_SALAD_2 = new CompoundDiscreteFood("Test discrete salad 2",
                    new HashMap<>() {{
                        put(LOOSE_SALAD_2, new Mass(100, MassUnit.G));
                        put(OIL_OLIVE, new Mass(2, MassUnit.TEASPOON));
                    }},
                    new HashMap<>() {{
                        //add discrete ingredients here
                    }}),
            DISCRETE_SALAD_3 = new CompoundDiscreteFood("Test discrete salad 3",
                    new HashMap<>() {{
                        put(OATS, new Mass(100, MassUnit.G));
                        put(SUGAR, new Mass(1, MassUnit.TABLESPOON));
                        put(OIL_SUNFLOWER, new Mass(1, MassUnit.TABLESPOON));
                    }},
                    new HashMap<>() {{
                        //add discrete ingredients here
                    }}),
            DISCRETE_SALAD_4 = new CompoundDiscreteFood("Test discrete salad 4",
                    new HashMap<>() {{
                        //put(POTATO_BOILED, new Mass(220, MassUnit.G));
                        //put(PEANUTS_ROASTED_UNSALTED, new Mass(40, MassUnit.G));
                        //put(ALMONDS, new Mass(5, MassUnit.G));
                        //put(HONEY, new Mass(3, MassUnit.TEASPOON));
                        put(FISH_OIL_COD, new Mass(1/4, MassUnit.TEASPOON));
                    }},
                    new HashMap<>() {{
                        //put(DISCRETE_SALAD_1, 1);
                        //put(DISCRETE_SALAD_2, 1);
                        //put(DISCRETE_SALAD_3, 1);
                        put(EGG_BOILED, 2);
                        //put(ENERGY_DRINK_MONSTER, 1);
                        put(MEAT_TUNA_CANNED, 1);
                        put(MEAT_SALMON_CANNED, 1);
                    }});
}
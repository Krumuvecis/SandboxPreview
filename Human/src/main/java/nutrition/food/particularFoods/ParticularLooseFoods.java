package nutrition.food.particularFoods;

import java.util.EnumMap;
import java.util.HashMap;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;
import nutrition.food.foodTypes.SingleLooseFood;
import nutrition.food.foodTypes.CompoundLooseFood;

//
public class ParticularLooseFoods {
    public static final @NotNull SingleLooseFood
            OATS = new SingleLooseFood("Oats", new NutritionalValue(
            13.15, 6.52, 67.7,
            new EnumMap<>(MicroNutrient.class) {{
                put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                put(MicroNutrient.VITAMIN_B1, new Mass(0.460, MassUnit.MG));
                put(MicroNutrient.VITAMIN_B2, new Mass(0.155, MassUnit.MG));
                put(MicroNutrient.VITAMIN_B3, new Mass(1.125, MassUnit.MG));
                put(MicroNutrient.VITAMIN_B5, new Mass(1.120, MassUnit.MG));
                put(MicroNutrient.VITAMIN_B6, new Mass(0.1, MassUnit.MG));
                put(MicroNutrient.VITAMIN_B7, null);
                put(MicroNutrient.VITAMIN_B9, new Mass(32, MassUnit.UG));
                put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                put(MicroNutrient.VITAMIN_E, new Mass(0.42, MassUnit.MG));
                put(MicroNutrient.VITAMIN_K, new Mass(2.0, MassUnit.UG));
            }})),
            CARROTS = new SingleLooseFood("Carrots", new NutritionalValue(
                    0.93, 0.24, 9.6,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(835, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.066, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.058, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0.983, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.273, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.138, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(19, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, null);

                        put(MicroNutrient.VITAMIN_C, new Mass(5.9, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, null);
                        put(MicroNutrient.VITAMIN_E, new Mass(0.66, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(13.2, MassUnit.UG));
                    }})),
            BROCCOLI = new SingleLooseFood("Broccoli", new NutritionalValue(
                    2.82, 0.37, 6.64,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(31, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.071, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.117, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0.639, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.573, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.175, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, new Mass(0.9, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B9, new Mass(63, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, null);

                        put(MicroNutrient.VITAMIN_C, new Mass(89.2, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, null);
                        put(MicroNutrient.VITAMIN_E, new Mass(0.78, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(101.6, MassUnit.UG));
                    }})),
            SPINACH = new SingleLooseFood("Spinach", new NutritionalValue(
                    2.9, 0.4, 3.6,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(469, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.078, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.189, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0.724, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.065, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.195, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(194, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(28.1, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(2.03, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(482.9, MassUnit.UG));
                    }})),
            TOMATO = new SingleLooseFood("Tomato", new NutritionalValue(
                    0.9, 0.2, 3.9,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(42, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.037, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.019, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0.594, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, null);
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.080, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(15, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(13.7, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(0.54, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(7.9, MassUnit.UG));
                    }})),
            CUCUMBER = new SingleLooseFood("Cucumber", new NutritionalValue(
                    0.65, 0.11, 3.63,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(5, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.027, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.033, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0.098, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.259, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.040, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(7, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(2.8, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(0.03, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(16.4, MassUnit.UG));
                    }})),
            ORANGE = new SingleLooseFood("Orange (navel)", new NutritionalValue(
                    0.9, 0.2, 13,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(12, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.068, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.051, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0.425, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.261, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.079, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(34, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(59.1, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(0.15, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(0, MassUnit.UG));
                    }})),
            POTATO_BOILED = new SingleLooseFood("Potato (boiled)", new NutritionalValue(
                    1.7, 0.1, 20,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.098, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.019, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(1.312, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.509, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.269, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(9, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(7.4, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(0.01, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(2.2, MassUnit.UG));
                    }})),
            SALO = new SingleLooseFood("Salo (pork)",
                    new NutritionalValue(7, 66.1, 0)),
            FISH_OIL_COD = new SingleLooseFood("Fish oil (cod liver)", new NutritionalValue(
                    0, 100, 0,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(30, MassUnit.MG));

                        put(MicroNutrient.VITAMIN_B1, null);
                        put(MicroNutrient.VITAMIN_B2, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(250, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, null);
                        put(MicroNutrient.VITAMIN_K, null);
                    }})),
            PEANUTS_ROASTED_UNSALTED = new SingleLooseFood("Peanuts (roasted, unsalted)", new NutritionalValue(
                    28, 53, 15,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.085, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.089, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(13.825, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, null);
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.461, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, new Mass(17.5, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B9, new Mass(120, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(0.8, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(6.91, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(0, MassUnit.UG));
                    }})),
            ALMONDS = new SingleLooseFood("Almonds", new NutritionalValue(
                    21, 50, 22,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0.205, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(1.138, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(3.618, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.471, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.137, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, new Mass(4.4, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B9, new Mass(44, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(25.63, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(0, MassUnit.UG));
                    }})),
            HONEY = new SingleLooseFood("Honey", new NutritionalValue(
                    0.3, 0, 82.4,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0.038, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0.121, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0.068, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0.024, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(2, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(0.5, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(0, MassUnit.UG));
                    }})),
            SUGAR = new SingleLooseFood("Sugar",
                    new NutritionalValue(0, 0, 100)),
            OIL_SUNFLOWER = new SingleLooseFood("Sunflower oil", new NutritionalValue(
                    0, 100, 0,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, null);
                        put(MicroNutrient.VITAMIN_B6, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(41.08, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(5.4, MassUnit.UG));
                    }})),
            OIL_CANOLA = new SingleLooseFood("Canola (rapeseed) oil", new NutritionalValue(
                    0, 100, 0,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B6, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(17.46, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(71.3, MassUnit.UG));
                    }})),
            OIL_OLIVE = new SingleLooseFood("Olive oil", new NutritionalValue(
                    0, 100, 0,
                    new EnumMap<>(MicroNutrient.class) {{
                        put(MicroNutrient.VITAMIN_A, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_B1, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B2, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B3, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B5, null);
                        put(MicroNutrient.VITAMIN_B6, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_B7, null);
                        put(MicroNutrient.VITAMIN_B9, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_B12, new Mass(0, MassUnit.UG));

                        put(MicroNutrient.VITAMIN_C, new Mass(0, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_D, new Mass(0, MassUnit.UG));
                        put(MicroNutrient.VITAMIN_E, new Mass(14.35, MassUnit.MG));
                        put(MicroNutrient.VITAMIN_K, new Mass(60.2, MassUnit.UG));
                    }}));

    public static final @NotNull CompoundLooseFood
            LOOSE_SALAD_1 = new CompoundLooseFood("Test loose salad 1", new HashMap<>() {{
                put(BROCCOLI, 9);
                put(SPINACH, 1);
            }}),
            LOOSE_SALAD_2 = new CompoundLooseFood("Test loose salad 2", new HashMap<>() {{
                put(TOMATO, 4);
                put(CUCUMBER, 1);
            }});
}
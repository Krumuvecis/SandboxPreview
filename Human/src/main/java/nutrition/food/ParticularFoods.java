package nutrition.food;

import java.util.EnumMap;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.nutrients.MicroNutrient;
import nutrition.nutrients.NutritionalValue;

//
public class ParticularFoods {
    public static final @NotNull SimpleFood
            OATS = new SimpleFood("Oats", new NutritionalValue(
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
            CARROTS = new SimpleFood("Carrots", new NutritionalValue(
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
            BEANS_KIDNEY = new SimpleFood("Kidney beans (boiled)", new NutritionalValue(
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
            BROCCOLI = new SimpleFood("Broccoli", new NutritionalValue(
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
            SPINACH = new SimpleFood("Spinach", new NutritionalValue(
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
            TOMATO = new SimpleFood("Tomato", new NutritionalValue(
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
            CUCUMBER = new SimpleFood("Cucumber", new NutritionalValue(
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
            ORANGE = new SimpleFood("Orange (navel)", new NutritionalValue(
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
            POTATO_BOILED = new SimpleFood("Potato (boiled)", new NutritionalValue(
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
            EGG_BOILED = new SimpleFood("Egg (chicken, boiled)", new NutritionalValue(
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
            MEAT_PORK_CANNED = new SimpleFood("Canned meat",
                    new NutritionalValue(13.9, 29.2, 0.5)),
            MEAT_TUNA_CANNED = new SimpleFood("Canned tuna (in oil)", new NutritionalValue(
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
            SAUSAGE = new SimpleFood("Sausage kabanos",
                    new NutritionalValue(12, 20, 2)),
            SALO = new SimpleFood("Salo (pork)",
                    new NutritionalValue(7, 66.1, 0)),
            FISH_OIL_COD = new SimpleFood("Fish oil (cod liver)", new NutritionalValue(
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
            ALMONDS = new SimpleFood("Almonds", new NutritionalValue(
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
            PEANUTS_ROASTED_UNSALTED = new SimpleFood("Peanuts (roasted, unsalted)", new NutritionalValue(
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
            PEANUT_BUTTER = new SimpleFood("Peanut butter", new NutritionalValue(
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
            PATE_CHICKEN = new SimpleFood("Chicken pate", new NutritionalValue(
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
            CORN_CANNED = new SimpleFood("Canned corn", new NutritionalValue(
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
            BREAD_WHITE = new SimpleFood("Bread (white)", new NutritionalValue(
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
            CHOCOLATE = new SimpleFood("Chocolate (dark)",
                    new NutritionalValue(4.9, 31, 61)),
            CONDENSED_MILK = new SimpleFood("Condensed milk",
                    new NutritionalValue(8, 9, 54)),
            HONEY = new SimpleFood("Honey", new NutritionalValue(
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
            SUGAR = new SimpleFood("Sugar",
                    new NutritionalValue(0, 0, 100)),
            ENERGY_DRINK_MONSTER = new SimpleFood("Energy drink (Monster)", new NutritionalValue(
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
}
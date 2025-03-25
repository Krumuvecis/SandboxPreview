package food;

import org.jetbrains.annotations.NotNull;

//
public class ParticularFoods {
    public static final @NotNull Food
            MEAT_PORK_CANNED = new Food("Canned meat",
                    new NutritionalValue(13.9, 29.2, 0.5)),
            MEAT_TUNA_CANNED = new Food("Canned tuna (in oil)",
                    new NutritionalValue(21.7, 3.6, 0)),
            SAUSAGE = new Food("Sausage kabanos",
                    new NutritionalValue(12, 20, 2)),
            SALO = new Food("Salo (pork)",
                    new NutritionalValue(7, 66.1, 0)),
            PEANUT_BUTTER = new Food("Peanut butter",
                    new NutritionalValue(25, 50, 20)),
            BEANS_KIDNEY = new Food("Kidney beans (boiled)",
                    new NutritionalValue(8.7, 0.5, 22.8)),
            CORN_CANNED = new Food("Canned corn",
                    new NutritionalValue(2.9, 0.8, 20)),
            BREAD = new Food("Bread",
                    new NutritionalValue(8, 3, 50)),
            CHOCOLATE = new Food("Chocolate (dark)",
                    new NutritionalValue(4.9, 31, 61)),
            CONDENSED_MILK = new Food("Condensed milk",
                    new NutritionalValue(8, 9, 54)),
            SUGAR = new Food("Sugar",
                    new NutritionalValue(0, 0, 100));
}
package nutritionTests.oldModel;

import java.util.ArrayList;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.MassUnit;
import dimensions.mass.Mass;
import nutrition.digestion.DigestiveHumanInterface;
import nutrition.food.oldModel.AbstractFoodMix;
import static nutrition.food.oldModel.FoodMixConstituentTemplate.*;

//
final class TestFoodMix_auto /*extends AbstractFoodMix.AutoFoodMix*/ {
    //
    TestFoodMix_auto(@NotNull DigestiveHumanInterface human) {
        /*super(human, new ArrayList<>() {{
            //breakfast
            add(new FoodMixConstituentTemplate_limitedByMass(OATS, new Mass(500.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(SUGAR, new Mass(1.0 * 3 / 7, MassUnit.TABLESPOON)));
            add(new FoodMixConstituentTemplate_limitedByMass(BREAD_WHITE, new Mass(300.0 * 3 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(PEANUT_BUTTER, new Mass(350.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(MEAT_TUNA_CANNED, new Mass(130.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(PATE_CHICKEN, new Mass(120.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(SALO, new Mass(10, MassUnit.G)));

            //salad
            add(new FoodMixConstituentTemplate_limitedByMass(CARROTS, new Mass(120.0 * 2 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(BROCCOLI, new Mass(400.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(SPINACH, new Mass(70.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(TOMATO, new Mass(120.0 * 2 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(CUCUMBER, new Mass(120.0 * 2 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(CORN_CANNED, new Mass(150.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(ORANGE, new Mass(150.0 * 2 / 7, MassUnit.G)));

            //main courses
            add(new FoodMixConstituentTemplate_limitedByRatio(POTATO_BOILED, 0.3));
            add(new FoodMixConstituentTemplate_limitedByRatio(BEANS_KIDNEY, 0.3));
            add(new FoodMixConstituentTemplate_limitedByMass(EGG_BOILED, new Mass(50.0 * 3, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(MEAT_PORK_CANNED, new Mass(150.0 * 2 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByRatio(SAUSAGE, 0.1));

            //additional
            add(new FoodMixConstituentTemplate_limitedByMass(FISH_OIL_COD, new Mass(1, MassUnit.TEASPOON)));
            add(new FoodMixConstituentTemplate_limitedByMass(ALMONDS, new Mass(300.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(PEANUTS_ROASTED_UNSALTED, new Mass(300.0 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(CHOCOLATE, new Mass(100.0 * 2 / 7, MassUnit.G)));
            add(new FoodMixConstituentTemplate_limitedByMass(CONDENSED_MILK, new Mass(2, MassUnit.TABLESPOON)));
            add(new FoodMixConstituentTemplate_limitedByMass(HONEY, new Mass(3, MassUnit.TEASPOON)));
            add(new FoodMixConstituentTemplate_limitedByMass(ENERGY_DRINK_MONSTER, new Mass(500.0, MassUnit.G)));
        }});*/
    }
}
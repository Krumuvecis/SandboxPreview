package markets2.person;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import markets2.resources.ParticularResources;
import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.market.MarketHistoryDataPoint;
import markets2.market.MultiMarket;
import markets2.person.actions.PersonAction;
import markets2.person.actions.BreedAction;
import markets2.person.actions.PersonActionTemplate;
import markets2.World;

//
class PersonActionAI {
    private final @NotNull World world; //reference
    private final @NotNull Person person; //reference

    //
    PersonActionAI(@NotNull World world, @NotNull Person person) {
        this.world = world;
        this.person = person;
    }

    //
    @Nullable PersonAction decideNewAction() {
        double foodAtHand = person.getInventory().getContinuousResourceAmount(ParticularResources.FOOD);
        double foodAtHand_desperateTargetReserve = person.getNutrition().getFoodReserveAmount(Person.FOOD_RESERVE_DURATION_DESPERATE);

        if (foodAtHand < foodAtHand_desperateTargetReserve) { //desperate
            //subsistence farm
            return PersonActionTemplate.GATHER_FOOD.getNewAction(person);
        } else {
            double referencePrice_food_lastPositiveVolume = getPrice_continuous_lastPositiveVolume(world.getMarket(), ParticularResources.FOOD);
            double moneyAtHand = person.getWallet().getMoney();
            double foodAndMoney_asFoodDuration = person.getNutrition().getFoodReserveDuration(foodAtHand, referencePrice_food_lastPositiveVolume, moneyAtHand);

            if (foodAndMoney_asFoodDuration < Person.FOOD_RESERVE_DURATION_SATISFIED) { //not desperate, not satisfied
                //find the most worthwhile action
                double
                        referencePrice_food_lastUnfulfilledBuy = getPrice_continuous_lastUnfulfilledBuy(world.getMarket(), ParticularResources.FOOD), //potential sell price
                        referencePrice_sticks_lastUnfulfilledBuy = getPrice_continuous_lastUnfulfilledBuy(world.getMarket(), ParticularResources.STICKS), //potential sell price
                        referencePrice_sticks_lastPositiveVolume = getPrice_continuous_lastPositiveVolume(world.getMarket(), ParticularResources.STICKS), //potential buy price
                        referencePrice_basket_lastUnfulfilledBuy = getPrice_discrete_lastUnfulfilledBuy(world.getMarket(), ParticularResources.BASKET); //potential sell price

                double
                        expectedGatherValue_food = person.maximumYield_gatherFood * referencePrice_food_lastUnfulfilledBuy,
                        expectedGatherValue_sticks = person.maximumYield_gatherSticks * referencePrice_sticks_lastUnfulfilledBuy;

                double
                        expectedCraftExpenses_basket = person.minimumConsumption_sticks_craftBasket * referencePrice_sticks_lastPositiveVolume,
                        expectedCraftRevenue_basket = 1 * referencePrice_basket_lastUnfulfilledBuy,
                        expectedCraftProfit_basket = expectedCraftRevenue_basket - expectedCraftExpenses_basket;

                if (person.getInventory().getContinuousResourceAmount(ParticularResources.STICKS) >= person.minimumConsumption_sticks_craftBasket &&
                        expectedCraftProfit_basket >= expectedGatherValue_sticks &&
                        expectedCraftProfit_basket >= expectedGatherValue_food) { //most profitable = craft basket
                    return PersonActionTemplate.CRAFT_BASKET.getNewAction(person);
                } else if (expectedGatherValue_sticks >= expectedGatherValue_food) { //most profitable = gather sticks
                    return PersonActionTemplate.GATHER_STICKS.getNewAction(person);
                } else { //most profitable = gather food
                    return PersonActionTemplate.GATHER_FOOD.getNewAction(person);
                }
            } else { //satisfied
                double breedFoodMinimumCost = BreedAction.BREED_FOOD_COST + BreedAction.MINIMUM_FOOD_INHERITANCE;
                double breedFoodThreshold = breedFoodMinimumCost + foodAtHand_desperateTargetReserve;
                if (foodAtHand >= breedFoodThreshold) { //ready to breed
                    return PersonActionTemplate.BREED.getNewAction(person);
                }

                //add more satisfied actions here, as needed
            }
        }
        return null; //rest, if no applicable actions found
    }

    private double getPrice_continuous_lastPositiveVolume(@NotNull MultiMarket market,
                                                          @NotNull ContinuousResource resource) {
        @Nullable MarketHistoryDataPoint<@NotNull Double>
                lastPositiveVolume = market.getMarket(resource).getHistory().getLastPositiveVolumeData();
        if (lastPositiveVolume != null) {
            return lastPositiveVolume.getPriceVWA();
        }
        return 0;
    }

    private double getPrice_discrete_lastPositiveVolume(@NotNull MultiMarket market,
                                                        @NotNull DiscreteResource resource) {
        @Nullable MarketHistoryDataPoint<@NotNull Integer>
                lastPositiveVolume = market.getMarket(resource).getHistory().getLastPositiveVolumeData();
        if (lastPositiveVolume != null) {
            return lastPositiveVolume.getPriceVWA();
        }
        return 0;
    }

    private double getPrice_continuous_lastUnfulfilledBuy(@NotNull MultiMarket market,
                                                          @NotNull ContinuousResource resource) {
        @Nullable MarketHistoryDataPoint<@NotNull Double>
                lastMarketData = market.getMarket(resource).getHistory().getLast();
        if (lastMarketData != null) {
            return lastMarketData.getPriceHighestUnfulfilledBuy();
        }
        return 0;
    }

    @SuppressWarnings("SameParameterValue")
    private double getPrice_discrete_lastUnfulfilledBuy(@NotNull MultiMarket market,
                                                        @NotNull DiscreteResource resource) {
        @Nullable MarketHistoryDataPoint<@NotNull Integer>
                lastMarketData = market.getMarket(resource).getHistory().getLast();
        if (lastMarketData != null) {
            return lastMarketData.getPriceHighestUnfulfilledBuy();
        }
        return 0;
    }
}
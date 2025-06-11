package markets2.person;

import java.util.Map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.resources.ParticularResources;
import markets2.resources.containers.Inventory;
import markets2.market.MarketHistoryDataPoint;
import markets2.market.MarketOrder.MarketOrderContinuous;
import markets2.market.MarketOrder.MarketOrderDiscrete;
import markets2.market.SingleResourceMarket.SingleResourceMarketContinuous;
import markets2.market.SingleResourceMarket.SingleResourceMarketDiscrete;
import markets2.person.actions.GatherAction.GatherAction_food;
import markets2.World;

//
class PersonMarketAI {
    private static final double
            BUY_PRICE_CHANGE_DESPERATE = 0.2,
            SELL_PRICE_CHANGE_DESPERATE = -0.15,
            BUY_PRICE_CHANGE_ANXIOUS = 0.1,
            FULL_INVENTORY_FULLNESS_THRESHOLD = 0.9,
            SELL_PRICE_CHANGE_FULL_INVENTORY = -0.01,
            PROFIT_MARGIN = 0.01,
            BUY_PRICE_CHANGE_PROFIT = -PROFIT_MARGIN / 2,
            SELL_PRICE_CHANGE_PROFIT = PROFIT_MARGIN / 2;
    private final @NotNull World world; //reference
    private final @NotNull Person person; //reference

    //
    PersonMarketAI(@NotNull World world, @NotNull Person person) {
        this.world = world;
        this.person = person;
    }

    //
    @SuppressWarnings("PointlessArithmeticExpression")
    void decideMarketActions() {
        double
                foodAtHand = person.getInventory().getContinuousResourceAmount(ParticularResources.FOOD),
                foodAtHand_targetReserve_desperate = person.getNutrition().getFoodReserveAmount(Person.FOOD_RESERVE_DURATION_DESPERATE),
                foodAtHand_targetReserve_anxious = person.getNutrition().getFoodReserveAmount(Person.FOOD_RESERVE_DURATION_ANXIOUS);

        double referencePrice_food = 0;
        @Nullable MarketHistoryDataPoint<@NotNull Double>
                lastPositiveVolumeData_food = world.getMarket().getMarket(ParticularResources.FOOD).getHistory().getLastPositiveVolumeData();
        if (lastPositiveVolumeData_food != null) {
            referencePrice_food = lastPositiveVolumeData_food.getPriceVWA();
        } else {
            @Nullable MarketHistoryDataPoint<@NotNull Double>
                    lastData_food = world.getMarket().getMarket(ParticularResources.FOOD).getHistory().getLast();
            if (lastData_food != null) {
                referencePrice_food = lastData_food.getPriceHighestUnfulfilledBuy();
            }
        }

        if (foodAtHand < foodAtHand_targetReserve_anxious) { //anxious or worse
            double missingFood = foodAtHand_targetReserve_anxious - foodAtHand;


            if (foodAtHand < foodAtHand_targetReserve_desperate) { //desperate
                double foodPrice = referencePrice_food * (1 + BUY_PRICE_CHANGE_DESPERATE);
                buyFood(foodPrice, missingFood);
                sellAssets(SELL_PRICE_CHANGE_DESPERATE, foodAtHand_targetReserve_anxious, 0);
            } else { //anxious
                double foodPrice = referencePrice_food * (1 + BUY_PRICE_CHANGE_ANXIOUS);
                buyFood(foodPrice, missingFood);
            }
        } else { //better than anxious

            //buying, limited by available money
            //TODO: finish this
            double additionalBasketWorth = 0;
            if (!person.hasBasket()) {
                double
                        basketLifetime = GatherAction_food.ESTIMATED_BASKET_LIFETIME,
                        gatherYield_food = person.maximumYield_gatherFood,
                        basketYieldIncrease = gatherYield_food * (Person.BASKET_YIELD_INCREASE - 1);
                additionalBasketWorth = basketLifetime * basketYieldIncrease * referencePrice_food;
            }

            double referencePrice_basket = 0;
            @Nullable MarketHistoryDataPoint<@NotNull Integer>
                    lastPositiveVolumeData_basket = world.getMarket().getMarket(ParticularResources.BASKET).getHistory().getLastPositiveVolumeData();
            if (lastPositiveVolumeData_basket != null) {
                referencePrice_basket = lastPositiveVolumeData_basket.getPriceVWA();
            } else {
                @Nullable MarketHistoryDataPoint<@NotNull Integer>
                        lastData_basket = world.getMarket().getMarket(ParticularResources.BASKET).getHistory().getLast();
                if (lastData_basket != null) {
                    referencePrice_basket = lastData_basket.getPriceHighestUnfulfilledBuy();
                }
            }
            double targetPrice_basket = Math.max(additionalBasketWorth, referencePrice_basket * (1 + BUY_PRICE_CHANGE_PROFIT));

            //TODO: temporary testing; improve, see below
            double availableMoney = person.getWallet().getMoney();
            if (availableMoney >= targetPrice_basket) {
                int basketCount = 1;
                buyBasket(targetPrice_basket, basketCount);
                availableMoney -= targetPrice_basket * basketCount;
            }

            if (availableMoney > 0) {
                double sticksWorth = additionalBasketWorth / person.minimumConsumption_sticks_craftBasket; //per 1.0
                double referencePrice_sticks = 0;
                @Nullable MarketHistoryDataPoint<@NotNull Double>
                        lastPositiveVolumeData_sticks = world.getMarket().getMarket(ParticularResources.STICKS).getHistory().getLastPositiveVolumeData();
                if (lastPositiveVolumeData_sticks != null) {
                    referencePrice_sticks = lastPositiveVolumeData_sticks.getPriceVWA();
                } else {
                    @Nullable MarketHistoryDataPoint<@NotNull Double>
                            lastData_sticks = world.getMarket().getMarket(ParticularResources.STICKS).getHistory().getLast();
                    if (lastData_sticks != null) {
                        referencePrice_sticks = lastData_sticks.getPriceHighestUnfulfilledBuy();
                    }
                }
                double targetPrice_sticks = Math.max(sticksWorth, referencePrice_sticks * (1 + BUY_PRICE_CHANGE_PROFIT));
                double targetPrice_food = referencePrice_food * (1 + BUY_PRICE_CHANGE_PROFIT);
                buySticks(targetPrice_sticks, availableMoney / 2 / targetPrice_sticks);
                buyFood(targetPrice_food, availableMoney / 2 / targetPrice_food);
            }


            //TODO: some ideas about weighted buying:
                //buy food, sticks and baskets for cheap
                    //divide available money by 3 (number of distinct items)

                    //discrete items 1, continuous 2
                    //minimum total weight = 1 + 2 = 3
                    //maximum total weight = 1 * 2 + 2 = 4
                    //max basket funds = from (1/3 total) to (2/4 = 1/2 total)
        }

        //TODO: code below is +/- ok, but overwrites orders, which later can't be removed, because duplicate
        // * check anxiousness/desperation states before and separately

        //selling, not limited by available money
        if (person.getInventory().getCapacityFullness() > FULL_INVENTORY_FULLNESS_THRESHOLD) { //full inventory check
            //sell excess assets to free up inventory (except retainable food and baskets)
            sellAssets(SELL_PRICE_CHANGE_FULL_INVENTORY, foodAtHand_targetReserve_anxious, 1);
        } else {
            //sell excess assets for profit (except retainable food and baskets)
            sellAssets(SELL_PRICE_CHANGE_PROFIT, foodAtHand_targetReserve_anxious, 1);
        }
    }

    private void buyFood(double price, double volume) {
        placeBuyOrder_continuous(ParticularResources.FOOD, price, volume);
    }

    private void buySticks(double price, double volume) {
        placeBuyOrder_continuous(ParticularResources.STICKS, price, volume);
    }

    private void buyBasket(double price, int volume) {
        placeBuyOrder_discrete(ParticularResources.BASKET, price, volume);
    }

    private void sellAssets(double priceChange, double retainableFood, int retainableBaskets) {
        @NotNull Inventory inventory = person.getInventory();

        //check continuous resources
        @NotNull @Unmodifiable Map<@NotNull ContinuousResource, @NotNull Double>
                continuousResources = inventory.getContinuousResources();
        for (@NotNull ContinuousResource resource : continuousResources.keySet()) {
            double volume = inventory.getContinuousResourceAmount(resource); //try to sell all
            if (resource == ParticularResources.FOOD) { //TODO: make a blacklist of unsellable resources
                volume -= retainableFood;
            }
            if (volume <= 0) {
                continue;
            }

            @Nullable MarketHistoryDataPoint<@NotNull Double> lastData = world.getMarket().getMarket(resource).getHistory().getLast();
            if (lastData != null) {
                double referencePrice = lastData.getPriceHighestUnfulfilledBuy();//getPriceLowestUnfulfilledSell();
                @Nullable MarketHistoryDataPoint<@NotNull Double> lastPositiveVolumeData = world.getMarket().getMarket(resource).getHistory().getLastPositiveVolumeData();
                if (lastPositiveVolumeData != null) { //take last positive volume VWA price, if such available
                    referencePrice = lastPositiveVolumeData.getPriceVWA();
                }
                if (referencePrice > 0) { //don't sell for 0
                    double price = referencePrice * (1 + priceChange);
                    placeSellOrder_continuous(resource, price, volume);
                }
            }
        }

        //check discrete resources
        @NotNull @Unmodifiable Map<@NotNull DiscreteResource, @NotNull Integer>
                discreteResources = inventory.getDiscreteResources();
        for (@NotNull DiscreteResource resource : discreteResources.keySet()) {
            int volume = inventory.getDiscreteResourceCount(resource); //try to sell all
            if (resource == ParticularResources.BASKET) { //TODO: make a blacklist of unsellable resources
                volume -= retainableBaskets;
            }
            if (volume <= 0) {
                continue;
            }
            @Nullable MarketHistoryDataPoint<@NotNull Integer> lastData = world.getMarket().getMarket(resource).getHistory().getLast();
            if (lastData != null) {
                double referencePrice = lastData.getPriceHighestUnfulfilledBuy();//getPriceLowestUnfulfilledSell();
                @Nullable MarketHistoryDataPoint<@NotNull Integer> lastPositiveVolumeData = world.getMarket().getMarket(resource).getHistory().getLastPositiveVolumeData();
                if (lastPositiveVolumeData != null) { //if no sellers on last data, take last positive volume VWA price
                    referencePrice = lastPositiveVolumeData.getPriceVWA();
                }
                if (referencePrice > 0) { //don't sell for 0
                    double price = referencePrice * (1 + priceChange);
                    placeSellOrder_discrete(resource, price, volume);
                }
            }
        }
    }

    private void placeSellOrder_continuous(@NotNull ContinuousResource resource, double price, double volume) {
        double cappedVolume = Math.min(volume, person.getInventory().getContinuousResourceAmount(resource));
        @NotNull SingleResourceMarketContinuous market = world.getMarket().getMarket(resource);
        @NotNull MarketOrderContinuous order = new MarketOrderContinuous(person, price, cappedVolume);
        person.addSellOrder(resource, order);
        market.placeSellOrder(order);
    }

    private void placeSellOrder_discrete(@NotNull DiscreteResource resource, double price, int volume) {
        int cappedVolume = Math.min(volume, person.getInventory().getDiscreteResourceCount(resource));
        @NotNull SingleResourceMarketDiscrete market = world.getMarket().getMarket(resource);
        @NotNull MarketOrderDiscrete order = new MarketOrderDiscrete(person, price, cappedVolume);
        person.addSellOrder(resource, order);
        market.placeSellOrder(order);
    }

    private void placeBuyOrder_continuous(@NotNull ContinuousResource resource, double price, double volume) {
        double cappedVolume = Math.min(volume, person.getWallet().getMoney() / price);
        @NotNull SingleResourceMarketContinuous market = world.getMarket().getMarket(resource);
        @NotNull MarketOrderContinuous order = new MarketOrderContinuous(person, price, cappedVolume);
        person.addBuyOrder(resource, order);
        market.placeBuyOrder(order);
    }

    @SuppressWarnings("SameParameterValue")
    private void placeBuyOrder_discrete(@NotNull DiscreteResource resource, double price, int volume) {
        int cappedVolume = Math.min(volume, (int) Math.floor(person.getWallet().getMoney() / price));
        @NotNull SingleResourceMarketDiscrete market = world.getMarket().getMarket(resource);
        @NotNull MarketOrderDiscrete order = new MarketOrderDiscrete(person, price, cappedVolume);
        person.addBuyOrder(resource, order);
        market.placeBuyOrder(order);
    }
}
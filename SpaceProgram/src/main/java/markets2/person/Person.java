package markets2.person;

import java.util.List;
import java.util.Random;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import common.NamedInterface;
import markets2.World;
import markets2.TraderInterface;
import markets2.Market;
import markets2.Market.Order;

import static markets2.Market.*;

//
public class Person implements NamedInterface, TraderInterface {
    private static final @NotNull Random RANDOM = new Random();
    private static final @NotNull String DEFAULT_NAME = "Person";
    private static int UNNAMED_PERSON_INDEX = 1;
    private static final double
            MAXIMUM_HEALTH = 20,
            MAXIMUM_HEALTH_REGEN_RATE = 1,
            BASE_FOOD_CONSUMPTION = 1,
            HEALTH_LOSS_PER_MISSING_FOOD = 5,
            FOOD_CONSUMPTION_PER_HEALTH_REGEN = 1,
            MAX_BASE_FARMING_RATE = 1,
            MIN_BASE_FARMING_RATE = 0.6,
            SKILL_FORGET_CHANCE = 0.05,
            SKILL_FORGET_RATE = 0.1,
            SKILL_LEARN_CHANCE = 0.05,
            SKILL_LEARN_RATE = 0.1,
            MAX_TRADE_VOLUME = 8,
            PRICE_CHANGE_DESPERATE = 0.15,
            PRICE_CHANGE_FULL = -0.1,
            PRICE_CHANGE_SATISFIED = 0.01,
            BREED_FOOD_COST = 5,
            BREED_FOOD_TRANSFER = 5,
            PARENT_FARMING_SKILL_TRANSFER_MAX_RATIO = 1;
    private final @NotNull World world; //reference
    private final @NotNull String name;
    private boolean alive;
    private int age;
    private double health;
    private final @NotNull PersonInventory inventory;
    private @Nullable PersonAction action;
    private double baseFarmingRate;
    public double maximumFoodReserveDuration; //temporary; TODO: rework

    //
    public Person(@NotNull World world, @NotNull String name, double parentFarmingSkill) {
        this.world = world;
        this.name = name;
        alive = true;
        age = 0;
        health = MAXIMUM_HEALTH;
        inventory = new PersonInventory();
        action = null;
        baseFarmingRate =
                MIN_BASE_FARMING_RATE +
                RANDOM.nextDouble() * (MAX_BASE_FARMING_RATE - MIN_BASE_FARMING_RATE) +
                RANDOM.nextDouble() * parentFarmingSkill * PARENT_FARMING_SKILL_TRANSFER_MAX_RATIO;

        maximumFoodReserveDuration = 0;
    }

    //unnamed, default name
    public Person(@NotNull World world, double parentFarmingSkill) {
        this(world, getNewDefaultName(), parentFarmingSkill);
    }

    private static @NotNull String getNewDefaultName() {
        @NotNull String name = DEFAULT_NAME + "-" + UNNAMED_PERSON_INDEX;
        UNNAMED_PERSON_INDEX ++;
        return name;
    }

    //
    @Override
    public final @NotNull String getName() {
        return name;
    }

    //
    public final boolean isAlive() {
        return alive;
    }

    //
    public final int getAge() {
        return age;
    }

    //
    public final double getHealth() {
        return health;
    }

    //
    public static double getMaximumHealth() {
        return MAXIMUM_HEALTH;
    }

    //
    @Override
    public final @NotNull PersonInventory getInventory() {
        return inventory;
    }

    //
    public final @Nullable PersonAction getAction() {
        return action;
    }

    //
    public final double getBaseFarmingRate() {
        return baseFarmingRate;
    }

    //gets called by a thread
    public final void updateDecision() {
        if (alive) {
            checkPreviousAction();

            double missingHealth = calculateMissingHealth();
            decideAction(missingHealth);

            //do some more stuff here?
        }
    }

    private void checkPreviousAction() {
        if (action != null && action.getRemainingDuration() <= 0) {
            action = null;
        }
    }

    private double calculateMissingHealth() {
        return Math.max(0, MAXIMUM_HEALTH - health);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void decideAction(double missingHealth) {
        if (action != null) {
            //busy; TODO: interrupt
        } else {
            double foodAtStart = inventory.food;
            double actualFoodReserveDuration = foodAtStart / BASE_FOOD_CONSUMPTION;

            double moneyAtStart = inventory.money;
            @NotNull Market market = world.getMarket();

            double
                    foodLastPrice = getFoodLastPrice(market),
                    referencePrice,
                    maximumBuyableFood;
            if (foodLastPrice <= 0) {
                referencePrice = Double.POSITIVE_INFINITY;
                maximumBuyableFood = 0;
            } else {
                referencePrice = foodLastPrice;
                maximumBuyableFood = moneyAtStart / referencePrice;
            }

            double maximumFood = foodAtStart + maximumBuyableFood;
            maximumFoodReserveDuration = maximumFood / BASE_FOOD_CONSUMPTION;

            double targetMinimumFoodReserveDuration = 5;
            double targetMaximumFoodReserveDuration = 40;
            double targetMinimumFoodReserve = targetMinimumFoodReserveDuration * BASE_FOOD_CONSUMPTION;
            double targetMaximumFoodReserve = targetMaximumFoodReserveDuration * BASE_FOOD_CONSUMPTION;
            double deltaActualMinimumFoodReserve = foodAtStart - targetMinimumFoodReserve;
            double deltaMaximumFoodReserve = maximumFood - targetMaximumFoodReserve;

            double inventoryFilledCapacity = inventory.getFilledCapacity();
            double inventoryMaxCapacity = inventory.getMaxCapacity();
            double inventoryRemainingCapacity = inventoryMaxCapacity - inventoryFilledCapacity;

            double expectedFarmingYield = calculateExpectedFarmingYield();
            double expectedFarmingSurplus = expectedFarmingYield - BASE_FOOD_CONSUMPTION;

            if (deltaActualMinimumFoodReserve < 0) { //needs actual food
                if (expectedFarmingSurplus < 0) { //farm and buy food
                    if (maximumBuyableFood > 0) { //buy food (at lowest-sell/instabuy price)
                        double
                                buyPrice = Math.max(0, referencePrice * (1 + PRICE_CHANGE_DESPERATE)),// * RANDOM.nextDouble())),
                                buyVolume_desired = -deltaActualMinimumFoodReserve,
                                buyVolume_affordable = moneyAtStart / buyPrice;
                        action = newBuyAction(buyPrice, Math.min(MAX_TRADE_VOLUME, Math.min(buyVolume_desired, buyVolume_affordable)));
                    } else { //no money, farm anyway - inefficient subsistence farming; TODO: should sell other assets before farming
                        action = newFarmAction();
                    }
                } else { //just farm
                    action = newFarmAction();
                }
            } else {
                double spareFood = Math.max(0, deltaActualMinimumFoodReserve);

                if (deltaMaximumFoodReserve < 0) { //needs wealth
                    if (expectedFarmingYield > inventoryRemainingCapacity) {
                        //not farm, sell food (at highest-buy/instasell price)
                        double sellPrice;
                        /*if (foodLastPrice > 0) {
                            sellPrice = foodLastPrice * (1 + PRICE_CHANGE_FULL * RANDOM.nextDouble());
                        } else {*/
                            sellPrice = getHighestBuyPrice(market) * (1 + PRICE_CHANGE_FULL);// * RANDOM.nextDouble());
                        //}
                        action = newSellAction(sellPrice, Math.min(MAX_TRADE_VOLUME, spareFood));
                    } else {
                        //farm and sell
                        action = newFarmAction();
                    }
                } else { //satisfied

                    //sell spare? breed?

                    if (spareFood >= BREED_FOOD_COST + BREED_FOOD_TRANSFER) {
                        action = newBreedAction();
                    } /*else {
                        double sellPrice = foodInstasellPrice * (1 + PRICE_CHANGE_SATISFIED);
                        action = newSellAction(sellPrice, Math.min(MAX_TRADE_VOLUME, spareFood));
                    }*/
                }
            }
        }
    }

    private double calculateExpectedFarmingYield() {
        return baseFarmingRate * world.getMaximumFarmingYield();
    }

    private double getFoodLastPrice(@NotNull Market market) {
        @NotNull List<@NotNull MarketHistoryDataPoint> reverseHistory = market.getHistory().reversed();
        for (@NotNull MarketHistoryDataPoint dataPoint : reverseHistory) {
            if (dataPoint.volume() > 0) {
                return dataPoint.VWAPrice();
            }
        }
        return 0;
    }

    private double getHighestBuyPrice(@NotNull Market market) {
        @Nullable Order highestBuyOrder = market.getHighestBuyOrder();
        if (highestBuyOrder != null) {
            return highestBuyOrder.getPrice();
        }
        return 0;
    }

    private @NotNull PersonAction newFarmAction() {
        return new PersonAction(this, "Farm", 1) {
            @Override
            public void action() {
                double standardYield = baseFarmingRate * world.getMaximumFarmingYield();
                double randomInefficiencyRange = 0.2;
                inventory.food += standardYield * (1 - randomInefficiencyRange * RANDOM.nextDouble());

                //increase skills
                if (RANDOM.nextDouble() < SKILL_LEARN_CHANCE) {
                    baseFarmingRate *= (1 + RANDOM.nextDouble() * SKILL_LEARN_RATE);
                }
            }
        };
    }

    private @NotNull PersonAction newBuyAction(double price, double volume) {
        return new PersonAction(this, "Buy food", 1) {
            @Override
            public void action() {
                world.getMarket().placeBuyOrder(new Order(getPerson(), price, Math.min(inventory.money / price, volume)));
            }
        };
    }

    private @NotNull PersonAction newSellAction(double price, double volume) {
        return new PersonAction(this, "Sell food", 1) {
            @Override
            public void action() {
                world.getMarket().placeSellOrder(new Order(getPerson(), price, volume));
            }
        };
    }

    private @NotNull PersonAction newBreedAction() {
        return new PersonAction(this, "Breed", 1) {
            @Override
            public void action() {
                getPerson().inventory.food -= (BREED_FOOD_COST + BREED_FOOD_TRANSFER);
                @NotNull Person kid = new Person(world, getPerson().baseFarmingRate);
                kid.inventory.food += BREED_FOOD_TRANSFER;
                world.addPerson(kid);
            }
        };
    }

    //gets called by a thread
    public final void updateUnconscious() {
        if (alive) {
            performAction();
            eat(calculateMissingHealth()); //eat and heal

            //reduce skills
            if (RANDOM.nextDouble() < SKILL_FORGET_CHANCE) {
                baseFarmingRate *= (1 - RANDOM.nextDouble() * SKILL_FORGET_RATE);
            }

            //suffer from injuries here

            age ++;
            deathCheck();
        }
    }

    private void performAction() {
        if (action != null) {
            action.perform();
        }
    }

    private void eat(double missingHealth) {
        double
                availableFood = inventory.food,
                eatenFood = 0,
                deltaHealth = 0;

        if (availableFood <= BASE_FOOD_CONSUMPTION) { //missing food, losing health
            double missingFood = BASE_FOOD_CONSUMPTION - availableFood;
            eatenFood = availableFood;
            deltaHealth = -missingFood * HEALTH_LOSS_PER_MISSING_FOOD;
        } else { //abundant food, recovering health
            double
                    regenableHealth = Math.min(missingHealth, MAXIMUM_HEALTH_REGEN_RATE),
                    requiredFood = BASE_FOOD_CONSUMPTION + regenableHealth * FOOD_CONSUMPTION_PER_HEALTH_REGEN;
            if (availableFood >= requiredFood) { //recovers health maximally
                eatenFood = requiredFood;
                deltaHealth = regenableHealth;
            } else { //recovers health partially
                double
                        foodRegenPart = availableFood - BASE_FOOD_CONSUMPTION,
                        healthRegen = foodRegenPart / FOOD_CONSUMPTION_PER_HEALTH_REGEN;
                eatenFood = availableFood;
                deltaHealth = healthRegen;
            }
        }

        inventory.food -= eatenFood;
        health += deltaHealth;
    }

    private void deathCheck() {
        if (isDead()) {
            alive = false;
        }
    }

    private boolean isDead() {
        return health <= 0;
    }
}
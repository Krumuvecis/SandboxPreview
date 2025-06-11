package markets2.person;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ParticularResources;
import markets2.resources.containers.Inventory;
import markets2.UpdatableInterface;

//
public final class PersonNutrition implements UpdatableInterface {
    private static final double
            DEFAULT_MAX_FOOD_THROUGHPUT = 2, //cap
            DEFAULT_BASE_FOOD_CONSUMPTION = 1,
            FOOD_CONSUMPTION_PER_HEALTH_REGEN = 1, //per 1 health
            HEALTH_LOSS_PER_MISSING_FOOD = 5, //per all base
            MAXIMUM_HEALTH_REGEN_RATE = 1; //cap

    private final @NotNull PersonHealth health;
    private final @NotNull Inventory inventory;
    private final double
            maxFoodThroughput, //cap
            baseFoodConsumption;

    //custom limits
    PersonNutrition(@NotNull PersonHealth health, @NotNull Inventory inventory,
                    double maxFoodThroughput, double baseFoodConsumption) {
        this.health = health;
        this.inventory = inventory;
        this.maxFoodThroughput = maxFoodThroughput;
        this.baseFoodConsumption = baseFoodConsumption;
    }

    //default limits
    PersonNutrition(@NotNull PersonHealth health, @NotNull Inventory inventory) {
        this(health, inventory, DEFAULT_MAX_FOOD_THROUGHPUT, DEFAULT_BASE_FOOD_CONSUMPTION);
    }

    //base; capped at max throughput
    public double getBaseFoodConsumption() {
        return getCappedFoodConsumption(baseFoodConsumption);
    }

    //base + health regen; capped at max throughput
    public double getOptimalFoodConsumption() {
        double maxHealthRegen = Math.min(health.getMissingHealth(), MAXIMUM_HEALTH_REGEN_RATE);
        double healthRegenFoodConsumption = maxHealthRegen * FOOD_CONSUMPTION_PER_HEALTH_REGEN;
        return getCappedFoodConsumption(baseFoodConsumption + healthRegenFoodConsumption);
    }

    private double getCappedFoodConsumption(double consumption) {
        return Math.max(0, Math.min(consumption, maxFoodThroughput));
    }

    //uses base consumption for reference
    public double getFoodReserveDuration(double availableFood, double referenceFoodPrice, double referenceAssetValue) {
        double buyableFood = 0;
        if (referenceAssetValue > 0) {
            buyableFood = referenceAssetValue / referenceFoodPrice;
        }
        return (availableFood + buyableFood) / getBaseFoodConsumption();
    }

    //uses base consumption for reference
    public double getFoodReserveAmount(double duration) {
        return baseFoodConsumption * duration;
    }

    //alive status must already be checked
    @Override
    public void update() {
        eat();
    }

    private void eat() {
        double
                availableFood = inventory.getContinuousResourceAmount(ParticularResources.FOOD),
                baseFoodConsumption = getBaseFoodConsumption(),
                eatenFood = 0,
                deltaHealth = 0;

        if (availableFood <= baseFoodConsumption) { //missing food, losing health
            double missingFood = baseFoodConsumption - availableFood;
            eatenFood = availableFood;
            deltaHealth = -missingFood * HEALTH_LOSS_PER_MISSING_FOOD;
        } else { //abundant food, recovering health
            double optimalFoodConsumption = getOptimalFoodConsumption();
            eatenFood = Math.min(optimalFoodConsumption, availableFood);
            deltaHealth = Math.min(
                    MAXIMUM_HEALTH_REGEN_RATE,
                    (eatenFood - baseFoodConsumption) / FOOD_CONSUMPTION_PER_HEALTH_REGEN);
        }

        inventory.subtractContinuousResource(ParticularResources.FOOD, eatenFood);
        health.addHealth(deltaHealth);
    }
}
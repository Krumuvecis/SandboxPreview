package markets2.person;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

import markets2.person.skills.PersonSkills;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import markets2.resources.ResourceInterface;
import markets2.resources.ContinuousResource;
import markets2.resources.DiscreteResource;
import markets2.resources.ParticularResources;
import markets2.resources.containers.Wallet;
import markets2.resources.containers.Inventory;
import markets2.TraderInterface;
import markets2.market.MarketOrder;
import markets2.market.MarketOrder.MarketOrderContinuous;
import markets2.market.MarketOrder.MarketOrderDiscrete;
import markets2.person.actions.PersonAction;
import markets2.World;

//
public class Person extends NamedPerson implements TraderInterface {
    public static final double
            FOOD_RESERVE_DURATION_DESPERATE = 3,
            FOOD_RESERVE_DURATION_ANXIOUS = 6,
            FOOD_RESERVE_DURATION_SATISFIED = 40,
            BASKET_YIELD_INCREASE = 2; //times
    private static final double CRAFT_BASKET_BASE_STICKS_CONSUMPTION = 5;
    private final @NotNull World world; //reference
    private final @NotNull Wallet wallet = new Wallet();
    private final @NotNull Inventory inventory = new Inventory();
    private final @NotNull PersonHealth health = new PersonHealth();
    private final @NotNull PersonNutrition nutrition;
    private final @NotNull PersonSkills skills;
    public double
            maximumYield_gatherFood = 0, //temporary; TODO: rework
            maximumYield_gatherSticks = 0, //temporary; TODO: rework
            minimumConsumption_sticks_craftBasket = Double.POSITIVE_INFINITY; //temporary; TODO: rework
    private @Nullable PersonAction action = null;
    private final @NotNull Map<@NotNull ResourceInterface, @NotNull MarketOrder<? extends @NotNull Number>>
            sellOrders = new HashMap<>(),
            buyOrders = new HashMap<>();
    private final @NotNull PersonActionAI actionAI;
    private final @NotNull PersonMarketAI marketAI;

    //null name generates new name; null parent-skills generate default skills
    public Person(@NotNull World world, @Nullable String name, @Nullable PersonSkills parentSkills) {
        super(name);
        this.world = world;
        nutrition = new PersonNutrition(health, inventory);
        skills = new PersonSkills(parentSkills);
        actionAI = new PersonActionAI(world, this);
        marketAI = new PersonMarketAI(world, this);
    }

    //reference for breeding
    public final @NotNull World getWorld() {
        return world;
    }

    //
    @Override
    public final @NotNull Wallet getWallet() {
        return wallet;
    }

    //
    @Override
    public final @NotNull Inventory getInventory() {
        return inventory;
    }

    //TODO: temporary; rework
    public final boolean hasBasket() {
        return inventory.getDiscreteResourceCount(ParticularResources.BASKET) > 0;
    }

    //
    public final @NotNull PersonHealth getHealth() {
        return health;
    }

    //
    public final @NotNull PersonNutrition getNutrition() {
        return nutrition;
    }

    //
    public final @NotNull PersonSkills getSkills() {
        return skills;
    }

    //
    public final @Nullable PersonAction getAction() {
        return action;
    }

    //
    @Override
    public final void removeCompletedSellOrder(@NotNull ResourceInterface resource,
                                               @NotNull MarketOrder<? extends @NotNull Number> order) {
        if (!sellOrders.remove(resource, order)) {
            throw new RuntimeException("Couldn't remove a completed sell order.");
        }
    }

    //
    @Override
    public final void removeCompletedBuyOrder(@NotNull ResourceInterface resource,
                                              @NotNull MarketOrder<? extends @NotNull Number> order) {
        if (!buyOrders.remove(resource, order)) {
            throw new RuntimeException("Couldn't remove a completed buy order.");
        }
    }

    //gets called by a thread
    public final void updateActionDecisions() {
        if (health.isAlive()) {
            maximumYield_gatherFood = world.getMaximumYield_gatherFood() * skills.getSkill_gatherFood();
            if (hasBasket()) {
                maximumYield_gatherFood *= BASKET_YIELD_INCREASE;
            }
            maximumYield_gatherSticks = world.getMaximumYield_gatherSticks() * skills.getSkill_gatherSticks();
            minimumConsumption_sticks_craftBasket = CRAFT_BASKET_BASE_STICKS_CONSUMPTION / skills.getSkill_craftBasket();

            checkPreviousAction();
            if (action == null) { //non-null action interrupts must have already been checked
                action = actionAI.decideNewAction();
                //do some more stuff here?
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    private void checkPreviousAction() {
        if (action != null) {
            if (action.getRemainingDuration() <= 0) { //previous action complete
                action = null;
            } else { //busy
                //TODO: interrupt; maybe bring this to AI?
            }
        }
    }

    //gets called by a thread; performs actions and updates skills
    public final void performAction() {
        if (health.isAlive()) {
            if (action != null) {
                action.perform();
            }

            skills.update();
        }
    }

    //gets called by a thread; manages trade orders after performing the action
    public final void updateMarketDecisions() {
        if (health.isAlive()) {
            //first, remove all previous orders
            retractSellOrders();
            retractBuyOrders();

            //then, place new orders
            marketAI.decideMarketActions();
        }
    }

    private void retractSellOrders() {
        for (@NotNull ResourceInterface resource : Set.copyOf(sellOrders.keySet())) {
            @NotNull MarketOrder<? extends @NotNull Number> order = sellOrders.get(resource);
            if (resource instanceof @NotNull ContinuousResource continuousResource) {
                if (order instanceof @NotNull MarketOrderContinuous orderContinuous) {
                    world.getMarket().getMarket(continuousResource).retractSellOrder(orderContinuous);
                } else throw new RuntimeException("Unrecognized market order type");
            } else if (resource instanceof @NotNull DiscreteResource discreteResource) {
                if (order instanceof @NotNull MarketOrderDiscrete orderDiscrete) {
                    world.getMarket().getMarket(discreteResource).retractSellOrder(orderDiscrete);
                } else throw new RuntimeException("Unrecognized market order type");
            } else throw new RuntimeException("Unrecognized resource type");
            sellOrders.remove(resource);
        }
    }

    private void retractBuyOrders() {
        for (@NotNull ResourceInterface resource : Set.copyOf(buyOrders.keySet())) {
            @NotNull MarketOrder<? extends @NotNull Number> order = buyOrders.get(resource);
            if (resource instanceof @NotNull ContinuousResource continuousResource) {
                if (order instanceof @NotNull MarketOrderContinuous orderContinuous) {
                    world.getMarket().getMarket(continuousResource).retractBuyOrder(orderContinuous);
                } else throw new RuntimeException("Unrecognized market order type");
            } else if (resource instanceof @NotNull DiscreteResource discreteResource) {
                if (order instanceof @NotNull MarketOrderDiscrete orderDiscrete) {
                    world.getMarket().getMarket(discreteResource).retractBuyOrder(orderDiscrete);
                } else throw new RuntimeException("Unrecognized market order type");
            } else throw new RuntimeException("Unrecognized resource type");
            buyOrders.remove(resource);
        }
    }

    //for internal use
    public final void addSellOrder(@NotNull ResourceInterface resource,
                            @NotNull MarketOrder<? extends @NotNull Number> order) {
        sellOrders.put(resource, order);
    }

    //for internal use
    public final void addBuyOrder(@NotNull ResourceInterface resource,
                           @NotNull MarketOrder<? extends @NotNull Number> order) {
        buyOrders.put(resource, order);
    }

    //gets called by a thread; eating, healing, aging, death check, etc.
    public final void updateNutritionAndHealth() {
        if (health.isAlive()) {
            nutrition.update();
            health.update();
        }
        if (!health.isAlive()) {
            retractSellOrders();
            retractSellOrders();
        }
    }
}
package markets2.person.actions;

import org.jetbrains.annotations.NotNull;

import markets2.resources.ParticularResources;
import markets2.resources.ContinuousResource;
import markets2.Wallet;
import markets2.person.PersonInventory;
import markets2.person.Person;
import markets2.World;

//
public final class BreedAction extends PersonAction {
    public static final double
            BREED_FOOD_COST = 4,
            MINIMUM_FOOD_INHERITANCE = 4;
    private static final double
            MONEY_INHERITANCE_RATIO = 0.2,
            FOOD_INHERITANCE_RATIO = 0.2;
    private static final @NotNull ContinuousResource FOOD = ParticularResources.FOOD; //cache
    private final @NotNull World world; //reference
    private final @NotNull PersonInventory parentInventory; //cache
    private final @NotNull Wallet parentWallet; //cache

    //
    public BreedAction(@NotNull PersonActionTemplate actionTemplate, @NotNull Person person) {
        super(actionTemplate, person, 1);
        world = person.getWorld();
        parentInventory = person.getInventory();
        parentWallet = person.getWallet();
    }

    //
    @Override
    public void action() {
        @NotNull Person kid = getNewKid();
        transferInheritance(kid);
        world.addPerson(kid);
    }

    private @NotNull Person getNewKid() {
        parentInventory.subtractContinuousResource(FOOD, BREED_FOOD_COST);
        return new Person(world, null, getPerson().getSkills());
    }

    private void transferInheritance(@NotNull Person kid) {
        double
                moneyInheritance = parentWallet.getMoney() * MONEY_INHERITANCE_RATIO,
                foodInheritance = Math.max(
                        MINIMUM_FOOD_INHERITANCE,
                        parentInventory.getContinuousResourceAmount(FOOD) * FOOD_INHERITANCE_RATIO);

        //subtract from parent
        parentWallet.addMoney(-moneyInheritance);
        parentInventory.subtractContinuousResource(FOOD, foodInheritance);

        //add to kid
        kid.getWallet().addMoney(moneyInheritance);
        kid.getInventory().addContinuousResource(FOOD, foodInheritance);
    }
}
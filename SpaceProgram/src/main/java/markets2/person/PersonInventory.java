package markets2.person;

//
public class PersonInventory {
    private static final double DEFAULT_MAX_CAPACITY = 20;
    private final double maxCapacity;
    public double
            money,
            food,
            sticks;
    public int baskets;

    //
    public PersonInventory() {
        maxCapacity = DEFAULT_MAX_CAPACITY;
        money = 0;
        food = 0;
        sticks = 0;
        baskets = 0;
    }

    //
    public final double getMaxCapacity() {
        return maxCapacity;
    }

    //
    public final double getFilledCapacity() {
        return food + sticks;
    }
}

package markets2.person;

import markets2.resources.ResourceSet;
import markets2.resources.containers.LimitedResourceContainer;

//
public class PersonInventory extends ResourceSet implements LimitedResourceContainer {
    private static final double
            DEFAULT_MASS_LIMIT = 20,
            DEFAULT_VOLUME_LIMIT = 20;
    private final double
            massLimit,
            volumeLimit;

    //custom capacity
    public PersonInventory(double massLimit, double volumeLimit) {
        super();
        this.massLimit = massLimit;
        this.volumeLimit = volumeLimit;
    }

    //default capacity
    public PersonInventory() {
        this(DEFAULT_MASS_LIMIT, DEFAULT_VOLUME_LIMIT);
    }

    //
    @Override
    public final double getLimit_mass() {
        return massLimit;
    }

    //
    @Override
    public final double getLimit_volume() {
        return volumeLimit;
    }
}
package markets2.resources.containers.examples;

import markets2.resources.ResourceSet;
import markets2.resources.containers.LimitedResourceContainer;

//An example on how to use limited resource containers.
@SuppressWarnings("unused")
final class ExampleLimitedContainer extends ResourceSet implements LimitedResourceContainer {
    private final double
            massLimit,
            volumeLimit;

    //
    ExampleLimitedContainer(double massLimit, double volumeLimit) {
        super();
        this.massLimit = massLimit;
        this.volumeLimit = volumeLimit;
    }

    //
    @Override
    public double getLimit_mass() {
        return massLimit;
    }

    //
    @Override
    public double getLimit_volume() {
        return volumeLimit;
    }
}
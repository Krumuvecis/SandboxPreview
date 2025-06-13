package markets2.resources;

import org.jetbrains.annotations.NotNull;

//
public class DiscreteResource extends AbstractResource {
    private final double
            mass, //per single-item
            volume; //per single-item

    //custom size
    DiscreteResource(@NotNull String name, double mass, double volume) {
        super(name);
        nonNegativeCheck(name, "mass", mass);
        nonNegativeCheck(name, "volume", volume);
        this.mass = mass;
        this.volume = volume;
    }

    //size = 0; TODO: intended for deeds, other papers, etc. (not money though, as money is non-discrete)
    @SuppressWarnings("unused")
    DiscreteResource(@NotNull String name) {
        this(name, 0, 0);
    }

    private static void nonNegativeCheck(@NotNull String resourceName, @NotNull String valueName, double value) {
        if (value < 0) {
            throw new RuntimeException("Discrete resource (" + resourceName + ") initialization exception: " +
                    "Negative " + valueName + " not supported.");
        }
    }

    //per single-item
    public final double getMass() {
        return mass;
    }

    //per single-item
    public final double getVolume() {
        return volume;
    }
}
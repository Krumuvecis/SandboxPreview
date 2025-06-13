package markets2.resources;

import org.jetbrains.annotations.NotNull;

//
public class ContinuousResource extends AbstractResource {
    private final double density;

    //
    ContinuousResource(@NotNull String name, double density) {
        super(name);
        this.density = density;
    }

    //
    public final double getDensity() {
        return density;
    }

    //
    public final double getVolume(double mass) {
        return mass / density;
    }
}
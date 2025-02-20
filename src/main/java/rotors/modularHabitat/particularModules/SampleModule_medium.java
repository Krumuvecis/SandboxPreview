package rotors.modularHabitat.particularModules;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import dimensions.mass.MassUnit;
import rotors.modularHabitat.HabitatModule;

//
public final class SampleModule_medium extends HabitatModule {
    private static final @NotNull Color COLOR = new Color(130, 100, 70);
    private static final @NotNull Mass MASS = new Mass(5, MassUnit.T);

    //
    public SampleModule_medium() {
        super(COLOR);
    }

    //
    @Override
    public @NotNull HabitatModule copy() {
        return new SampleModule_medium();
    }

    //
    @Override
    public @NotNull Mass getMass() {
        return MASS;
    }
}
package rotors.rotatingHabitat.modularHabitat.particularModules;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import dimensions.mass.MassUnit;
import rotors.rotatingHabitat.modularHabitat.HabitatModule;

//
public final class SampleModule_heavy extends HabitatModule {
    private static final @NotNull Color COLOR = new Color(80, 70, 40);
    private static final @NotNull Mass MASS = new Mass(10, MassUnit.T);

    //
    public SampleModule_heavy() {
        super(COLOR);
    }

    //
    @Override
    public @NotNull HabitatModule copy() {
        return new SampleModule_heavy();
    }

    //
    @Override
    public @NotNull Mass getMass() {
        return MASS;
    }
}
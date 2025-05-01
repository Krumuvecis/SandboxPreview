package rotors.rotatingHabitat.modularHabitat.particularModules;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import dimensions.mass.MassUnit;
import rotors.rotatingHabitat.modularHabitat.HabitatModule;

//
public final class SampleModule_light extends HabitatModule {
    private static final @NotNull Color COLOR = new Color(200, 170, 150);
    private static final @NotNull Mass MASS = new Mass(2, MassUnit.T);

    //
    public SampleModule_light() {
        super(COLOR);
    }

    //
    @Override
    public @NotNull HabitatModule copy() {
        return new SampleModule_light();
    }

    //
    @Override
    public @NotNull Mass getMass() {
        return MASS;
    }
}
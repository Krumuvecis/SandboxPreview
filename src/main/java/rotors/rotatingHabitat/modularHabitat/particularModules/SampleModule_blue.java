package rotors.rotatingHabitat.modularHabitat.particularModules;

import java.awt.Color;

import org.jetbrains.annotations.NotNull;

import dimensions.mass.Mass;
import dimensions.mass.MassUnit;
import rotors.rotatingHabitat.modularHabitat.HabitatModule;

//
public final class SampleModule_blue extends HabitatModule {
    private static final @NotNull Color COLOR = new Color(85, 80, 180);
    private static final @NotNull Mass MASS = new Mass(15, MassUnit.T);

    //
    public SampleModule_blue() {
        super(COLOR);
    }

    //
    @Override
    public @NotNull HabitatModule copy() {
        return new SampleModule_blue();
    }

    //
    @Override
    public @NotNull Mass getMass() {
        return MASS;
    }
}
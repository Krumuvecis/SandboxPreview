package rotors.modularHabitat.particularModules;

import org.jetbrains.annotations.NotNull;
import rotors.modularHabitat.HabitatModule;

import java.awt.*;

//
public final class SampleModule_magenta extends HabitatModule {
    //
    public SampleModule_magenta() {
        super(Color.magenta);
    }

    //
    @Override
    public @NotNull HabitatModule copy() {
        return new rotors.modularHabitat.particularModules.SampleModule_magenta();
    }
}

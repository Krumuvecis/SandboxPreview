package rotors.modularHabitat.particularModules;

import org.jetbrains.annotations.NotNull;
import rotors.modularHabitat.HabitatModule;

import java.awt.*;

//
public final class SampleModule_green extends HabitatModule {
    //
    public SampleModule_green() {
        super(Color.green);
    }

    //
    @Override
    public @NotNull HabitatModule copy() {
        return new rotors.modularHabitat.particularModules.SampleModule_green();
    }
}

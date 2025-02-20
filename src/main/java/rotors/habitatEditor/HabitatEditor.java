package rotors.habitatEditor;

import org.jetbrains.annotations.NotNull;

import rotors.habitatEditor.window.Window;
import rotors.modularHabitat.ModularHabitat;

//
public final class HabitatEditor {
    private static final @NotNull ModularHabitat HABITAT = new ModularHabitat.SampleHabitat();
    private static final long WINDOW_FPS = 50;

    //
    public static void main(String[] args) {
        new Window(HABITAT, WINDOW_FPS);
    }
}
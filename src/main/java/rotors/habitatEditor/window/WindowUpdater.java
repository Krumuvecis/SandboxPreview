package rotors.habitatEditor.window;

import org.jetbrains.annotations.NotNull;

import delayCalculator.delayOptions.DelayType;
import delayCalculator.delayOptions.DelayOptions;
import ThreadAbstraction.AbstractUpdater;

//
final class WindowUpdater extends AbstractUpdater {
    private final @NotNull Window window;

    //
    WindowUpdater(@NotNull Window window, long fps) {
        super(new DelayOptions(DelayType.FPS, fps));
        this.window = window;
    }

    //
    @Override
    public void update() {
        window.repaint();
    }
}
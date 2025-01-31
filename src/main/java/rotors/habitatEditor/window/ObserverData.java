package rotors.habitatEditor.window;

import org.jetbrains.annotations.NotNull;

//
public final class ObserverData {
    private static final double DEFAULT_SCALE = 30;
    private double scale;
    private final double @NotNull [] viewLocationActual;

    //
    ObserverData(double habitatLength_actual) {
        scale = DEFAULT_SCALE;
        viewLocationActual = new double[] {habitatLength_actual / 2, 0};
    }

    //
    public double getScale() {
        return scale;
    }

    //
    void increaseScale(double delta) {
        scale += delta;
    }

    void increaseScaleRelatively(double rate) {
        increaseScale(rate * scale);
    }

    //for view center
    public double @NotNull [] getViewLocationActual() {
        return viewLocationActual;
    }

    //for mouse actual location
    public double @NotNull [] getOffsetLocationActual(double @NotNull [] scaledDelta) {
        return new double[] {
                viewLocationActual[0] + scaledDelta[0] / scale,
                viewLocationActual[1] + scaledDelta[1] / scale};
    }

    //for camera movement
    void increaseViewLocation(double @NotNull [] scaledDelta) {
        viewLocationActual[0] += scaledDelta[0] / scale;
        viewLocationActual[1] += scaledDelta[1] / scale;
    }
}
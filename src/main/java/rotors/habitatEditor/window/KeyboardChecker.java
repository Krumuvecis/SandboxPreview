package rotors.habitatEditor.window;

import java.util.List;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.jetbrains.annotations.NotNull;

import static consoleUtils.SimplePrinting.printLine;
import ThreadAbstraction.AbstractUpdater;

//
public final class KeyboardChecker extends AbstractUpdater implements KeyListener {
    private static final double
            RELATIVE_VIEW_SPEED = 10,
            RELATIVE_ZOOM_SPEED = 0.05;
    private final @NotNull ObserverData observerData;
    private final @NotNull java.util.List<@NotNull KeyEvent> pressedKeys;

    //
    KeyboardChecker(long delay, @NotNull ObserverData observerData) {
        super(delay);
        this.observerData = observerData;
        pressedKeys = new ArrayList<>();
    }

    //for graphical & debug purposes
    public @NotNull List<@NotNull KeyEvent> getPressedKeys() {
        return pressedKeys;
    }

    private int getPressedKeyIndex(@NotNull KeyEvent e) {
        int keyCode = e.getKeyCode();
        for (int i = 0; i < pressedKeys.size(); i++) {
            @NotNull KeyEvent e1 = pressedKeys.get(i);
            if (e1.getKeyCode() == keyCode) {
                return i;
            }
        }
        return -1;
    }

    //
    @Override
    public void keyPressed(@NotNull KeyEvent e) {
        if (getPressedKeyIndex(e) < 0) {
            pressedKeys.add(e);
        }
    }

    //
    @Override
    public void keyReleased(@NotNull KeyEvent e) {
        int pressedKeyIndex = getPressedKeyIndex(e);
        if (pressedKeyIndex >= 0) {
            pressedKeys.remove(pressedKeyIndex);
        }
    }

    //unused functionality
    @Override
    public void keyTyped(@NotNull KeyEvent e) {
    }

    //
    @Override
    public void update() {
        for (@NotNull KeyEvent e : pressedKeys) {
            keyAction(e);
        }
    }

    private void keyAction(@NotNull KeyEvent e) {
        //printLine("pressed key: " + e.getKeyCode() + ", " + e.getKeyChar());
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 33 -> observerData.increaseScaleRelatively(RELATIVE_ZOOM_SPEED);                    //PageUp
            case 34 -> observerData.increaseScaleRelatively(-RELATIVE_ZOOM_SPEED);                   //PageDown
            case 68, 39 -> observerData.increaseViewLocation(new double[]{RELATIVE_VIEW_SPEED, 0}); //d, right
            case 65, 37 -> observerData.increaseViewLocation(new double[]{-RELATIVE_VIEW_SPEED, 0});//a, left
            case 83, 40 -> observerData.increaseViewLocation(new double[]{0, RELATIVE_VIEW_SPEED}); //s, down
            case 87, 38 -> observerData.increaseViewLocation(new double[]{0, -RELATIVE_VIEW_SPEED});//w, up
            default -> printLine("pressed key: " + keyCode + ", " + KeyEvent.getKeyText(keyCode));
        }
    }
}
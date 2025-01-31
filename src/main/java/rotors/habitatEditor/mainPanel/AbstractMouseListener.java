package rotors.habitatEditor.mainPanel;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.event.MouseInputListener;

import org.jetbrains.annotations.NotNull;

//
abstract class AbstractMouseListener implements MouseMotionListener, MouseInputListener {
    //
    @Override
    public final void mouseMoved(@NotNull MouseEvent e) {
        mouseMovedAction(e.getPoint());
    }

    //
    @Override
    public final void mouseDragged(@NotNull MouseEvent e) {
        mouseMovedAction(e.getPoint());
    }

    //currently unused
    @Override
    public final void mouseClicked(@NotNull MouseEvent e) {
        //mouseClickedAction((e.getPoint(), e.getButton());
    }

    //
    @Override
    public final void mousePressed(@NotNull MouseEvent e) {
        mouseClickedAction(e.getPoint(), e.getButton());
    }

    //currently unused
    @Override
    public final void mouseReleased(@NotNull MouseEvent e) {
        //mouseClickedAction((e.getPoint(), e.getButton());
    }

    //currently unused
    @Override
    public final void mouseEntered(@NotNull MouseEvent e) {}

    //currently unused
    @Override
    public final void mouseExited(@NotNull MouseEvent e) {}

    //gets called when the mouse is moved
    abstract void mouseMovedAction(@NotNull Point location);

    //gets called when the mouse is clicked
    abstract void mouseClickedAction(@NotNull Point location, int button);
}
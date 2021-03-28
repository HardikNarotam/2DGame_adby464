import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelHandler implements MouseWheelListener {
    private GameView view;

    public MouseWheelHandler(GameView v) {
        this.view = v;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.isControlDown())
        {
            view.setZoom(view.getZoom()+5);
        } else {
            view.setZoom(view.getZoom()-5);
        }
    }
}
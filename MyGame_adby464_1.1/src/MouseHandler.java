import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    private GameLevel level;
    private GameView view;

    public MouseHandler(GameLevel l, GameView v) {
        level = l;
        view = v;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Bullet bullet = new Bullet(level);
        Point mousePoint = e.getPoint();
        Vec2 worldPoint = view.viewToWorld(mousePoint);
        Vec2 playerPoint = level.getPlayer().getPosition();
        bullet.setGravityScale(0);
        if (worldPoint.x < playerPoint.x) {
            bullet.setPosition(new Vec2(playerPoint.x -1, playerPoint.y+0.8f));
            bullet.setLinearVelocity(new Vec2(-20.0f, 0.0f));
        } else {
            bullet.setPosition(new Vec2(playerPoint.x +1, playerPoint.y+0.8f));
            bullet.setLinearVelocity(new Vec2(20.0f, 0.0f));
        }
        bullet.playSound();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void updateHandler(GameLevel l, GameView v) {
        this.level = l;
        this.view = v;
    }
}
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerController implements KeyListener {

    private Player player;

    private static final float WALKING_SPEED = 5;

    public PlayerController(Player p) {
        player = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            player.changeImage("data/player_stand2.png");
            player.startWalking(-WALKING_SPEED);
        } else if (code == KeyEvent.VK_D) {
            player.changeImage("data/player_stand.png");
            player.startWalking(WALKING_SPEED);
        } else if (code == KeyEvent.VK_W) {
            player.jump(11);
        } else if (code == KeyEvent.VK_S) {
            // player crouch
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_A) {
            player.stopWalking();
        } else if (code == KeyEvent.VK_D) {
            player.stopWalking();
        }
    }

    public void updatePlayer(Player player) {
        this.player = player;
    }
}

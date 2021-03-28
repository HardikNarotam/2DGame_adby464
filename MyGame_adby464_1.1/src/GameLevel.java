import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

public abstract class GameLevel extends World {

    private Player player;
    private List<Bot> bots = new ArrayList<Bot>();
    private Game game;

    public GameLevel(Game game) {
        this.game = game;
    }

    public void populate(Game game) {
        player = new Player(game, this);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public void setBot(GameLevel level) {
        Bot bot = new Bot(level);
        bots.add(bot);
    }

    public List<Bot> getBotList() {
        return bots;
    }

    public void displayBot() {
        for (Bot bot : game.getLevel().getBotList()) {
            float result = (float) Math.random() * 20.0f - 11.0f;
            bot.setPosition(new Vec2(result, 5f));
            BotCollisions collisions = new BotCollisions(bot, this, game);
            bot.addCollisionListener(collisions);
        }
    }
    public abstract boolean isComplete();

    public abstract String getLevelName();

}

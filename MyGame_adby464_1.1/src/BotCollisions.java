import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class BotCollisions implements CollisionListener {

    private Bot bot;
    private GameLevel level;
    private Game game;

    public BotCollisions(Bot b, GameLevel level, Game game) {
        bot = b;
        this.level = level;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Bullet) {
            collisionEvent.getOtherBody().destroy();
            int health = bot.getHealth() - 1;
            bot.setHealth(health);
            System.out.println("Bot Health: "+health);
            if (bot.getHealth() <= 0) {
                game.getLevel().getPlayer().addScore();
                bot.playSound();
                bot.destroy();
            }
        }
    }
}

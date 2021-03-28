import city.cs.engine.*;
import city.cs.engine.Shape;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      Hardik, Narotam, hardik.narotam@city.ac.uk
 * @version     1.3
 * @since       1
 */
public class Bot extends Walker {

    /**
     * health of a bot
     */
    private int health = 10;

    /**
     * shape of a bot
     */
    private static final Shape BotShape = new PolygonShape(-0.86f,1.69f, -0.89f,-1.5f, 0.84f,-1.49f, 0.76f,1.66f, -0.82f,1.72f);

    /**
     * image of a bot
     */
    private static final BodyImage image =
            new BodyImage("data/bot.png", 4f);


    /**
     * Bot class constructor
     * <p>
     * Makes an Bot object
     *
     * @param  level Current level of the game.
     * @return
     */
    public Bot(GameLevel level) {
        super(level, BotShape);
        addImage(image);

    }

    /**
     * getter method of health
     * <p>
     * returns the health of a bot in the current level.
     *
     * @param
     * @return current health of a bot+
     */
    public int getHealth() {
        return health;
    }

    /**
     * setter method for health
     * <p>
     * Set a specific health of a bot
     *
     * @param  h takes an integer which will be the health of a bot
     * @return
     */
    public void setHealth(int h) {
        health = h;
    }

    /**
     * plays a bot dying sound
     * <p>
     * This method will play the sound effect of a bot dying.
     *
     * @param
     * @return
     */
    public void playSound() {
        try {
            SoundClip botDead = new SoundClip("data/bot_dead.mp3");
            botDead.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
}

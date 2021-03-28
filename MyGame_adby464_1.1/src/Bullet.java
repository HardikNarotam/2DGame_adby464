import city.cs.engine.*;
import city.cs.engine.Shape;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Bullet extends DynamicBody {

    private static final Shape bulletShape = new BoxShape(0.5f,0.2f);

    private static final BodyImage image =
            new BodyImage("data/bullet.png", 0.5f);

    public Bullet(GameLevel level) {
        super(level, bulletShape);
        addImage(image);
    }

    public void playSound() {
        try {
            SoundClip shoot = new SoundClip("data/shoot.wav");
            shoot.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
}

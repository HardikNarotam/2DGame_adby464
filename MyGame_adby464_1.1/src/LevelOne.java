import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

/**
 * @author      Hardik, Narotam, hardik.narotam@city.ac.uk
 * @version     1.3
 * @since       1.2
 */
public class LevelOne extends GameLevel {

    /**
     * LevelOne class constructor
     * <p>
     * Call to instantiate a object
     *
     * @param game Game class
     * @return
     */
    public LevelOne(Game game) {
        super(game);

        // making the ground
        Shape shape = new BoxShape(8, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -10));

        // making the platforms
        Shape shape1 = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-9, -5));

        Shape shape1_1 = new BoxShape(3, 0.5f);
        StaticBody platform1_1 = new StaticBody(this, shape1_1);
        platform1_1.setPosition(new Vec2(11, -5));

        Shape shape2 = new BoxShape(5, 0.5f);
        StaticBody top_platform = new StaticBody(this, shape2);
        top_platform.setPosition(new Vec2(1, 0));

    }

    /**
     * Override method from GameLevel
     * <p>
     * Populating the game with dynamic bodies of player and bots
     *
     * @param  game Game class
     * @return
     */
    @Override
    public void populate(Game game) {
        super.populate(game);

        getPlayer().setPosition(new Vec2(5,10));

        for (int i=0; i<3; i++) {
            setBot(this);
        }

        displayBot();
    }

    /**
     * Override method from GameLevel
     * <p>
     * To complete this level, player needs to get more than or equal to 2 score.
     *
     * @param
     * @return boolean
     */
    @Override
    public boolean isComplete() {
        if (getGame().getLevel().getPlayer().getScore() >= 2) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Override method from GameLevel
     * <p>
     * returns string of this class name
     *
     * @param
     * @return String of class name
     */
    @Override
    public String getLevelName() {
        return "LevelOne";
    }
}

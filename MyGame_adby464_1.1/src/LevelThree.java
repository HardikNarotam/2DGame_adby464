import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

public class LevelThree extends GameLevel {

    public LevelThree(Game game) {
        super(game);

        // making the ground
        Shape shape = new BoxShape(4, 0.5f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0, -10));

        // making the platforms
        Shape shape1 = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(this, shape1);
        platform1.setPosition(new Vec2(-12, -5));

        Shape shape1_1 = new BoxShape(3, 0.5f);
        StaticBody platform1_1 = new StaticBody(this, shape1_1);
        platform1_1.setPosition(new Vec2(11, -5));

        Shape shape2 = new BoxShape(4, 0.5f);
        StaticBody top_platform = new StaticBody(this, shape2);
        top_platform.setPosition(new Vec2(0, 0));
    }

    @Override
    public void populate(Game game) {
        super.populate(game);

        getPlayer().setPosition(new Vec2(7,10));

        for (int i=0; i<5; i++) {
            setBot(this);
        }

        displayBot();
    }

    @Override
    public boolean isComplete() {
        if (getGame().getLevel().getPlayer().getScore() >= 4)
            return true;
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "LevelThree";
    }

}

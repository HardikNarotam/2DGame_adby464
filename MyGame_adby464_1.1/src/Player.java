import city.cs.engine.*;

public class Player extends Walker {

    private static final Shape playerShape = new PolygonShape(-0.88f,2.18f, -1.15f,-1.88f, 0.56f,-1.97f, 0.77f,2.23f, -0.79f,2.24f);

    private static BodyImage image = new BodyImage("data/player_stand.png", 5f);

    private Game game;

    private static int score;

    private static int fastestTime;

    public Player(Game game, GameLevel level) {
        super(level, playerShape);
        this.game = game;
        addImage(image);
    }

    public void changeImage(String filename) {
        removeAllImages();
        image = new BodyImage(filename, 5f);
        addImage(image);
    }

    public void addScore() {
        score++;
        System.out.println("Score:"+score);
    }

    public int getScore() {
        return score;
    }

    public void resetScore() {
        score = 0;
    }

    public void setScore(int s) {
        score = s;
    }

    public void setFastestTime(int time) {
        fastestTime = Integer.valueOf(game.getControlPanel().getCount()[0]);
    }

    public int getFastestTime() {
        return fastestTime;
    }

}

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {

    public static void save(Game game, GameLevel level, String fileName)
            throws IOException
    {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevelName() + "\n");

            for (StaticBody body : level.getStaticBodies()) {
                // no static body that are worth saving
            }

            // saving dynamic body positions and relevant information
            for (DynamicBody body : level.getDynamicBodies()) {
                if (body instanceof Player) {
                    writer.write("Player"+ "," + body.getPosition().x + "," + body.getPosition().y + "," + ((Player) body).getScore() + "\n");
                } else if (body instanceof Bot) {
                    writer.write("Bot" + "," + body.getPosition().x + "," + body.getPosition().y + "," + ((Bot) body).getHealth() + "\n");
                }
            }

            writer.write(String.valueOf(game.getControlPanel().getCount()[0]));

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static GameLevel load(Game game, String fileName)
            throws IOException
    {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            // getting save level
            GameLevel level = null;
            if (line.equals("LevelOne")) {
                level = new LevelOne(game);
            } else if (line.equals("LevelTwo")) {
                level = new LevelTwo(game);
            } else if (line.equals("LevelThree")) {
                level = new LevelThree(game);
            }


            // getting saved positions
            line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");

                if (tokens[0].equals("Player")) {
                    Player player = new Player(game, level);
                    level.setPlayer(player);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    player.setPosition(new Vec2(x,y));
                    int score = Integer.parseInt(tokens[3]);
                    player.setScore(score);
                } else if (tokens[0].equals("Bot")) {
                    Bot bot = new Bot(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    bot.setPosition(new Vec2(x,y));
                    BotCollisions collisions = new BotCollisions(bot, level, game);
                    bot.addCollisionListener(collisions);
                    int health = Integer.parseInt(tokens[3]);
                    bot.setHealth(health);
                    level.getBotList().add(bot);
                } else {
                    game.getControlPanel().setCount(Integer.valueOf(tokens[0]));
                }

                line = reader.readLine();
            }

            return level;

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

}

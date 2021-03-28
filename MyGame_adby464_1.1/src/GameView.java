import city.cs.engine.UserView;
import city.cs.engine.World;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GameView extends UserView {

    private Image background;

    private Game game;

    public GameView(World w, Game game, int width, int height) {
        super(w, width, height);
        background = new ImageIcon("data/background.png").getImage();
        this.game = game;
    }

    @Override
    protected void paintBackground(Graphics2D graphics2D) {
        graphics2D.drawImage(background, 0, 0, 700, 500,this);
    }

    @Override
    protected void paintForeground(Graphics2D graphics2D) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("data/PressStart2P-Regular.ttf")).deriveFont(Font.BOLD, 24);
            ge.registerFont(font);
            graphics2D.setFont(font);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        graphics2D.drawString("Score: " + game.getLevel().getPlayer().getScore(), 10, 30);

        int i = 0;
        int x = 1;
        for (Bot bot : game.getLevel().getBotList()) {
            graphics2D.setFont(new Font("TimesRoman", Font.BOLD, 12));
            graphics2D.drawString("Bot"+ x , 10, 45+i);
            graphics2D.fillRect(10, 50+i, bot.getHealth()*10, 4); // health bar
            i = i +20;
            x += 1;
        }

    }

}

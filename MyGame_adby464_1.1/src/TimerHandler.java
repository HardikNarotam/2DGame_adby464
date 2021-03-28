//A separate class to treat the timer event
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerHandler implements ActionListener {

    private Game game;

    public TimerHandler (Game game) {
        this.game = game;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        game.getView().setEnabled(false);
        game.getView().removeMouseListener(game.getHandler());
        JOptionPane.showMessageDialog(null,"Time out!", "Game over", 1);
    }

}
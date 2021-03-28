import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ControlPanel {

    private JPanel mainPanel;
    private JButton playButton;
    private JButton restartButton;
    private JButton pauseButton;
    private JButton exitButton;
    private JButton nextButton;
    private JButton howToPlayButton;
    private JButton saveButton;
    private JButton loadButton;
    private JLabel label;
    private JLabel fastedTimeLabel;
    private JLabel playerName;

    private Game game;

    final int[] count = {1};

    public ControlPanel(Game game) {

        this.game = game;
        saveButton.setEnabled(false);
        loadButton.setEnabled(false);

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getView().isEnabled()) {
                    game.getView().setEnabled(false);
                    game.getView().removeMouseListener(game.getHandler());
                    pauseButton.setText("Unpause");
                } else {
                    game.getView().setEnabled(true);
                    game.getView().addMouseListener(game.getHandler());
                    pauseButton.setText("Pause");
                }
            }
        });

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getView().addMouseListener(game.getHandler());
                game.start();
                System.out.println("Welcome to Laser Shooter.");
                System.out.println("How to Play? Left Click on left to shoot left and Right to shoot right. Use WASD to move.");
                System.out.println("Bot Health: 10");
                System.out.println("Level 1");
                playButton.setEnabled(false);
                saveButton.setEnabled(true);
                loadButton.setEnabled(true);

                File f = new File("data/fastestTime.txt");
                if(f.exists() && !f.isDirectory()) {
                    FileReader fr = null;
                    BufferedReader reader = null;
                    try {
                        System.out.println("Reading " + "data/fastestTime.txt" + " ...");
                        fr = new FileReader(f);
                        reader = new BufferedReader(fr);
                        try {
                            String line = reader.readLine();
                            String[] tokens = line.split(",");
                            int fastestTime = Integer.parseInt(tokens[1]);
                            game.getLevel().getPlayer().setFastestTime(fastestTime);
                            playerName.setText(tokens[0]);
                            fastedTimeLabel.setText(tokens[1]);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    } finally {
                        if (reader != null) {
                            try {
                                reader.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        if (fr != null) {
                            try {
                                fr.close();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                    }
                }

                // start timer
                Timer timer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        label.setText(String.valueOf(count[0]));
                        count[0]++;
                    }
                });
                timer.start();

            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // restart the game , back to level one
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.getLevel().isComplete()) {
                    try {
                        game.nextLevel();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null,"Level is not complete!", "Error",2);
                }
            }
        });

        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("<html><center>WASD to move your character.<br><br><br>" +
                        "Use Left-Click on left side of the screen to shoot left and right side of the screen to shoot right.<br><br><br>" +
                        "Upon level completion, click on Next button to proceed to next level.");
                label.setHorizontalAlignment(SwingConstants.CENTER);
                JOptionPane.showMessageDialog(null, label, "How To Play?", 3);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // run save methods
                try {
                    GameSaverLoader.save(game, game.getLevel(), "data/save.txt");
                    JOptionPane.showMessageDialog(null,"Game Saved!", "Saving",1);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // run load methods
                try {
                    GameLevel level = GameSaverLoader.load(game, "data/save.txt");
                    game.setLevel(level);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Yes", "No" };
                int input = JOptionPane.showOptionDialog(null, "Are you sure?","Exit",
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
                        null, options, null);
                if (input == JOptionPane.YES_OPTION){
                    System.exit(0);
                }

            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public int[] getCount() {
        return count;
    }

    public void setCount(int time) {
        count[0] = time;
    }

    public String getFastestTime() {
        return fastedTimeLabel.getText();
    }
}

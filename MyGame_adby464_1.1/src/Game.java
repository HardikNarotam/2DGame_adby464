import city.cs.engine.*;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.*;

/**
 * @author      Hardik, Narotam, hardik.narotam@city.ac.uk
 * @version     1.3
 * @since       1
 */
public class Game {

    private GameLevel level;
    private GameView view;
    private final JFrame frame;
    private PlayerController controller;
    private MouseHandler handler;
    private MouseWheelHandler wheelHandler;
    private SoundClip gameMusic;
    private ControlPanel controlPanel;

    /**
     * Game class constructor
     * <p>
     * this method creates a game.
     *
     * @param
     * @return
     */
    public Game() {

        try {
            gameMusic = new SoundClip("data/DST-Xboss.mp3");   // Open an audio input stream
            gameMusic.loop();  // Set it to continous playback (looping)
        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }

        // making the world
        level = new LevelOne(this);

        // making a view
        view = new GameView(level, this, 700, 500);
        frame = new JFrame("Shooter");
        frame.add(view);

        level.populate(this);

        controlPanel = new ControlPanel(this);
        frame.add(controlPanel.getMainPanel(), BorderLayout.WEST);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);

        // debugger view
        JFrame debugView = new DebugViewer(level, 700, 500);


        // mouse focus
        view.addMouseListener(new GiveFocus(view));

        // mouse scroller

        level.addStepListener(new Tracker(view, level.getPlayer()));

        controller = new PlayerController(level.getPlayer());
        view.addKeyListener(controller);

        handler = new MouseHandler(level, view);
        wheelHandler = new MouseWheelHandler(view);
        view.addMouseWheelListener(wheelHandler);
    }

    /**
     * Allows to proceed to next level
     * <p>
     * This method uses if statement and recalls all the neccesary class to proceed to next level.
     * After completing the game, InputDialong is displayed congratulating user and asking to input their name.
     * Finally exit of the system. (closes the game)
     *
     * @param
     * @return
     */
    public void nextLevel() throws IOException {
        if(level instanceof LevelOne) {
            System.out.println("Well done! Level 1 complete.");
            System.out.println("Level 2");
            level.stop();
            level = new LevelTwo(this);
            level.populate(this);
            level.getPlayer().resetScore();
            view.setWorld(level);
            controller.updatePlayer(level.getPlayer());
            handler.updateHandler(level, view);
            level.addStepListener(new Tracker(view, level.getPlayer()));
            level.start();
        }
        else if(level instanceof LevelTwo) {
            System.out.println("Well done! Level 2 complete.");
            System.out.println("Level 3");
            level.stop();
            level = new LevelThree(this);
            level.populate(this);
            level.getPlayer().resetScore();
            view.setWorld(level);
            controller.updatePlayer(level.getPlayer());
            handler.updateHandler(level, view);
            level.addStepListener(new Tracker(view, level.getPlayer()));
            level.start();
        }
        else if (level instanceof LevelThree) {
            String yourName = JOptionPane.showInputDialog("Well done! You completed the game.", "Enter Your name");
            level.getPlayer().setFastestTime(Integer.valueOf(controlPanel.getCount()[0]));
            int time = Integer.valueOf(controlPanel.getCount()[0]);
            if (Integer.parseInt(controlPanel.getFastestTime()) > time) {
                FileWriter writer = null;
                try {
                    writer = new FileWriter("data/fastestTime.txt", false);
                    writer.write(yourName + "," + level.getPlayer().getFastestTime() + "\n");
                } finally {
                    if (writer != null) {
                        writer.close();
                    }
                }
            }
            System.exit(1);
        }
    }

    /**
     * void method to set level
     * <p>
     * Used to set a specific level.
     * Used when user clicks on load Button on the GUI menu, to load the saved level in the save.txt
     *
     * @param  level level saved in the file
     * @return
     */
    public void setLevel(GameLevel level) {
        this.level.stop();
        this.level = level;
        view.setWorld(this.level);
        controller.updatePlayer(this.level.getPlayer());
        handler.updateHandler(this.level, view);
        this.level.addStepListener(new Tracker(view, this.level.getPlayer()));
        this.level.start();
    }

    /**
     * getter method of view
     * <p>
     * returns the GameView
     *
     * @param
     * @return GameView
     */
    public GameView getView() {
        return view;
    }

    /**
     * getter method of level
     * <p>
     * returns current level of the game
     *
     * @param
     * @return GameLevel
     */
    public GameLevel getLevel() {
        return level;
    }

    /**
     * getter method of MouseHandler
     * <p>
     * returns the MouseHandler object
     *
     * @param
     * @return MouseHandler
     */
    public MouseHandler getHandler() {
        return handler;
    }

    /**
     * getter method of JFrame
     * <p>
     * returns the JFrame of the game
     *
     * @param
     * @return JFrame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * getter method of ControlPanel
     * <p>
     * returns controlPanel from the game
     *
     * @param
     * @return ControlPanel
     */
    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    /**
     * Method to start the game
     * <p>
     * Starts the game at LevelOne
     *
     * @param
     * @return
     */
    public void start() {
        // start our game world simulation!
        level.start();
    }

    /**
     * Initials the game
     * <p>
     * runs the game
     *
     * @param
     * @return
     */
    public static void main(String[] args) {
        new Game();
    }
}
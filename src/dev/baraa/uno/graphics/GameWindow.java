package dev.baraa.uno.graphics;

import dev.baraa.uno.exceptions.graphics.GameWindowHasBeenInitializedException;
import dev.baraa.uno.exceptions.graphics.GameWindowNotInitializedException;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private static GameWindow gameWindow;

    /**
     * Stores the previous size and previous location of the window.
     * Whenever the user goes to windowed mode, this will be accessed to position and size the window to the preferred user size and position.
     */
    private Dimension prevSize;
    private Point prevLocation;


    private GameWindow() {
        setMinimumSize(new Dimension(700, 600));
        setLocationRelativeTo(null);
        setUndecorated(true);

        /*
         * Initializes the previous size and previous location.
         */
        pack();
        prevSize = getSize();
        prevLocation = getLocation();

        /*
         * Sets the JFrame to be maximized.
         * This is how the game is played by default. The player can modify this in the settings at any time.
         */
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }


    /**
     * Creates the game window object.
     *
     * @throws GameWindowHasBeenInitializedException thrown when the game window has been already initialized before
     */
    public static void initialize() throws GameWindowHasBeenInitializedException {
        if (gameWindow != null)
            throw new GameWindowHasBeenInitializedException();

        gameWindow = new GameWindow();
    }

    /**
     * Displays the game window.
     *
     * @throws GameWindowNotInitializedException thrown when the game window has not been initialized.
     */
    public static void start() throws GameWindowNotInitializedException {
        if (gameWindow == null)
            throw new GameWindowNotInitializedException();

        gameWindow.setVisible(true);
    }
}

package dev.baraa.uno.graphics;

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
        setMinimumSize(new Dimension(600, 600));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        setExtendedState(MAXIMIZED_BOTH);
    }

    /**
     * Creates the game window object if not created.
     * In case something goes wrong. this method will make sure it displays the window just once.
     */
    public static void start() {
        if (gameWindow != null)
            return;

        gameWindow = new GameWindow();
        gameWindow.setVisible(true);
    }
}

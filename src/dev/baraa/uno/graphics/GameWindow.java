package dev.baraa.uno.graphics;

import dev.baraa.uno.Uno;
import dev.baraa.uno.exceptions.graphics.GameWindowHasBeenInitializedException;
import dev.baraa.uno.exceptions.graphics.GameWindowNotInitializedException;
import dev.baraa.uno.game.Card;

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

    private CardsPanel[] cardsPanel;
    private PlacedCards placedCards;

    private GameWindow() {
        setMinimumSize(new Dimension(700, 600));
        setLocationRelativeTo(null);
        //setUndecorated(true);

        JPanel gameAction = new JPanel();
        gameAction.setOpaque(false);
        gameAction.setLayout(new BorderLayout());

        cardsPanel = new CardsPanel[2];

        CardsPanel player0 = new CardsPanel(Uno.getPlayerCards(0), true);
        gameAction.add(player0, BorderLayout.SOUTH);
        cardsPanel[0] = player0;

        CardsPanel player2 = new CardsPanel(Uno.getPlayerCards(1), false);
        gameAction.add(player2, BorderLayout.NORTH);
        cardsPanel[1] = player2;

        placedCards = new PlacedCards();
        gameAction.add(placedCards);

        add(gameAction);

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

    public static void updateCards() {
        for (CardsPanel cardsPanel : gameWindow.cardsPanel) {
            cardsPanel.update();
        }
    }

    public static void updateTable(Card card) {
        gameWindow.placedCards.update(card);
    }
}

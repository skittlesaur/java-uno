package dev.baraa.uno;

import dev.baraa.uno.exceptions.graphics.GameWindowHasBeenInitializedException;
import dev.baraa.uno.exceptions.graphics.GameWindowNotInitializedException;
import dev.baraa.uno.exceptions.graphics.GraphicsException;
import dev.baraa.uno.game.Game;
import dev.baraa.uno.graphics.GameWindow;

import java.util.ArrayList;

public class Uno {

    private static Game game;

    public static void main(String[] args) throws GraphicsException {
        game = new Game();
        initialize();
        run();
    }

    private static void initialize() throws GameWindowHasBeenInitializedException {
        GameWindow.initialize();
    }

    private static void run() throws GameWindowNotInitializedException {
        //GameWindow.start();
    }

    public static ArrayList getPlayerCards(int playerIndex) {
        return game.getPlayerCards(playerIndex);
    }

}

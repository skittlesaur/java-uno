package dev.baraa.uno;

import dev.baraa.uno.game.Game;

import java.util.ArrayList;

public class Controller {

    private static Game game;

    public static ArrayList getPlayerCards(int playerIndex) {
        return game.getPlayerCards(playerIndex);
    }

    public static void initialize() {
        game = new Game();
    }
}

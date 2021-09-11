package dev.baraa.uno;

import dev.baraa.uno.exceptions.game.PlayerTurnException;
import dev.baraa.uno.exceptions.game.IllegalCardException;
import dev.baraa.uno.exceptions.graphics.GameWindowHasBeenInitializedException;
import dev.baraa.uno.exceptions.graphics.GameWindowNotInitializedException;
import dev.baraa.uno.exceptions.graphics.GraphicsException;
import dev.baraa.uno.game.*;
import dev.baraa.uno.graphics.ColorEvent;
import dev.baraa.uno.graphics.ColorPicker;
import dev.baraa.uno.graphics.GameWindow;
import dev.baraa.uno.graphics.ImageProvider;

import java.io.IOException;
import java.util.ArrayList;

public class Uno {

    private static Game game;

    public static void main(String[] args) throws GraphicsException, IOException {
        initialize();
        run();
    }

    private static void initialize() throws GameWindowHasBeenInitializedException, IOException {
        ImageProvider.load();
        game = new Game();
        GameWindow.initialize();
        GameWindow.updateTable(game.getLastPlayedCard());
    }

    private static void run() throws GameWindowNotInitializedException {
        GameWindow.start();
    }

    public static ArrayList getPlayerCards(int playerIndex) {
        return game.getPlayerCards(playerIndex);
    }

    public static void play(TablePlayer player, Card card) {

        if (card.getColor() == CardColor.UNIVERSAL) {
            ColorPicker colorPicker = new ColorPicker();
            colorPicker.setColorEvent(cardColor -> {
                card.setColor(cardColor);
            });
            colorPicker.display();
        }

        try {
            game.play(player, card);
            GameWindow.updateCards();
            GameWindow.updateTable(card);
            game.nextTurn();
        } catch (PlayerTurnException e) {
            System.out.println(e.getMessage());
        } catch (IllegalCardException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Card drawCard(TablePlayer player) {
        try {
            return game.drawCard(player);
        } catch (PlayerTurnException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static Card getLastPlayedCard() {
        return game.getLastPlayedCard();
    }
}

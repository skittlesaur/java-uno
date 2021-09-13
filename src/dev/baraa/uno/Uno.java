package dev.baraa.uno;

import dev.baraa.uno.exceptions.game.PlayerTurnException;
import dev.baraa.uno.exceptions.game.IllegalCardException;
import dev.baraa.uno.exceptions.graphics.GameWindowHasBeenInitializedException;
import dev.baraa.uno.exceptions.graphics.GameWindowNotInitializedException;
import dev.baraa.uno.exceptions.graphics.GraphicsException;
import dev.baraa.uno.game.*;
import dev.baraa.uno.graphics.*;

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

        System.out.println(player + " PLAYED " + card);

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
        System.out.println("DRAW: " + player);
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

    public static void endGame(TablePlayer winner) {
        System.out.println(winner + " WON!");
        System.exit(1);
    }

    public static void drawCard() {
        Player player = game.getPlayer();

        Card card = drawCard(player);
        if (card == null)
            return;

        if (card.isPlayable(game.getLastPlayedCard())) {
            PlayableDrawnCard playableDrawnCard = new PlayableDrawnCard(card);
            playableDrawnCard.setPlayableCardEvent(option -> {
                switch (option) {
                    case PLAY -> play(player, card);
                    case KEEP -> player.addCard(card);
                }
                playableDrawnCard.dispose();
            });
            playableDrawnCard.setActive();
        } else {
            player.addCard(card);
        }

        GameWindow.updateCards();
        game.nextTurn();
    }
}

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

    /**
     * Loads the game assets, window, and objects required for the game to function.
     *
     * @throws GameWindowHasBeenInitializedException If the game has been already initialized before, this exception is thrown.
     * @throws IOException                           If something goes wrong with the image loading. This exception will be thrown.
     */
    private static void initialize() throws GameWindowHasBeenInitializedException, IOException {
        ImageProvider.load();
        game = new Game();
        GameWindow.initialize();
        GameWindow.updateTable(game.getLastPlayedCard());
    }

    /**
     * Starts the actual game.
     *
     * @throws GameWindowNotInitializedException If the game window has been not initialized correctly, this exception will get thrown.
     */
    private static void run() throws GameWindowNotInitializedException {
        GameWindow.start();
    }

    public static ArrayList<Card> getPlayerCards(int playerIndex) {
        return game.getPlayerCards(playerIndex);
    }

    public static void play(TablePlayer player, Card card) {
        if (card.getColor() == CardColor.UNIVERSAL) {
            ColorPicker colorPicker = new ColorPicker();
            colorPicker.setColorEvent(card::setColor);
            colorPicker.display();
        }

        try {
            game.play(player, card);
            game.updateTurn();
            GameWindow.updateCards();
            GameWindow.updateTable(card);
            game.nextTurn();
        } catch (PlayerTurnException e) {
            //TODO: Handle exception
        } catch (IllegalCardException e) {
            //TODO: Handle exception
        }
    }

    /**
     * Draws a card for a specific player.
     *
     * @param player the player the card is drawn for
     * @return a randomly generated card from the deck
     */
    public static Card drawCard(TablePlayer player) {
        try {
            return game.drawCard(player);
        } catch (PlayerTurnException e) {
            //TODO: Handle exception
        }
        return null;
    }


    //TODO:

    /**
     * Draws a card for the local player.
     */
    public static void drawCard() {
        Player player = game.getPlayer();

        Card card = drawCard(player);

        /*
        If the card is null, the player attempted to draw a card during another player's turn.
        Nothing occurs with the card in this scenario and the method ends.
         */

        if (card == null)
            return;


        /*
        If the drawn card is playable on the last played card, the player has the choice of playing or keeping it.
        This will display a JDialog window to the player, from which he may make his selection.

        The selection is made with an enum class of CardOptions. If the player picks to PLAY, the `play` method is being called and the game continues.
        If the player chooses to KEEP, the `skipTurn` method is called.
         */
        if (card.isPlayable(game.getLastPlayedCard())) {
            PlayableDrawnCard playableDrawnCard = new PlayableDrawnCard(card);

            playableDrawnCard.setPlayableCardEvent(option -> {
                playableDrawnCard.dispose();

                if (option == CardOptions.PLAY) {
                    play(player, card);
                } else {
                    player.addCard(card);
                    GameWindow.updateCards();
                    game.skipTurn();
                }
            });
            playableDrawnCard.setActive();
        } else {
            player.addCard(card);
            GameWindow.updateCards();
            game.skipTurn();
        }
    }

    /**
     * @param winner The winning player
     */
    public static void endGame(TablePlayer winner) {
        System.out.println(winner + " WON!");
        System.exit(1);
    }

    /**
     * @return The last played card in the game
     */
    public static Card getLastPlayedCard() {
        return game.getLastPlayedCard();
    }

    /**
     * Updates the players cards
     */
    public static void updateCards() {
        GameWindow.updateCards();
    }
}

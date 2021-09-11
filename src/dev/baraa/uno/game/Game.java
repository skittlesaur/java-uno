package dev.baraa.uno.game;

import dev.baraa.uno.Uno;
import dev.baraa.uno.exceptions.game.PlayerTurnException;
import dev.baraa.uno.exceptions.game.IllegalCardException;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private TablePlayer[] gamePlayers;
    private int turn;

    private Player player;
    private Card lastPlayedCard;

    public Game() {
        gamePlayers = new TablePlayer[4];

        player = new Player(true);
        gamePlayers[0] = player;

        gamePlayers[1] = new Bot();
        gamePlayers[2] = new Bot();
        gamePlayers[3] = new Bot();

        for (TablePlayer tablePlayer : gamePlayers)
            for (int i = 0; i < 7; i++)
                tablePlayer.addCard(getCard());

        do
            lastPlayedCard = getCard();
        while (lastPlayedCard.isSpecial());

        System.out.println(lastPlayedCard);

        // turn = new Random().nextInt(4);
    }

    private Card getCard() {
        Random random = new Random();

        int randomValue = random.nextInt(14);

        return new Card(randomValue);
    }

    public ArrayList getPlayerCards(int playerIndex) {
        return gamePlayers[playerIndex].getCards();
    }

    public void play(TablePlayer player, Card card) throws PlayerTurnException, IllegalCardException {

        /**
         * If not the player turn, throw an exception.
         */
        if (getIndex(player) != turn)
            throw new PlayerTurnException();

        /**
         * checks if the card is not a universal card.
         * if so, it checks if it's playable by checking the color of last played card and the new card.
         */
        CardColor cardColor = card.getColor();
        if (cardColor != CardColor.UNIVERSAL)
            if (cardColor != lastPlayedCard.getColor() && card.getValue() != lastPlayedCard.getValue())
                throw new IllegalCardException(card, lastPlayedCard);

        player.removeCard(card);
        lastPlayedCard = card;

        nextTurn();
    }

    private void nextTurn() {
        turn = (turn + 1) % 4;
        System.out.println(turn);
        TablePlayer currentPlayer = gamePlayers[turn];

        if (currentPlayer instanceof Bot) {
            Card botCard = ((Bot) currentPlayer).play(lastPlayedCard);
            Uno.play(currentPlayer, botCard);
        }
    }

    /**
     * @param player
     * @return The index of the player in the gamePlayers array.
     */
    private int getIndex(TablePlayer player) {
        for (int i = 0; i < gamePlayers.length; i++)
            if (gamePlayers[i].equals(player))
                return i;
        return -1;
    }
}

package dev.baraa.uno.game;

import dev.baraa.uno.Uno;
import dev.baraa.uno.exceptions.game.PlayerTurnException;
import dev.baraa.uno.exceptions.game.IllegalCardException;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private final TablePlayer[] gamePlayers;
    private int turn;

    private final Player player;
    private Card lastPlayedCard;

    private int direction = 1;

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

        // turn = new Random().nextInt(4);
    }

    public Card getCard() {
        Random random = new Random();
        int randomValue = random.nextInt(14);
        return new Card(randomValue);
    }

    public ArrayList getPlayerCards(int playerIndex) {
        return gamePlayers[playerIndex].getCards();
    }

    public void play(TablePlayer player, Card card) throws PlayerTurnException, IllegalCardException {

        /*
          If not the player turn, throw an exception.
         */
        if (getIndex(player) != turn)
            throw new PlayerTurnException();

        /*
          checks if the card is not a universal card.
          if so, it checks if it's playable by checking the color of last played card and the new card.
         */
        CardColor cardColor = card.getColor();
        if (cardColor != CardColor.UNIVERSAL)
            if (cardColor != lastPlayedCard.getColor() && card.getValue() != lastPlayedCard.getValue())
                throw new IllegalCardException(card, lastPlayedCard);

        //TODO: remove this
        if (card.getColor() == CardColor.UNIVERSAL)
            card.setColor(CardColor.RED);

        player.removeCard(card);
        lastPlayedCard = card;
    }

    public void nextTurn() {

        int changeVal = 1 * direction;

        if (lastPlayedCard.isSpecial()) {
            if (lastPlayedCard.getSpecialMove() == SpecialMove.SKIP) {
                changeVal = 2 * direction;
            } else if (lastPlayedCard.getSpecialMove() == SpecialMove.REVERSE) {
                direction *= -1;
            }
        }

        turn = (turn + changeVal) % 4;
        if (turn < 0)
            turn = 3;
        TablePlayer currentPlayer = gamePlayers[turn];

        if (lastPlayedCard.isSpecial()) {
            if (lastPlayedCard.getSpecialMove() == SpecialMove.PLUS_TWO) {
                plusCards(currentPlayer, 2);
            } else if (lastPlayedCard.getSpecialMove() == SpecialMove.PLUS_FOUR) {
                plusCards(currentPlayer, 4);
            }
        }

        if (currentPlayer instanceof Bot) {
            Card botCard = ((Bot) currentPlayer).play(lastPlayedCard);

            if (botCard != null && botCard.isPlayable(lastPlayedCard))
                Uno.play(currentPlayer, botCard);
            else
                nextTurn();
        }
    }

    private void plusCards(TablePlayer tablePlayer, int amount) {
        for (int i = 0; i < amount; i++) {
            tablePlayer.addCard(getCard());
        }
    }

    public Card drawCard(TablePlayer player) throws PlayerTurnException {

        if (getIndex(player) != turn)
            throw new PlayerTurnException();

        return getCard();
    }

    /**
     * @param player player
     * @return The index of the player in the gamePlayers array.
     */
    private int getIndex(TablePlayer player) {
        for (int i = 0; i < gamePlayers.length; i++)
            if (gamePlayers[i].equals(player))
                return i;
        return -1;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

}

package dev.baraa.uno.game;

import dev.baraa.uno.Uno;
import dev.baraa.uno.exceptions.game.PlayerTurnException;
import dev.baraa.uno.exceptions.game.IllegalCardException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Game {

    private final TablePlayer[] gamePlayers;
    private int turn;

    private final Player player;
    private Card lastPlayedCard;

    private int direction = 1;
    private int changeVal = 1;

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

        gamePlayers[turn].setPlayerTurn(true);
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
          checks if the card is not special,
          if so, checks if the card has the same value of the last played card or same color.
         */
        if (!card.isSpecial()) {
            if (card.getColor() != lastPlayedCard.getColor()
                    && card.getValue() != lastPlayedCard.getValue())
                throw new IllegalCardException(card, lastPlayedCard);
        } else {
            /*
             if the card is special,
             check if the card special move is CHANGE_COLOR or PLUS_FOUR, if so those cards can be played fine.
             if not, compare the value and color of the card and last played card.
             */
            if (card.getSpecialMove() != SpecialMove.CHANGE_COLOR
                    && card.getSpecialMove() != SpecialMove.PLUS_FOUR)
                if (card.getColor() != lastPlayedCard.getColor()
                        && card.getValue() != lastPlayedCard.getValue())
                    throw new IllegalCardException(card, lastPlayedCard);
        }

        player.setPlayerTurn(false);
        player.removeCard(card);
        lastPlayedCard = card;

        if (player.getCards().size() == 1 && !player.isUno())
            unoPenalty(player);

        if (player.getCards().size() == 0) {
            System.out.println("WINNER " + player);
        }

        if (player.getCards().size() > 1)
            player.setUno(false);


        if (lastPlayedCard.isSpecial()) {
            if (lastPlayedCard.getSpecialMove() == SpecialMove.SKIP) {
                changeVal = 2;
            } else if (lastPlayedCard.getSpecialMove() == SpecialMove.REVERSE) {
                direction *= -1;
            } else if (lastPlayedCard.getSpecialMove() == SpecialMove.PLUS_TWO) {
                TablePlayer affectedPlayer = gamePlayers[getNextTurn()];
                plusCards(affectedPlayer, 2);
                changeVal = 2;
            } else if (lastPlayedCard.getSpecialMove() == SpecialMove.PLUS_FOUR) {
                TablePlayer affectedPlayer = gamePlayers[getNextTurn()];
                plusCards(affectedPlayer, 4);
                changeVal = 2;
            }
        } else {
            changeVal = 1;
        }

        gamePlayers[getNextTurn()].setPlayerTurn(true);
    }

    private void unoPenalty(TablePlayer player) {
        plusCards(player, 2);
    }

    public void nextTurn() {

        turn = getNextTurn();
        TablePlayer currentPlayer = gamePlayers[turn];

        if (currentPlayer instanceof Bot) {
            botTurn(currentPlayer);
        }
    }

    private int getNextTurn() {
        int val = (turn + changeVal * direction) % 4;
        if (val < 0)
            val = 4 + val;
        return val;
    }

    private void botTurn(TablePlayer currentPlayer) {
        int randomDelay = (int) (Math.random() * (1000) + 800);

        Timer timer = new Timer(randomDelay, e -> {
            Card botCard = ((Bot) currentPlayer).play(lastPlayedCard);

            if (botCard != null)
                Uno.play(currentPlayer, botCard);
            else
                nextTurn();
        });
        timer.setRepeats(false);
        timer.start();
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

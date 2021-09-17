package dev.baraa.uno.game;

import dev.baraa.uno.Uno;
import dev.baraa.uno.exceptions.game.PlayerTurnException;
import dev.baraa.uno.exceptions.game.IllegalCardException;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {

    private final TablePlayer[] gamePlayers;
    private int turn;

    private final Player player;
    private Card lastPlayedCard;

    private int direction = 1;
    private int changeVal = 1;

    public Game() {
        gamePlayers = new TablePlayer[2];

        player = new Player(true);
        gamePlayers[0] = player;

        gamePlayers[1] = new Bot();

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
        int randomValue = random.nextInt(15);
        return new Card(randomValue);
    }

    public ArrayList<Card> getPlayerCards(int playerIndex) {
        return gamePlayers[playerIndex].getCards();
    }

    public void play(TablePlayer player, Card card) throws PlayerTurnException, IllegalCardException {

        /*
          If not the player turn, throw an exception.
         */
        if (!player.isPlayerTurn())
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

        player.removeCard(card);
        lastPlayedCard = card;

        if (player.getCards().size() == 1 && !player.isUno())
            //unoPenalty(player);
            player.setUno(true);

        if (player.getCards().size() == 0) {
            Uno.endGame(player);
        }

        if (player.getCards().size() > 1)
            player.setUno(false);


        if (lastPlayedCard.isSpecial())
            switch (lastPlayedCard.getSpecialMove()) {
                case SKIP -> changeVal = 2;
                case REVERSE -> direction *= -1;
                case PLUS_TWO -> {
                    changeVal = 1;
                    TablePlayer affectedPlayer = gamePlayers[getNextTurn()];
                    plusCards(affectedPlayer, 2);
                    changeVal = 2;
                }
                case CHANGE_COLOR -> changeVal = 1;
                case PLUS_FOUR -> {
                    changeVal = 1;
                    TablePlayer affectedPlayer = gamePlayers[getNextTurn()];
                    plusCards(affectedPlayer, 4);
                    changeVal = 2;
                }
            }
        else
            changeVal = 1;
    }

    private void unoPenalty(TablePlayer player) {
        plusCards(player, 2);
    }

    public void updateTurn() {
        gamePlayers[turn].setPlayerTurn(false);
        turn = getNextTurn();
        gamePlayers[turn].setPlayerTurn(true);
    }

    public void nextTurn() {
        TablePlayer currentPlayer = gamePlayers[turn];

        if (currentPlayer instanceof Bot) {
            botTurn(currentPlayer);
        }
    }

    private int getNextTurn() {
        int val = (turn + changeVal * direction) % gamePlayers.length;
        return Math.abs(val);
    }

    private void botTurn(TablePlayer currentPlayer) {
        int randomDelay = (int) (Math.random() * (1000) + 800);

        Timer timer = new Timer(randomDelay, e -> {
            Card botCard = ((Bot) currentPlayer).play(lastPlayedCard);

            if (botCard != null)
                Uno.play(currentPlayer, botCard);
            else
                skipTurn();
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

    public Player getPlayer() {
        return player;
    }

    public void skipTurn() {
        changeVal = 1;
        updateTurn();
        Uno.updateCards();
        nextTurn();
    }

    public int getTurn() {
        return turn;
    }

    public TablePlayer[] getGamePlayers() {
        return gamePlayers;
    }
}

package dev.baraa.uno.game;

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
        while (lastPlayedCard.getColor() != CardColor.UNIVERSAL);

        turn = new Random().nextInt(4);
    }

    private Card getCard() {
        Random random = new Random();

        int randomValue = random.nextInt(14);

        return new Card(randomValue);
    }

    public ArrayList getPlayerCards(int playerIndex) {
        return gamePlayers[playerIndex].getCards();
    }
}

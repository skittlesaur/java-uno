package dev.baraa.uno.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends TablePlayer {

    private static int botCount;
    private String botName;

    public Bot() {
        botCount++;
        botName = "Bot" + botCount;
    }

    public Card play(Card lastPlayedCard) {
        List<Card> possibleCards = new ArrayList<>();

        /**
         * gets the possible cards to play
         */
        for (Card card : getCards()) {
            if (card.getValue() == lastPlayedCard.getValue()
                    || card.getColor() == lastPlayedCard.getColor()
                    || card.getColor() == CardColor.UNIVERSAL)
                possibleCards.add(card);
        }

        if (possibleCards.size() == 0)
            System.out.println("oops");

        return possibleCards.get(new Random().nextInt(possibleCards.size()));
    }

}

package dev.baraa.uno.game;

import dev.baraa.uno.Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends TablePlayer {

    private static int botCount;

    public Bot() {
        botCount++;
        setName("Bot" + botCount);
    }

    public Card play(Card lastPlayedCard) {
        List<Card> possibleCards = new ArrayList<>();

        /*
          gets all possible cards to play
         */
        for (Card card : getCards()) {
            if (card.isPlayable(lastPlayedCard))
                possibleCards.add(card);
        }

        if (possibleCards.size() == 0) {
            Card draw = Uno.drawCard(this);
            addCard(draw);

            if (draw.isPlayable(lastPlayedCard)) {
                if (draw.getColor() == CardColor.UNIVERSAL)
                    draw.setColor(getBestColor());

                if (getCards().size() - 1 == 1)
                    setUno(true);

                return draw;
            } else
                return null;
        }

        Card card = possibleCards.get(new Random().nextInt(possibleCards.size()));

        if (card.getColor() == CardColor.UNIVERSAL)
            card.setColor(getBestColor());

        if (getCards().size() - 1 == 1)
            setUno(true);

        return card;
    }

    private CardColor getBestColor() {
        int yellowCount = 0, redCount = 0, greenCount = 0, blueCount = 0;

        int bestColorValue = 0;
        CardColor bestColor = null;

        for (Card card : getCards()) {
            if (card.getColor() == CardColor.YELLOW) {
                yellowCount++;
                if (yellowCount > bestColorValue) {
                    bestColor = CardColor.YELLOW;
                    bestColorValue = yellowCount;
                }
            } else if (card.getColor() == CardColor.RED) {
                redCount++;
                if (redCount > bestColorValue) {
                    bestColor = CardColor.RED;
                    bestColorValue = redCount;
                }
            } else if (card.getColor() == CardColor.GREEN) {
                greenCount++;
                if (greenCount > bestColorValue) {
                    bestColor = CardColor.GREEN;
                    bestColorValue = greenCount;
                }
            } else if (card.getColor() == CardColor.BLUE) {
                blueCount++;
                if (blueCount > bestColorValue) {
                    bestColor = CardColor.BLUE;
                    bestColorValue = blueCount;
                }
            }
        }

        return bestColor;
    }
}

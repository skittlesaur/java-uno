package dev.baraa.uno.game;

import dev.baraa.uno.Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends TablePlayer {

    private static int botCount;
    private final String botName;

    public Bot() {
        botCount++;
        botName = "Bot" + botCount;
    }

    public Card play(Card lastPlayedCard) {
        List<Card> possibleCards = new ArrayList<>();

        /*
          gets the possible cards to play
         */
        for (Card card : getCards()) {
            if (card.isPlayable(lastPlayedCard))
                possibleCards.add(card);
        }

        if (possibleCards.size() == 0) {
            Card draw = Uno.drawCard(this);
            addCard(draw);

            if (draw.isPlayable(lastPlayedCard))
                return draw;
            else
                return null;
        }

        return possibleCards.get(new Random().nextInt(possibleCards.size()));
    }

}

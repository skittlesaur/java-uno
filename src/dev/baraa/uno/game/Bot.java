package dev.baraa.uno.game;

import dev.baraa.uno.Uno;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends TablePlayer {

    private static int botCount;
    private double changeOfPlayingOnDraw;

    public Bot() {
        this.changeOfPlayingOnDraw = 0.8;

        botCount++;
        setName("Bot" + botCount);
    }

    /**
     * Makes the decision of which card to play for the bot.
     *
     * @param lastPlayedCard The top card on the table
     * @return A card to play
     */
    public Card play(Card lastPlayedCard) {
        List<Card> possibleCards = new ArrayList<>();

        /*
          gets all possible cards to play
         */
        for (Card card : getCards()) {
            if (card.isPlayable(lastPlayedCard))
                possibleCards.add(card);
        }

        /*
        If there are no possible cards to play, the bot must draw a card according to UNO rules.
        This if statement accomplishes the same thing.
         */
        if (possibleCards.size() == 0) {
            Card draw = Uno.drawCard(this);
            addCard(draw);

            /*
            If the drawn card is playable, the bot decides whether to keep it or play it.
            By default, the bot has an 80% probability of playing the card. In this version of the game, the decision is decided at random.
            */
            if (draw.isPlayable(lastPlayedCard) && Math.random() > changeOfPlayingOnDraw) {

                /*
                If the drawn card is UNIVERSAL, the bot must choose a color before it may be played.
                This if statement changes the card color to the one that favors the bot, which is the color of the majority of the bot cards.
                 */
                if (draw.getColor() == CardColor.UNIVERSAL)
                    draw.setColor(getBestColor());

                /*
                According to UNO rules, if this is the second last card to be played, the player must shout UNO.
                 */
                if (getCards().size() == 2)
                    setUno(true);

                /*
                Returns the drawn card to be played.
                 */
                return draw;
            } else

                /*
                If the card isn't playable, returns null, skipping the player's turn.
                 */

                return null;
        }

        /*
        If the possible cards aren't empty, the bot can move by using one of its cards.
        At this point, the card played by the bot is chosen at random because there is no algorithm in place to determine which card is the best at this point of the game.
         */

        Card card = possibleCards.get(new Random().nextInt(possibleCards.size()));

        if (card.getColor() == CardColor.UNIVERSAL)
            card.setColor(getBestColor());

        if (getCards().size() - 1 == 1)
            setUno(true);

        return card;
    }

    /**
     * This method counts all of the cards in the player's hand and returns the color with the greatest value.
     *
     * @return The color with most appearances in player's deck.
     */
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

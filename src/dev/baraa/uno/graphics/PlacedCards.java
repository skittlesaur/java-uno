package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;

public class PlacedCards extends JLayeredPane {

    private CardImage[] cards;
    private int visibleCards = 5;

    public PlacedCards() {
        cards = new CardImage[visibleCards];
        setOpaque(true);
        setBackground(Color.BLACK);
        //setLayout(new BorderLayout());
        setLayout(new FlowLayout());
    }

    public void update(Card card) {
        if (card == null)
            return;

        updateCards();
        cards[0] = new CardImage(card, true);

        removeAll();

        int i = visibleCards;
        for (CardImage cardImage : cards) {
            if (cardImage == null)
                break;
            add(cardImage, i);
            i--;
        }

        repaint();
        revalidate();
    }

    private void updateCards() {
        for (int i = cards.length - 1; i > 1; i--) {
            cards[i] = cards[i - 1];
        }
    }
}

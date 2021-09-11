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

        add(new CardImage(card, true));

        repaint();
        revalidate();
    }
}

package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlacedCards extends JLayeredPane {

    private List<CardImage> cards;
    private int visibleCards = 5;

    public PlacedCards() {
        cards = new ArrayList<>();
        setOpaque(true);
        setBackground(Color.BLACK);
        //setLayout(new BorderLayout());
        setLayout(new FlowLayout());
    }

    public void update(Card card) {
        System.out.println("CARD: " + card);
        if (card == null)
            return;

        if (cards.size() == visibleCards) {
            cards.remove(0);
        }

        cards.add(new CardImage(card, true));

        removeAll();

        for (int i = 0; i < cards.size(); i++) {
            //addImpl(cards.get(i), BorderLayout.EAST, new Integer(i));
            add(cards.get(i));
        }

        repaint();
        revalidate();
    }
}

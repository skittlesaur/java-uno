package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CardsPanel extends JPanel {

    private final List<Card> cards;
    private final boolean visible;

    public CardsPanel(List<Card> cards, boolean visible) {
        this.cards = cards;
        this.visible = visible;

        setOpaque(false);
        setPreferredSize(new Dimension(200, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        update();
    }

    public void setRotation(int rotation) {

    }


    public void update() {
        removeAll();

        for (Card card : cards) {
            CardImage cardDisplay = new CardImage(card, visible);
            add(cardDisplay);
        }

        repaint();
        revalidate();
    }
}

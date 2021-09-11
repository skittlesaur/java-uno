package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CardsPanel extends JPanel {

    private final List<Card> cards;
    private final boolean visible;
    private int angle;

    public CardsPanel(List<Card> cards, boolean visible) {
        this.cards = cards;
        this.visible = visible;

        setOpaque(false);
        setPreferredSize(new Dimension(200, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        update();
    }

    public void setAngle(int angle) {
        this.angle = angle;
        update();
    }


    public void update() {
        removeAll();

        for (Card card : cards) {
            CardImage cardDisplay = new CardImage(card, visible);
            cardDisplay.setAngle(angle);
            add(cardDisplay);
        }

        repaint();
        revalidate();
    }
}

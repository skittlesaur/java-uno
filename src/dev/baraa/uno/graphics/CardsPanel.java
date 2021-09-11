package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CardsPanel extends JPanel {

    private ArrayList<CardImage> cardImages;

    public CardsPanel(List<Card> cards, boolean visible) {
        cardImages = new ArrayList<>();

        setOpaque(false);
        setPreferredSize(new Dimension(200, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        for (Card card : cards) {
            CardImage cardDisplay = new CardImage(card, visible);
            add(cardDisplay);
            cardImages.add(cardDisplay);
        }
    }

    public void setRotation(int rotation) {
        for (CardImage card : cardImages)
            card.setRotation(rotation);
    }
}

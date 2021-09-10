package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cards extends JPanel {

    private ArrayList<CardDisplay> cardDisplays;

    public Cards(List<Card> cards, boolean visible) {
        cardDisplays = new ArrayList<>();

        setOpaque(false);
        setPreferredSize(new Dimension(200, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        for (Card card : cards) {
            CardDisplay cardDisplay = new CardDisplay(card, visible);
            add(cardDisplay);
            cardDisplays.add(cardDisplay);
        }
    }

    public void setRotation(int rotation) {
        for (CardDisplay card : cardDisplays)
            card.setRotation(rotation);
    }
}

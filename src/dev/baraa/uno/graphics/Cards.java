package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Cards extends JPanel {

    public Cards(List<Card> cards, boolean visible) {
        setOpaque(false);
        setPreferredSize(new Dimension(200, 200));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        for (Card card : cards) {
            CardDisplay cardDisplay = new CardDisplay(card, visible);
            add(cardDisplay);
        }
    }

}

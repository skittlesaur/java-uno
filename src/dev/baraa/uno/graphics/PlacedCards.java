package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlacedCards extends JLayeredPane {

    private List<CardImage> cards;
    private int visibleCards = 5;

    public PlacedCards() {
        cards = new ArrayList<>();
    }

    public void update(Card card) {
        System.out.println("CARD: " + card);
        if (card == null)
            return;

        if (cards.size() == visibleCards) {
            cards.remove(0);
        }

        CardImage cardImage = new CardImage(card, true);
        cardImage.setAngle(new Random().nextInt(360));
        cards.add(cardImage);

        removeAll();

        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setBounds(this.getWidth() / 2 - 100, this.getHeight() / 2 - 100, 200, 200);
            add(cards.get(i), Integer.valueOf(i));
        }

        repaint();
        revalidate();
    }
}

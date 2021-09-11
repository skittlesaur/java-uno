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

    private Arrow arrow;

    public PlacedCards() {
        cards = new ArrayList<>();
        arrow = new Arrow();

        Timer timer = new Timer(10, e -> {
            arrow.rotate(-5);
        });

        timer.setRepeats(true);
        //timer.start();
    }

    public void update(Card card) {
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
            cards.get(i).setSize(200, 200);
            add(cards.get(i), Integer.valueOf(i + 1));
        }

        arrow.setSize(500, 500);
        add(arrow, Integer.valueOf(0));

        repaint();
        revalidate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (Component component : getComponents()) {
            component.setLocation(this.getWidth() / 2 - component.getWidth() / 2, this.getHeight() / 2 - component.getHeight() / 2);
        }
        super.paintComponent(g);
    }
}

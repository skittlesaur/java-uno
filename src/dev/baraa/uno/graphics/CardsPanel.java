package dev.baraa.uno.graphics;

import dev.baraa.uno.Uno;
import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CardsPanel extends JPanel {

    private final List<Card> cards;
    private final boolean visible;
    private int angle;

    private JLabel cardsCount;

    public CardsPanel(List<Card> cards, boolean visible) {
        this.cards = cards;
        this.visible = visible;

        cardsCount = new JLabel(String.valueOf(cards.size()));

        setOpaque(false);
        setPreferredSize(new Dimension(200, 200));
        //setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));

        update();
    }

    public void setAngle(int angle) {
        this.angle = angle;
        update();
    }


    public void update() {
        removeAll();
        cardsCount.setText(String.valueOf(cards.size()));
        add(cardsCount);

        for (Card card : cards) {
            CardImage cardDisplay = new CardImage(card, visible);
            cardDisplay.setAngle(angle);

            if (card.getHolder().isLocalPlayer()) {
                if (card.getHolder().isPlayerTurn()
                        && card.isPlayable(Uno.getLastPlayedCard())) {
                    cardDisplay.setPlayable();
                } else {
                    cardDisplay.setHidden();
                }
            }

            add(cardDisplay);
        }

        repaint();
        revalidate();
    }
}

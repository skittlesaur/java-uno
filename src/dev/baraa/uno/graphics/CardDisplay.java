package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CardDisplay extends JPanel {

    private Card card;

    public CardDisplay(Card card) {
        this.card = card;
        setOpaque(false);
        setPreferredSize(new Dimension((int) (165 * 0.6), (int) (255 * 0.6)));
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage cardImage = ImageProvider.getCard(card.getCardName());
        g.drawImage(cardImage, 0, 0, getWidth(), getHeight(), null);
    }
}

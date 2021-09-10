package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CardDisplay extends JPanel {

    private Card card;
    private boolean visible;

    public CardDisplay(Card card, boolean visible) {
        this.card = card;
        this.visible = visible;
        setOpaque(false);
        setPreferredSize(new Dimension((int) (165 * 0.6), (int) (255 * 0.6)));
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        String cardName = visible ? card.getCardName() : "Default";
        BufferedImage cardImage = ImageProvider.getCard(cardName);
        g.drawImage(cardImage, 0, 0, getWidth(), getHeight(), null);
    }
}

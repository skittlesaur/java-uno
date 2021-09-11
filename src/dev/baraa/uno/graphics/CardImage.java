package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CardImage extends JPanel {

    private Card card;
    private boolean visible;
    private int rotation;

    public CardImage(Card card, boolean visible) {
        this.card = card;
        this.visible = visible;
        setOpaque(false);
        setPreferredSize(new Dimension((int) (165 * 0.6), (int) (255 * 0.6)));
        setLayout(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();

        String cardName = visible ? card.getCardName() : "Default";
        BufferedImage cardImage = ImageProvider.getCard(cardName);

        graphics2D.drawImage(cardImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}

package dev.baraa.uno.graphics;

import dev.baraa.uno.Uno;
import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class CardImage extends JPanel {

    private final Card card;
    private final boolean visible;
    private int angle;

    public CardImage(Card card, boolean visible) {
        this.card = card;
        this.visible = visible;
        setOpaque(false);
        setPreferredSize(new Dimension(100, 155));
        setLayout(new BorderLayout());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (card.getHolder() == null)
                    return;
                if (card.getHolder().isLocalPlayer()) {
                    Uno.play(card.getHolder(), card);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (card.getHolder() == null)
                    return;
                if (card.getHolder().isLocalPlayer()) {
                    setPreferredSize(new Dimension(130, 200));
                    revalidate();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (card.getHolder() == null)
                    return;
                if (card.getHolder().isLocalPlayer()) {
                    setPreferredSize(new Dimension(100, 155));
                    revalidate();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();

        String cardName = visible ? card.getCardName() : "Default";
        BufferedImage cardImage = ImageProvider.getCard(cardName);

        cardImage = rotateImageByDegrees(cardImage, angle);

        graphics2D.drawImage(cardImage, 0, 0, getWidth() - cardImage.getWidth() / getWidth() - cardImage.getHeight() / getHeight(), getHeight(), this);
    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {

        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2d, (newHeight - h) / 2d);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, this);
        g2d.dispose();

        return rotated;
    }


    public void setAngle(int angle) {
        this.angle = angle;
    }
}

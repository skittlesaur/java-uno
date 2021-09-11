package dev.baraa.uno.graphics;

import dev.baraa.uno.Uno;
import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class CardImage extends JPanel {

    private final Card card;
    private final boolean visible;
    private int angle;

    private boolean locked;
    private final int width = 130, height = 180;

    public CardImage(Card card, boolean visible) {
        this.card = card;
        this.visible = visible;
        setOpaque(false);
        setPreferredSize(new Dimension(width, height));
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
                    setPreferredSize(new Dimension((int) (width * 1.1), (int) (height * 1.1)));
                    revalidate();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (card.getHolder() == null)
                    return;
                if (card.getHolder().isLocalPlayer()) {
                    setPreferredSize(new Dimension(width, height));
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

        graphics2D.drawImage(cardImage, getWidth() / 2 - cardImage.getWidth() / 2, 0, this);

        if (locked) {
            graphics2D.setColor(new Color(0xCD181818, true));
            graphics2D.fillRoundRect((getWidth() > width) ? (int) (width * 0.05) : 0, 0, cardImage.getWidth(), cardImage.getHeight(), 5, 5);
        }
        graphics2D.dispose();
    }

    public BufferedImage rotateImageByDegrees(BufferedImage image, double angle) {
        final double rads = Math.toRadians(angle);
        final double sin = Math.abs(Math.sin(rads));
        final double cos = Math.abs(Math.cos(rads));
        final int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin);
        final int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin);
        final BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        final AffineTransform at = new AffineTransform();
        at.translate(w / 2, h / 2);
        at.rotate(rads, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        rotateOp.filter(image, rotatedImage);
        return rotatedImage;
    }


    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void setPlayable() {
        locked = false;
        repaint();
        revalidate();
    }

    public void setHidden() {
        locked = true;
        repaint();
        revalidate();
    }
}

package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlayableDrawnCard extends JDialog {

    private PlayableCardEvent playableCardEvent;
    private OptionButton keep;
    private OptionButton play;

    public PlayableDrawnCard(Card card) {
        setSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("PLAYABLE CARD DRAWN", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 30));
        contentPanel.add(title, BorderLayout.NORTH);

        CardImage cardImage = new CardImage(card, true);
        contentPanel.add(cardImage, BorderLayout.WEST);

        JPanel options = new JPanel();
        options.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 10));
        options.setOpaque(false);
        options.setLayout(new GridLayout(2, 1));

        play = new OptionButton(CardOptions.PLAY);
        play.setOptionEvent(playableCardEvent);
        options.add(play);

        keep = new OptionButton(CardOptions.KEEP);
        keep.setOptionEvent(playableCardEvent);
        options.add(keep);

        contentPanel.add(options);
        setContentPane(contentPanel);
    }

    public void setActive() {
        setModal(true);
        setVisible(true);
    }

    public void setPlayableCardEvent(PlayableCardEvent playableCardEvent) {
        this.playableCardEvent = playableCardEvent;
        play.setOptionEvent(playableCardEvent);
        keep.setOptionEvent(playableCardEvent);
    }
}

class OptionButton extends JButton {


    private PlayableCardEvent playableCardEvent;

    OptionButton(CardOptions option) {
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        switch (option) {
            case KEEP -> setText("Keep Card");
            case PLAY -> setText("Play Card");
        }

        setBackground(Color.red);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                playableCardEvent.actionSelected(option);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
            }
        });
    }

    public void setOptionEvent(PlayableCardEvent playableCardEvent) {
        this.playableCardEvent = playableCardEvent;
    }
}

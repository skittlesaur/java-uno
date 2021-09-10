package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Cards extends JPanel {

    public Cards(List<Card> cards) {
        setPreferredSize(new Dimension(200, 200));
        setBackground(Color.black);
    }

}

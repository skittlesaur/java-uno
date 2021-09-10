package dev.baraa.uno.graphics;

import javax.swing.*;
import java.awt.*;

public class TitleBar extends JPanel {

    /**
     * Generates the window's title bar.
     * Instead of the old-styled Swing title bar, this gives the game a nicer feel when windowed.
     */
    public TitleBar() {
        setPreferredSize(new Dimension(0, 30));
        setBackground(new Color(0x484848));
    }

}

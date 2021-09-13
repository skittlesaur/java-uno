package dev.baraa.uno.graphics;

import dev.baraa.uno.game.Card;
import dev.baraa.uno.game.CardColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorPicker extends JDialog {

    private ColorEvent colorEvent;

    public ColorPicker() {
        setSize(new Dimension(600, 300));
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel colorsPanel = new JPanel();
        colorsPanel.setOpaque(false);
        colorsPanel.setLayout(new BorderLayout());

        JLabel title = new JLabel("CHOOSE A COLOR", SwingConstants.CENTER);
        title.setPreferredSize(new Dimension(0, 30));
        colorsPanel.add(title, BorderLayout.NORTH);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);

        ColorButton yellow = new ColorButton(this, CardColor.YELLOW);
        buttons.add(yellow, gridBagConstraints);

        ColorButton red = new ColorButton(this, CardColor.RED);
        buttons.add(red, gridBagConstraints);

        ColorButton green = new ColorButton(this, CardColor.GREEN);
        buttons.add(green, gridBagConstraints);

        ColorButton blue = new ColorButton(this, CardColor.BLUE);
        buttons.add(blue, gridBagConstraints);

        colorsPanel.add(buttons);

        setContentPane(colorsPanel);
    }

    public void setColorEvent(ColorEvent colorEvent) {
        this.colorEvent = colorEvent;
    }

    public ColorEvent getColorEvent() {
        return colorEvent;
    }

    public void display() {
        setModal(true);
        setVisible(true);
    }
}


class ColorButton extends JButton {

    public ColorButton(ColorPicker controller, CardColor color) {
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

        setPreferredSize(new Dimension(80, 80));
        setBackground(color.getColor());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.getColorEvent().colorPicked(color);
                controller.dispose();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        graphics2D.setColor(new Color(0));
        graphics2D.fillOval(0, 0, getWidth(), getHeight());
        graphics2D.setColor(getBackground());
        graphics2D.fillOval(2, 2, getWidth() - 4, getHeight() - 4);
    }
}

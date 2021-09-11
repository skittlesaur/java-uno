package dev.baraa.uno.game;

import dev.baraa.uno.graphics.ColorPicker;

import java.awt.*;
import java.util.Random;

public enum CardColor {
    YELLOW, RED, GREEN, BLUE, UNIVERSAL;

    /**
     * @return a random card color
     */
    public static CardColor getRandomColor() {
        Random random = new Random();
        CardColor[] cardColors = CardColor.values();
        int randomValue = random.nextInt(cardColors.length - 1);
        return cardColors[randomValue];
    }

    public Color getColor() {
        return switch (this) {
            case YELLOW -> new Color(0xF7E463);
            case RED -> new Color(0xF56462);
            case GREEN -> new Color(0x54E5AB);
            case BLUE -> new Color(0x00C3E5);
            default -> throw new IllegalStateException("Unexpected value: " + this);
        };
    }

    public int getIndex() {
        return switch (this) {
            case YELLOW -> 1;
            case RED -> 2;
            case GREEN -> 3;
            case BLUE -> 4;
            case UNIVERSAL -> 0;
        };
    }
}

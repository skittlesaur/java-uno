package dev.baraa.uno.game;

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
}
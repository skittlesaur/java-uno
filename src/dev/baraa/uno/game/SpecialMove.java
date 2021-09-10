package dev.baraa.uno.game;

public enum SpecialMove {
    PLUS_TWO, SKIP, REVERSE, CHANGE_COLOR, PLUS_FOUR;

    /**
     * Gets the special move of the card based on the card's value. Those values are fixed.
     *
     * @param value Card value
     * @return A special move for the card
     */
    public static SpecialMove getSpecialMove(int value) {

        return switch (value) {
            case 10 -> PLUS_TWO;
            case 11 -> SKIP;
            case 12 -> REVERSE;
            case 13 -> CHANGE_COLOR;
            case 14 -> PLUS_FOUR;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };

    }
}


package dev.baraa.uno.game;

public class Card {

    private int value;
    private CardColor color;

    private boolean special;
    private SpecialMove specialMove;

    private TablePlayer holder;

    /**
     * Creates an UNO card and generates its color based on a given value.
     *
     * @param value Card value. For smaller values (namely less than 10), this value is displayed on the card. Special cards start from 10 till 14.
     */
    public Card(int value) {
        this.value = value;

        if (value > 12) {
            this.color = CardColor.UNIVERSAL;
            setSpecial(this.value);

        } else {
            this.color = CardColor.getRandomColor();

            if (value > 9)
                setSpecial(this.value);
        }
    }

    /**
     * Sets the card to be special.
     *
     * @param value card value
     */
    private void setSpecial(int value) {
        this.special = true;
        this.specialMove = SpecialMove.getSpecialMove(value);
    }

    public int getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }

    public String getCardName() {
        return value + "" + color;
    }

    public TablePlayer getHolder() {
        return holder;
    }

    public void setHolder(TablePlayer holder) {
        this.holder = holder;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", color=" + color +
                ", special=" + special +
                ", specialMove=" + specialMove +
                ", holder=" + holder +
                '}';
    }

    public boolean isSpecial() {
        return special;
    }
}

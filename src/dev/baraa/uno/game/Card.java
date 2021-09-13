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
     * Checks if the selected card can be placed on top of the other card.
     *
     * @param anotherCard The card to be placed on
     * @return True if the selected card is playable and false otherwise.
     */
    public boolean isPlayable(Card anotherCard) {
        return value == anotherCard.getValue()
                || color == anotherCard.getColor()
                || color == CardColor.UNIVERSAL;
    }

    public int getValue() {
        return value;
    }

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
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

    public SpecialMove getSpecialMove() {
        return specialMove;
    }

    private void setSpecial(int value) {
        this.special = true;
        this.specialMove = SpecialMove.getSpecialMove(value);
    }

    public boolean isSpecial() {
        return special;
    }

    @Override
    public String toString() {
        return "Card{" +
                "value=" + value +
                ", color=" + color +
                ", specialMove=" + specialMove +
                '}';
    }
}

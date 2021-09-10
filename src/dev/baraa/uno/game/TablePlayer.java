package dev.baraa.uno.game;

import java.util.ArrayList;

public abstract class TablePlayer {

    private ArrayList<Card> cards;

    public TablePlayer() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void playCard(Card card) {
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
}

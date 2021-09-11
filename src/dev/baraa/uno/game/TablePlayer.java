package dev.baraa.uno.game;

import java.util.ArrayList;

public abstract class TablePlayer {

    private ArrayList<Card> cards;
    private boolean localPlayer;
    private boolean uno;

    public TablePlayer(boolean localPlayer) {
        this();
        this.localPlayer = localPlayer;
    }

    public TablePlayer() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        card.setHolder(this);
        cards.add(card);
    }

    public void removeCard(Card card) {
        card.setHolder(null);
        cards.remove(card);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean isLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(boolean localPlayer) {
        this.localPlayer = localPlayer;
    }

    public boolean isUno() {
        return uno;
    }

    public void setUno(boolean uno) {
        if (uno)
            System.out.println(this + " UNO!");
        this.uno = uno;
    }
}

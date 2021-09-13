package dev.baraa.uno.game;

import java.util.ArrayList;

public abstract class TablePlayer {

    private final ArrayList<Card> cards;
    private boolean localPlayer;
    private boolean uno;
    private boolean playerTurn;
    private String name;

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

    public boolean isUno() {
        return uno;
    }

    public void setUno(boolean uno) {
        this.uno = uno;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

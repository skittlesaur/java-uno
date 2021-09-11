package dev.baraa.uno.exceptions.game;

import dev.baraa.uno.game.Card;

public class IllegalCardException extends GameException {
    public IllegalCardException(Card card, Card lastPlayedCard) {
        super("Couldn't play " + card + " on " + lastPlayedCard);
    }
}

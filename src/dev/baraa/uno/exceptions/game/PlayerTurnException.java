package dev.baraa.uno.exceptions.game;

public class PlayerTurnException extends GameException {
    public PlayerTurnException() {
        super("Please wait for your turn to play!");
    }
}

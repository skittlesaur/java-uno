package dev.baraa.uno.exceptions.graphics;

public class GameWindowHasBeenInitializedException extends GraphicsException {

    public GameWindowHasBeenInitializedException() {
        super("The game's window had already been set up");
    }

}

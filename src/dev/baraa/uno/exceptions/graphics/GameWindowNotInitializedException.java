package dev.baraa.uno.exceptions.graphics;

public class GameWindowNotInitializedException extends GraphicsException {

    public GameWindowNotInitializedException() {
        super("The game window was not properly initialised");
    }

}

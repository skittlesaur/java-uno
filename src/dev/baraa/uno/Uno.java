package dev.baraa.uno;

import dev.baraa.uno.exceptions.graphics.GameWindowHasBeenInitializedException;
import dev.baraa.uno.exceptions.graphics.GameWindowNotInitializedException;
import dev.baraa.uno.exceptions.graphics.GraphicsException;
import dev.baraa.uno.graphics.GameWindow;

public class Uno {

    public static void main(String[] args) throws GraphicsException {
        initialize();
        run();
    }

    private static void initialize() throws GameWindowHasBeenInitializedException {
        GameWindow.initialize();
    }

    private static void run() throws GameWindowNotInitializedException {
        GameWindow.start();
    }

}

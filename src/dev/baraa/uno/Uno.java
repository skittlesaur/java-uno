package dev.baraa.uno;

import dev.baraa.uno.exceptions.graphics.GameWindowHasBeenInitializedException;
import dev.baraa.uno.exceptions.graphics.GameWindowNotInitializedException;
import dev.baraa.uno.exceptions.graphics.GraphicsException;
import dev.baraa.uno.graphics.GameWindow;
import dev.baraa.uno.graphics.ImageProvider;

import java.io.IOException;

public class Uno {

    public static void main(String[] args) throws GraphicsException, IOException {
        initialize();
        run();
    }

    private static void initialize() throws GameWindowHasBeenInitializedException, IOException {
        ImageProvider.load();
        Controller.initialize();
        GameWindow.initialize();
    }

    private static void run() throws GameWindowNotInitializedException {
        GameWindow.start();
    }
}

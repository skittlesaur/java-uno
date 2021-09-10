package dev.baraa.uno.game;

public class Bot extends TablePlayer {

    private static int botCount;
    private String botName;

    public Bot() {
        botCount++;
        botName = "Bot" + botCount;
    }

}

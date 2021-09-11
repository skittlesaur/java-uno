package dev.baraa.uno.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageProvider {

    private static Map<String, BufferedImage> cards;

    public static void load() throws IOException {
        cards = new HashMap<>();
        mapCards();
    }

    private static BufferedImage readCardImage(String name) throws IOException {
        InputStream inputStream = ImageProvider.class.getClassLoader().getResourceAsStream("res/cards/" + name + ".png");
        return ImageIO.read(inputStream);
    }

    /**
     * Maps the correct card to its BufferedImage in the cards.png
     * Also, stores it in the HashMap of cards.
     */
    private static void mapCards() throws IOException {
        cards.put("Default", readCardImage("card_back"));
        cards.put("13UNIVERSAL", readCardImage("wild_color_changer"));
        cards.put("14UNIVERSAL", readCardImage("wild_pick_four"));

        String[] colors = {"YELLOW", "RED", "GREEN", "BLUE"};
        for (String color : colors) {
            for (int i = 0; i <= 9; i++) {
                cards.put(i + color, readCardImage(color.toLowerCase() + "_" + i));
            }
            cards.put("10" + color, readCardImage(color.toLowerCase() + "_picker"));
            cards.put("11" + color, readCardImage(color.toLowerCase() + "_skip"));
            cards.put("12" + color, readCardImage(color.toLowerCase() + "_reverse"));
            cards.put("13" + color, readCardImage("wild_color_changer"));
            cards.put("14" + color, readCardImage("wild_pick_four"));
        }
    }

    /**
     * @param cardName The card name, formatted as valueCOLOR.
     * @return The BufferedImage for specified card
     */
    public static BufferedImage getCard(String cardName) {
        return cards.get(cardName);
    }
}

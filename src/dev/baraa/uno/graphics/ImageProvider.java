package dev.baraa.uno.graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageProvider {

    private static BufferedImage cardsImage;
    private static Map<String, BufferedImage> cards;

    public static void load() throws IOException {
        InputStream inputStream = ImageProvider.class.getClassLoader().getResourceAsStream("res/cards.png");
        cardsImage = ImageIO.read(inputStream);
        cards = new HashMap<>();
        mapCards();
    }

    /**
     * Maps the correct card to its BufferedImage in the cards.png
     * Also, stores it in the HashMap of cards.
     */
    private static void mapCards() {
        final int cardWidth = 165;
        final int cardHeight = 255;
        final int offset = 2;

        /**
         * Default back view of the card
         */
        cards.put("Default", cardsImage.getSubimage(0, 0, cardWidth, cardHeight));

        /**
         * Maps special cards
         */
        String[] colors = {"UNIVERSAL", "YELLOW", "RED", "BLUE", "GREEN"};

        int col = 1;
        for (int val = 13; val <= 14; val++) {
            for (String color : colors) {
                int x = (cardWidth + offset) * col;
                String name = val + color;
                cards.put(name, cardsImage.getSubimage(x, 0, cardWidth, cardHeight));
                col++;
            }
        }


        /**
         * Maps the remaining cards (color-based) to their names
         */

        int row = 1, val;
        col = 0;
        colors = new String[]{"YELLOW", "RED", "BLUE", "GREEN"};

        for (String color : colors) {
            for (int i = 0; i < 13; i++) {
                int x = (cardWidth + offset) * (col % 12);
                int y = (cardHeight + offset) * row;

                /**
                 * Card with number 0 appears as the 9th card in cards.png
                 *
                 * If `i` is bigger than 9 the value of the card is the same as `i`, otherwise it's less by one.
                 */
                if (i == 9)
                    val = 0;
                else if (i > 9)
                    val = i;
                else
                    val = i + 1;


                /**
                 * Formatting the card name as valueCOLOR, for example (2RED).
                 */
                String name = val + color;

                cards.put(name, cardsImage.getSubimage(x, y, cardWidth, cardHeight));

                col++;
                if (col % 12 == 0)
                    row++;
            }
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

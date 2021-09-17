package dev.baraa.uno.tests;

import dev.baraa.uno.exceptions.game.GameException;
import dev.baraa.uno.game.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTests {

    @Test
    public void turnTests() throws GameException {
        Game game = new Game();
        Player player = game.getPlayer();
        Bot bot = (Bot) game.getGamePlayers()[1];

        /*
        Some cards allow the player to go again in 1v1 mode. These cards are +2, skip, and +4.
         */
        testTurn(game, player, 10, 0); // +2 Card
        testTurn(game, player, 11, 0); // Skip Card
        testTurn(game, player, 14, 0); // +4 Card


        /*
        All other cards should change the player's turn to the next player.
         */

        int turn = 0;

        for (int i = 0; i < 14; i++) {
            // If i is one of the cards that don't change turn skip it.
            if (i == 10 || i == 11)
                continue;

            if (turn % 2 == 0) {
                testTurn(game, player, i, 1);
            } else {
                testTurn(game, bot, i, 0);
            }
            turn++;
        }

        testTurn(game, player, 0, 1);

        turn = 0;
        for (int i = 0; i < 14; i++) {
            // If i is one of the cards that don't change turn skip it.
            if (i == 10 || i == 11)
                continue;

            if (turn % 2 == 1) {
                testTurn(game, player, i, 1);
            } else {
                testTurn(game, bot, i, 0);
            }
            turn++;
        }
    }

    @Test
    public void wildCardTurnsTest() throws GameException {
        Game game = new Game();
        Player player = game.getPlayer();

        testTurn(game, player, 10, 0); // +2
        testTurn(game, player, 11, 0); // skip
        testTurn(game, player, 14, 0); // +4
        testTurn(game, player, 13, 1); // change color
        game.updateTurn();
        testTurn(game, player, 12, 1); // reverse
    }

    @Test
    public void plusCardsTest() throws GameException {
        Game game = new Game();
        Player player = game.getPlayer();
        Bot bot = (Bot) game.getGamePlayers()[1];

        int botCards = bot.getCards().size();

        Card plusTwo = new Card(10);
        playCard(game, player, plusTwo);
        testCardsCount(bot, botCards + 2);

        botCards = bot.getCards().size();

        Card plusFour = new Card(14);
        playCard(game, player, plusFour);
        testCardsCount(bot, botCards + 4);
    }

    private void playCard(Game game, TablePlayer player, Card card) throws GameException {
        CardColor color = game.getLastPlayedCard().getColor();
        card.setColor(color);
        game.play(player, card);
        game.updateTurn();
    }

    private void testTurn(Game game, TablePlayer player, int cardValue, int expectedTurn) throws GameException {
        Card card = new Card(cardValue);
        playCard(game, player, card);
        assertEquals("Played card: " + card, expectedTurn, game.getTurn());
    }

    private void testCardsCount(TablePlayer player, int expectedCount) {
        assertEquals(expectedCount, player.getCards().size());
    }

}

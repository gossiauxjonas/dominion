package logic.test;

import logic.Player;
import logic.TreasureCard;
import logic.VictoryCard;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Indy Van Mol on 22/03/16.
 */

public class TestPlayer {

    TreasureCard copper;
    VictoryCard estate;
    Player player;

    @Before
    public void setUp() {
        String name = "name";
        copper = new TreasureCard("copper", 0, 1);
        estate = new VictoryCard("estate", 2, 1);
        player = new Player(name, copper, estate);
    }

    @Test
    public void testConstructorWithCreateNewDeck() {
        assertEquals(5, player.amountCardsHand());
        assertEquals(5, player.amountCardsDeck());
        assertEquals(0, player.amountCardsDiscard());
    }

    @Test
    public void testGetName() {
        assertEquals("name", player.getName());
    }

    @Test
    public void testDrawCardFromDeck() {
        Object copy1 = player.getDrawDeck().get(4);
        Object safe1 = player.drawCardFromDeck();
        assertEquals(4, player.amountCardsDeck());
        assertEquals(copy1, safe1);
        Object copy2 = player.getDrawDeck().get(3);
        Object safe2 = player.drawCardFromDeck();
        assertEquals(3, player.amountCardsDeck());
        assertEquals(copy2, safe2);
    }

    @Test
    public void testDrawnCardToHand() {
        Object copy1 = player.getDrawDeck().get(4);
        player.drawCardsToHand(1);
        assertEquals(6, player.amountCardsHand());
        assertEquals(4, player.amountCardsDeck());
        assertEquals(copy1, player.getCardInHandOn(5));
        Object copy2 = player.getDrawDeck().get(3);
        Object copy3 = player.getDrawDeck().get(2);
        player.drawCardsToHand(2);
        assertEquals(8, player.amountCardsHand());
        assertEquals(2, player.amountCardsDeck());
        assertEquals(copy2, player.getCardInHandOn(6));
        assertEquals(copy3, player.getCardInHandOn(7));
    }

    @Test
    public void testDiscardCardFromHand() {
        player.discardCardFromHand(4);
        assertEquals(4, player.amountCardsHand());
        assertEquals(1, player.amountCardsDiscard());
        player.discardCardFromHand(0);
        assertEquals(3, player.amountCardsHand());
        assertEquals(2, player.amountCardsDiscard());
    }

    @Test
    public void testEmptyHand() {
        player.emptyHand();
        assertEquals(0, player.amountCardsHand());
        assertEquals(5, player.amountCardsDiscard());
    }

    @Test
    public void testEndTurn() {
        player.endTurn();
        assertEquals(5, player.amountCardsHand());
        assertEquals(5, player.amountCardsDiscard());
        assertEquals(0, player.amountCardsDeck());
    }

    @Test
    public void testFillDeck() {
        player.emptyHand();
        player.drawCardsToHand(5);
        assertEquals(0, player.amountCardsDeck());
        assertEquals(5, player.amountCardsHand());
        assertEquals(5, player.amountCardsDiscard());
        player.emptyHand();
        assertEquals(0, player.amountCardsHand());
        assertEquals(10, player.amountCardsDiscard());
        player.drawCardsToHand(1);
        assertEquals(9, player.amountCardsDeck());
        assertEquals(1, player.amountCardsHand());
        assertEquals(0, player.amountCardsDiscard());
        player.drawCardsToHand(5);
        player.endTurn();
        assertEquals(5, player.amountCardsDeck());
        assertEquals(5, player.amountCardsHand());
        assertEquals(0, player.amountCardsDiscard());
    }

    @Test
    public void testDrawnCardToHandWhenBothDecksAreEmpty() {
        player.drawCardsToHand(5);
        assertEquals(10, player.amountCardsHand());
        assertEquals(0, player.amountCardsDeck());
        assertEquals(0, player.amountCardsDiscard());
        player.drawCardsToHand(2);
        assertEquals(10, player.amountCardsHand());
        assertEquals(0, player.amountCardsDeck());
        assertEquals(0, player.amountCardsDiscard());
    }

    @Test
    public void testPutDeckOnDiscard() {
        player.putDeckOnDiscard();
        assertEquals(5, player.amountCardsDiscard());
        assertEquals(0, player.amountCardsDeck(), 0);
    }

    @Test
    public void testToDiscard() {
        player.toDiscard(estate);
        assertEquals(1, player.amountCardsDiscard());
        player.toDiscard(copper);
        player.toDiscard(copper);
        assertEquals(3, player.amountCardsDiscard());
    }

}
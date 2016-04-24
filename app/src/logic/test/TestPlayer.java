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
    Player player0;
    Player player1;
    Player player2;
    Player player3;
    Player player4;
    Player player5;
    Player player6;
    Player player7;

    @Before
    public void setUp() {
        String name = "name";
        copper = new TreasureCard("copper", 0, 1);
        estate = new VictoryCard("estate", 2, 1);
        player0 = new Player(name, copper, estate);
        player1 = new Player(name, copper, estate);
        player2 = new Player(name, copper, estate);
        player3 = new Player(name, copper, estate);
        player4 = new Player(name, copper, estate);
        player5 = new Player(name, copper, estate);
        player6 = new Player(name, copper, estate);
        player7 = new Player(name, copper, estate);
    }

    @Test
    public void testConstructor() {
        assertEquals(player1.amountCardsHand(), 5);
        assertEquals(player1.amountCardsDeck(), 5);
    }

    @Test
    public void testDrawCardFromDeck() {
        Object copy1 = player0.getDrawDeck().get(4);
        Object copy2 = player0.getDrawDeck().get(3);
        Object safe1 = player0.drawCardFromDeck();
        assertEquals(player0.amountCardsDeck(), 4);
        assertEquals(safe1, copy1);
        Object safe2 = player0.drawCardFromDeck();
        assertEquals(player0.amountCardsDeck(), 3);
        assertEquals(safe2, copy2);
    }

    @Test
    public void testDrawnCardToHand() {
        player1.drawCardToHand(1);
        assertEquals(player1.amountCardsHand(), 6);
        assertEquals(player1.amountCardsDeck(), 4);
        player1.drawCardToHand(2);
        assertEquals(player1.amountCardsHand(), 8);
        assertEquals(player1.amountCardsDeck(), 2);
    }

    @Test
    public void testDiscardCardFromHand() {
        player2.discardCardFromHand(4);
        assertEquals(player2.amountCardsHand(), 4);
        assertEquals(player2.amountCardsDiscard(), 1);
        player2.discardCardFromHand(0);
        assertEquals(player2.amountCardsHand(), 3);
        assertEquals(player2.amountCardsDiscard(), 2);
    }

    @Test
    public void testEmptyHand() {
        player3.emptyHand();
        assertEquals(player3.amountCardsHand(), 0);
        assertEquals(player3.amountCardsDiscard(), 5);
    }

    @Test
    public void testEndTurn() {
        player4.endTurn();
        assertEquals(player4.amountCardsHand(), 5);
        assertEquals(player4.amountCardsDiscard(), 5);
        assertEquals(player4.amountCardsDeck(), 0);
    }

    @Test
    public void testFillDeck() {
        player5.emptyHand();
        player5.drawCardToHand(5);
        assertEquals(player5.amountCardsDeck(), 0);
        assertEquals(player5.amountCardsHand(), 5);
        assertEquals(player5.amountCardsDiscard(), 5);
        player5.emptyHand();
        assertEquals(player5.amountCardsHand(), 0);
        assertEquals(player5.amountCardsDiscard(), 10);
        player5.drawCardToHand(1);
        assertEquals(player5.amountCardsDeck(), 9);
        assertEquals(player5.amountCardsHand(), 1);
        assertEquals(player5.amountCardsDiscard(), 0);
        player5.drawCardToHand(5);
        player5.endTurn();
        assertEquals(player5.amountCardsDeck(), 5);
        assertEquals(player5.amountCardsHand(), 5);
        assertEquals(player5.amountCardsDiscard(), 0);
    }

    @Test
    public void testPlayCard() {
        Object copy1 = player6.getHand().get(0);
        Object ref1 = player6.playCard(0);
        assertEquals(copy1, ref1);
        assertEquals(player6.amountCardsHand(), 5);
        Object copy2 = player6.getHand().get(3);
        Object ref2 = player6.playCard(3);
        assertEquals(copy2, ref2);
        assertEquals(player6.amountCardsHand(), 5);
    }

    @Test
    public void testPutDeckOnDiscard() {
        player6.discardCardFromHand(3);
        player6.putDeckOnDiscard();
        assertEquals(player6.amountCardsDiscard(), 6);
        assertEquals(player6.amountCardsDeck(), 0);
    }

    @Test
    public void testToDiscard() {
        player7.toDiscard(estate);
        assertEquals(player7.amountCardsDiscard(), 1);
        player7.toDiscard(copper);
        player7.toDiscard(copper);
        assertEquals(player7.amountCardsDiscard(), 3);
    }

}
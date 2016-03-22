package logic.test;

import junit.framework.Assert;
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
    Player player1;
    Player player2;
    Player player3;
    Player player4;

    @Before
    public void setUp() {
        copper = new TreasureCard("copper", 0, 1);
        estate = new VictoryCard("estate", 2, 1);
        player1 = new Player(copper, estate);
        player2 = new Player(copper, estate);
        player3 = new Player(copper, estate);
        player4 = new Player(copper, estate);
    }

    @Test
    public void testConstructor() {
        assertEquals(player1.getHand().size(), 5);
        assertEquals(player1.getDeck().size(), 5);
    }

    @Test
    public void testDrawnCard() {
        player1.drawCard(1);
        assertEquals(player1.getHand().size(), 6);
        assertEquals(player1.getDeck().size(), 4);
        player1.drawCard(2);
        assertEquals(player1.getHand().size(), 8);
        assertEquals(player1.getDeck().size(), 2);
    }

    @Test
    public void testRemoveCardFromHand() {
        player2.removeCardFromHand(4);
        assertEquals(player2.getHand().size(), 4);
        assertEquals(player2.getDiscard().size(), 1);
        player2.removeCardFromHand(0);
        assertEquals(player2.getHand().size(), 3);
        assertEquals(player2.getDiscard().size(), 2);
    }

    @Test
    public void testEmptyHand() {
        player3.emptyHand();
        assertEquals(player3.getHand().size(), 0);
        assertEquals(player3.getDiscard().size(), 5);
    }

    @Test
    public void testEndTurn() {
        player4.endTurn();
        assertEquals(player4.getHand().size(), 5);
        assertEquals(player4.getDiscard().size(), 5);
        assertEquals(player4.getDeck().size(), 0);
    }

}

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

    @Before
    public void setUp() {
        copper = new TreasureCard("copper", 0, 1);
        estate = new VictoryCard("estate", 2, 1);
        player1 = new Player(copper, estate);
    }

    @Test
    public void testConstructor() {
        assertEquals(player1.getHand().size(), 0);
        assertEquals(player1.getDeck().size(), 10);
    }

}

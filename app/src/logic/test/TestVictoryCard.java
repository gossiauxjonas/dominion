package logic.test;

import logic.VictoryCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestVictoryCard {

    VictoryCard victoryCard1;
    VictoryCard victoryCard2;
    VictoryCard victoryCard3;

    @Before
    public void setUp() {
        victoryCard1 = new VictoryCard(2, 1);
        victoryCard2 = new VictoryCard(5, 3);
        victoryCard3 = new VictoryCard(8, 6);
    }

    @Test
    public void testVictoryPriceGetter() {
        assertEquals(victoryCard1.getPrice(), 2);
        assertEquals(victoryCard2.getPrice(), 5);
        assertEquals(victoryCard3.getPrice(), 8);
    }

    @Test
    public void testVictoryPointsGetter() {
        assertEquals(victoryCard1.getVictoryPoints(), 1);
        assertEquals(victoryCard2.getVictoryPoints(), 3);
        assertEquals(victoryCard3.getVictoryPoints(), 6);
    }

}

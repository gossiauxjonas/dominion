package logic.test;

import logic.BasicCard;
import logic.TreasureCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestTreasureCard {

    TreasureCard treasureCard1;
    TreasureCard treasureCard2;
    TreasureCard treasureCard3;

    @Before
    public void setUp() {
        treasureCard1 = new TreasureCard(0, 1);
        treasureCard2 = new TreasureCard(3, 2);
        treasureCard3 = new TreasureCard(6, 3);
    }

    @Test
    public void testTreasurePriceGetter() {
        assertEquals(treasureCard1.getPrice(), 0);
        assertEquals(treasureCard2.getPrice(), 3);
        assertEquals(treasureCard3.getPrice(), 6);
    }

    @Test
    public void testTreasureCoinValueGetter() {
        assertEquals(treasureCard1.getCoinValue(), 1);
        assertEquals(treasureCard2.getCoinValue(), 2);
        assertEquals(treasureCard3.getCoinValue(), 3);
    }

}
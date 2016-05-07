package logic.test;

import logic.TreasureCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestTreasureCard {

    TreasureCard copper;
    TreasureCard silver;
    TreasureCard gold;

    @Before
    public void setUp() {
        copper = new TreasureCard("copper", 0, 1);
        silver = new TreasureCard("silver", 3, 2);
        gold = new TreasureCard("gold", 6, 3);
    }

    @Test
    public void testTreasureGetName() {
        assertEquals("copper", copper.getName());
        assertEquals("silver", silver.getName());
        assertEquals("gold", gold.getName());
    }

    @Test
    public void testTreasurePriceGetter() {
        assertEquals(0, copper.getPrice());
        assertEquals(3, silver.getPrice());
        assertEquals(6, gold.getPrice());
    }

    @Test
    public void testTreasureCoinValueGetter() {
        assertEquals(1, copper.getCoinValue());
        assertEquals(2, silver.getCoinValue());
        assertEquals(3, gold.getCoinValue());
    }

}
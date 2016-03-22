package logic.test;

import logic.Copper;
import logic.Gold;
import logic.Silver;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestAllTreasureCards {

    Copper copperCard;
    Silver silverCard;
    Gold goldCard;

    @Before
    public void setUp() {
        copperCard = new Copper();
        silverCard = new Silver();
        goldCard = new Gold();
    }

    @Test
    public void testPriceGetter() {
        assertEquals(copperCard.getPrice(), 0);
        assertEquals(silverCard.getPrice(), 3);
        assertEquals(goldCard.getPrice(), 6);
    }

    @Test
    public void testCoinValueGetter() {
        assertEquals(copperCard.getCoinValue(), 1);
        assertEquals(silverCard.getCoinValue(), 2);
        assertEquals(goldCard.getCoinValue(), 3);
    }
}

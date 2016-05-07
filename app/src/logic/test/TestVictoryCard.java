package logic.test;

import logic.VictoryCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestVictoryCard {

    VictoryCard estate;
    VictoryCard duchy;
    VictoryCard province;
    VictoryCard curse;

    @Before
    public void setUp() {
        estate = new VictoryCard("estate", 2, 1);
        duchy = new VictoryCard("duchy", 5, 3);
        province = new VictoryCard("province", 8, 6);
        curse = new VictoryCard("curse", 0, -1);
    }

    @Test
    public void testVictoryGetName() {
        assertEquals("estate", estate.getName());
        assertEquals("duchy", duchy.getName());
        assertEquals("province", province.getName());
        assertEquals("curse", curse.getName());
    }

    @Test
    public void testVictoryPriceGetter() {
        assertEquals(2, estate.getPrice());
        assertEquals(5, duchy.getPrice());
        assertEquals(8, province.getPrice());
        assertEquals(0, curse.getPrice());
    }

    @Test
    public void testVictoryPointsGetter() {
        assertEquals(1, estate.getVictoryPoints());
        assertEquals(3, duchy.getVictoryPoints());
        assertEquals(6, province.getVictoryPoints());
        assertEquals(-1, curse.getVictoryPoints());
    }

}

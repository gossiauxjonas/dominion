package logic.test;

import logic.Duchy;
import logic.Estate;
import logic.Province;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestAllVictoryCards {

    Estate estate;
    Duchy duchy;
    Province province;

    @Before
    public void setUp() {
        estate = new Estate();
        duchy = new Duchy();
        province = new Province();
    }

    @Test
    public void testPriceGetter() {
        assertEquals(estate.getPrice(), 2);
        assertEquals(duchy.getPrice(), 5);
        assertEquals(province.getPrice(), 8);
    }

    @Test
    public void testVictoryPointGetter() {
        assertEquals(estate.getVictoryPoints(), 1);
        assertEquals(duchy.getVictoryPoints(), 3);
        assertEquals(province.getVictoryPoints(), 6);
    }

}

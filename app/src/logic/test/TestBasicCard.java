package logic.test;

import logic.BasicCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestBasicCard {

    BasicCard rootCard1;
    BasicCard rootCard2;
    BasicCard rootCard3;

    @Before
    public void setUp() {
        rootCard1 = new BasicCard("rootCard1", 2);
        rootCard2 = new BasicCard("rootCard2", 3);
        rootCard3 = new BasicCard("rootCard3",0);
    }

    @Test
    public void testBasicCardNameGetter() {
        assertEquals("rootCard1", rootCard1.getName());
        assertEquals("rootCard2", rootCard2.getName());
        assertEquals("rootCard3", rootCard3.getName());
    }

    @Test
    public void testBasicCardPriceGetter() {
        assertEquals(2, rootCard1.getPrice());
        assertEquals(3, rootCard2.getPrice());
        assertEquals(0, rootCard3.getPrice());
    }

}

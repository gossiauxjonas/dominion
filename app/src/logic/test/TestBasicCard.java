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
        assertEquals(rootCard1.getName(), "rootCard1");
        assertEquals(rootCard2.getName(), "rootCard2");
        assertEquals(rootCard3.getName(), "rootCard3");
    }

    @Test
    public void testBasicCardPriceGetter() {
        assertEquals(rootCard1.getPrice(), 2);
        assertEquals(rootCard2.getPrice(), 3);
        assertEquals(rootCard3.getPrice(), 0);
    }

}

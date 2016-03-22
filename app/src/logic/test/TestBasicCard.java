package logic.test;

import static org.junit.Assert.*;

import logic.BasicCard;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TestBasicCard {

    BasicCard rootCard1;
    BasicCard rootCard2;
    BasicCard rootCard3;

    @Before
    public void setUp() {
        rootCard1 = new BasicCard(2);
        rootCard2 = new BasicCard(3);
        rootCard3 = new BasicCard(0);
    }

    @Test
    public void testBasicCardPriceGetter() {
        assertEquals(rootCard1.getPrice(), 2);
        assertEquals(rootCard2.getPrice(), 3);
        assertEquals(rootCard3.getPrice(), 0);
    }

}

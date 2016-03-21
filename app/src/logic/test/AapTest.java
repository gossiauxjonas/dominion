package logic.test;

import logic.Rechthoek;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Asus on 21/03/16.
 */
public class AapTest {
    @Test
    public void testVierkantToString() {
        Rechthoek rechthoek = new Rechthoek();
        assertEquals("base: 5 height: 10", rechthoek.toString());
    }
}

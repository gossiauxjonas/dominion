package logic.test;

import logic.Main;
import logic.Rechthoek;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Asus on 20/03/16.
 */

public class MainTest {

    @Test
    public void testRechthoekToString() {
        Rechthoek rechthoek = new Rechthoek();
        assertEquals("base: 5 height: 10", rechthoek.toString());

    }

    @Test
    public void testVanJonas(){
        
        assertEquals(1,1);
    }




}
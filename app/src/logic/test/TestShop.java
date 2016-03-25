package logic.test;

import logic.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Indy Van Mol on 23/03/16.
 */

public class TestShop {

    //Cards
    BasicCard copper;
    BasicCard silver;
    BasicCard gold;
    BasicCard actionCard;
    BasicCard estate;
    BasicCard duchy;
    BasicCard province;
    BasicCard curse;

    //Stacks
    CardStack copperStack;
    CardStack silverStack;
    CardStack goldStack;
    CardStack actionCardStack;
    CardStack estateStack;
    CardStack duchyStack;
    CardStack provinceStack;
    CardStack curseStack;

    Shop shop1;
    Shop shop2;
    Shop shop3;

    @Before
    public void setUp() {
        //Card constructors
        copper = new TreasureCard(0, 1);
        silver = new TreasureCard(3, 2);
        gold = new TreasureCard(6, 3);
        actionCard = new ActionCard(4);
        estate = new VictoryCard(2, 1);
        duchy = new VictoryCard(5, 3);
        province = new VictoryCard(8, 6);
        curse = new VictoryCard(0, -1);

        //Stack constructors
        copperStack = new CardStack(2, copper);
        silverStack = new CardStack(2, silver);
        goldStack = new CardStack(2, gold);
        actionCardStack = new CardStack(2, actionCard);
        estateStack = new CardStack(2, estate);
        duchyStack = new CardStack(2, duchy);
        provinceStack = new CardStack(2, province);
        curseStack = new CardStack(2, curse);

        //Shop constructors
        shop1 = new Shop(copperStack, silverStack, goldStack, actionCardStack, actionCardStack
                        , actionCardStack, actionCardStack, actionCardStack, actionCardStack
                        , actionCardStack, actionCardStack, actionCardStack, actionCardStack
                        , estateStack, duchyStack, provinceStack, curseStack);
        shop2 = new Shop(copperStack, silverStack, goldStack, actionCardStack, actionCardStack
                , actionCardStack, actionCardStack, actionCardStack, actionCardStack
                , actionCardStack, actionCardStack, actionCardStack, actionCardStack
                , estateStack, duchyStack, provinceStack, curseStack);
        shop1 = new Shop(copperStack, silverStack, goldStack, actionCardStack, actionCardStack
                , actionCardStack, actionCardStack, actionCardStack, actionCardStack
                , actionCardStack, actionCardStack, actionCardStack, actionCardStack
                , estateStack, duchyStack, provinceStack, curseStack);
    }

    @Test
    public void testPriceOfCard() {
        assertEquals(shop1.priceOfCard(0), 0);
        assertEquals(shop1.priceOfCard(1), 3);
        assertEquals(shop1.priceOfCard(2), 6);
        assertEquals(shop1.priceOfCard(7), 4);
        assertEquals(shop1.priceOfCard(13), 2);
        assertEquals(shop1.priceOfCard(14), 5);
        assertEquals(shop1.priceOfCard(15), 8);
        assertEquals(shop1.priceOfCard(16), 0);
    }

    @Test
    public void testBuyCard() {
        assertEquals(shop1.buyCard(0), copper);
        assertEquals(shop1.buyCard(1), silver);
        assertEquals(shop1.buyCard(2), gold);
        assertEquals(shop1.buyCard(4), actionCard);
        assertEquals(shop1.buyCard(13), estate);
        assertEquals(shop1.buyCard(14), duchy);
        assertEquals(shop1.buyCard(15), province);
        assertEquals(shop1.buyCard(16), curse);
    }

    @Test
    public void testIsOpen() {
        shop2.buyCard(1);
        assertEquals(shop2.isOpen(), true);
        for (int i = 0; i < 10; i++) {
            shop2.buyCard(4);
        }
        for (int i = 0; i < 40; i++) {
            shop2.buyCard(1);
        }
        for (int i = 0; i < 40; i++) {
            shop2.buyCard(2);
        }
        assertEquals(shop2.isOpen(), false);
    }




}

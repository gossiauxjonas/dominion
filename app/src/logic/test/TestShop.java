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
    BasicCard copper = new TreasureCard(0, 1);
    BasicCard silver = new TreasureCard(3, 2);
    BasicCard gold = new TreasureCard(6, 3);
    BasicCard actionCard = new ActionCard(4);
    BasicCard estate = new VictoryCard(2, 1);
    BasicCard duchy = new VictoryCard(5, 3);
    BasicCard province = new VictoryCard(8, 6);
    BasicCard curse = new VictoryCard(0, -1);

    //Stacks
    CardStack copperStack1 = new CardStack(2, copper);
    CardStack copperStack2 = new CardStack(2, copper);
    CardStack copperStack3 = new CardStack(3, copper);
    CardStack silverStack1 = new CardStack(2, silver);
    CardStack silverStack2 = new CardStack(2, silver);
    CardStack silverStack3 = new CardStack(3, silver);
    CardStack goldStack1 = new CardStack(2, gold);
    CardStack goldStack2 = new CardStack(2, gold);
    CardStack goldStack3 = new CardStack(3, gold);
    CardStack estateStack1 = new CardStack(2, estate);
    CardStack estateStack2 = new CardStack(2, estate);
    CardStack estateStack3 = new CardStack(3, estate);
    CardStack duchyStack1 = new CardStack(2, duchy);
    CardStack duchyStack2 = new CardStack(2, duchy);
    CardStack duchyStack3 = new CardStack(3, duchy);
    CardStack provinceStack1 = new CardStack(2, province);
    CardStack provinceStack2 = new CardStack(2, province);
    CardStack provinceStack3 = new CardStack(3, province);
    CardStack curseStack1 = new CardStack(2, curse);
    CardStack curseStack2 = new CardStack(2, curse);
    CardStack curseStack3 = new CardStack(3, curse);

    //Action stacks
    CardStack actionCardStack1_1 = new CardStack(2, actionCard);
    CardStack actionCardStack1_2 = new CardStack(2, actionCard);
    CardStack actionCardStack1_3 = new CardStack(2, actionCard);
    CardStack actionCardStack1_4 = new CardStack(2, actionCard);
    CardStack actionCardStack1_5 = new CardStack(2, actionCard);
    CardStack actionCardStack1_6 = new CardStack(2, actionCard);
    CardStack actionCardStack1_7 = new CardStack(2, actionCard);
    CardStack actionCardStack1_8 = new CardStack(2, actionCard);
    CardStack actionCardStack1_9 = new CardStack(2, actionCard);
    CardStack actionCardStack1_10 = new CardStack(2, actionCard);

    CardStack actionCardStack2_1 = new CardStack(2, actionCard);
    CardStack actionCardStack2_2 = new CardStack(2, actionCard);
    CardStack actionCardStack2_3 = new CardStack(2, actionCard);
    CardStack actionCardStack2_4 = new CardStack(2, actionCard);
    CardStack actionCardStack2_5 = new CardStack(2, actionCard);
    CardStack actionCardStack2_6 = new CardStack(2, actionCard);
    CardStack actionCardStack2_7 = new CardStack(2, actionCard);
    CardStack actionCardStack2_8 = new CardStack(2, actionCard);
    CardStack actionCardStack2_9 = new CardStack(2, actionCard);
    CardStack actionCardStack2_10 = new CardStack(2, actionCard);

    CardStack actionCardStack3_1 = new CardStack(3, actionCard);
    CardStack actionCardStack3_2 = new CardStack(3, actionCard);
    CardStack actionCardStack3_3 = new CardStack(3, actionCard);
    CardStack actionCardStack3_4 = new CardStack(3, actionCard);
    CardStack actionCardStack3_5 = new CardStack(3, actionCard);
    CardStack actionCardStack3_6 = new CardStack(3, actionCard);
    CardStack actionCardStack3_7 = new CardStack(3, actionCard);
    CardStack actionCardStack3_8 = new CardStack(3, actionCard);
    CardStack actionCardStack3_9 = new CardStack(3, actionCard);
    CardStack actionCardStack3_10 = new CardStack(3, actionCard);

    //Shops
    Shop shop1;
    Shop shop2;
    Shop shop3;

    @Before
    public void setUp() {
        //Shop constructors
        shop1 = new Shop(copperStack1, silverStack1, goldStack1, actionCardStack1_1, actionCardStack1_2
                , actionCardStack1_3, actionCardStack1_4, actionCardStack1_5, actionCardStack1_6
                , actionCardStack1_7, actionCardStack1_8, actionCardStack1_9, actionCardStack1_10
                , estateStack1, duchyStack1, provinceStack1, curseStack1);
        shop2 = new Shop(copperStack2, silverStack2, goldStack2, actionCardStack2_1, actionCardStack2_2
                , actionCardStack2_3, actionCardStack2_4, actionCardStack2_5, actionCardStack2_6
                , actionCardStack2_7, actionCardStack2_8, actionCardStack2_9, actionCardStack2_10
                , estateStack2, duchyStack2, provinceStack2, curseStack2);
        shop3 = new Shop(copperStack3, silverStack3, goldStack3, actionCardStack3_1, actionCardStack3_2
                , actionCardStack3_3, actionCardStack3_4, actionCardStack3_5, actionCardStack3_6
                , actionCardStack3_7, actionCardStack3_8, actionCardStack3_9, actionCardStack3_10
                , estateStack3, duchyStack3, provinceStack3, curseStack3);
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
        //normal shop closing
        assertEquals(shop2.isOpen(), true);
        for (int i = 0; i < 10; i++) {
            shop2.buyCard(4);
        }
        assertEquals(shop2.cardsLeftInStack(4), 0);
        assertEquals(shop2.isOpen(), true);
        for (int i = 0; i < 40; i++) {
            shop2.buyCard(1);
        }
        assertEquals(shop2.cardsLeftInStack(1), 0);
        assertEquals(shop2.isOpen(), true);
        for (int i = 0; i < 30; i++) {
            shop2.buyCard(2);
        }
        assertEquals(shop2.cardsLeftInStack(2), 0);
        assertEquals(shop2.isOpen(), false);

        //shop closing province
        assertEquals(shop3.isOpen(), true);
        for (int i = 0; i < 10; i++) {
            shop3.buyCard(7);
        }
        assertEquals(shop3.cardsLeftInStack(7), 0);
        assertEquals(shop3.isOpen(), true);
        for (int i = 0; i < 12; i++) {
            shop3.buyCard(15);
        }
        assertEquals(shop3.cardsLeftInStack(15), 0);
        assertEquals(shop3.isOpen(), false);
    }

}
package logic.test;

import logic.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Indy Van Mol on 24/03/16.
 */

public class testCardStack {

    @Test
    public void testAmountCopper() {
        TreasureCard copper = new TreasureCard("copper", 0, 1);
        CardStack copperStack2P = new CardStack(2, copper);
        assertEquals(copperStack2P.getAmountOfCards(), 46);
        CardStack copperStack3P = new CardStack(3, copper);
        assertEquals(copperStack3P.getAmountOfCards(), 39);
        CardStack copperStack4P = new CardStack(4, copper);
        assertEquals(copperStack4P.getAmountOfCards(), 32);
    }

    @Test
    public void testAmountSilver() {
        TreasureCard silver = new TreasureCard("silver", 3, 2);
        CardStack silverStack2P = new CardStack(2, silver);
        assertEquals(silverStack2P.getAmountOfCards(), 40);
        CardStack silverStack3P = new CardStack(3, silver);
        assertEquals(silverStack3P.getAmountOfCards(), 40);
        CardStack silverStack4P = new CardStack(4, silver);
        assertEquals(silverStack4P.getAmountOfCards(), 40);
    }

    @Test
    public void testAmountGold() {
        TreasureCard gold = new TreasureCard("gold", 6, 3);
        CardStack goldStack2P = new CardStack(2, gold);
        assertEquals(goldStack2P.getAmountOfCards(), 30);
        CardStack goldStack3P = new CardStack(3, gold);
        assertEquals(goldStack3P.getAmountOfCards(), 30);
        CardStack goldStack4P = new CardStack(4, gold);
        assertEquals(goldStack4P.getAmountOfCards(), 30);
    }

    @Test
    public void testAmountBaseActionCard() {
        ActionCard actionCard = new ActionCard("testactioncard" ,15);
        CardStack actionStack2P = new CardStack(2, actionCard);
        assertEquals(actionStack2P.getAmountOfCards(), 10);
        CardStack actionStack3P = new CardStack(3, actionCard);
        assertEquals(actionStack3P.getAmountOfCards(), 10);
        CardStack actionStack4P = new CardStack(4, actionCard);
        assertEquals(actionStack4P.getAmountOfCards(), 10);
    }

    @Test
    public void testAmountSmithyActionCard() {
        Smithy smithy = new Smithy();
        CardStack smithyStack2P = new CardStack(2, smithy);
        assertEquals(smithyStack2P.getAmountOfCards(), 10);
        CardStack smithyStack3P = new CardStack(3, smithy);
        assertEquals(smithyStack3P.getAmountOfCards(), 10);
        CardStack smithyStack4P = new CardStack(4, smithy);
        assertEquals(smithyStack4P.getAmountOfCards(), 10);
    }

    @Test
    public void testAmountGarden() {
        Garden garden = new Garden();
        CardStack gardenStack2P = new CardStack(2, garden);
        assertEquals(gardenStack2P.getAmountOfCards(), 8);
        CardStack gardenStack3P = new CardStack(3, garden);
        assertEquals(gardenStack3P.getAmountOfCards(), 12);
        CardStack gardenStack4P = new CardStack(4, garden);
        assertEquals(gardenStack4P.getAmountOfCards(), 12);
    }

    @Test
    public void testAmountCurse() {
        VictoryCard curse = new VictoryCard("curse", 0, -1);
        CardStack curseStack2P = new CardStack(2, curse);
        assertEquals(curseStack2P.getAmountOfCards(), 10);
        CardStack curseStack3P = new CardStack(3, curse);
        assertEquals(curseStack3P.getAmountOfCards(), 20);
        CardStack curseStack4P = new CardStack(4, curse);
        assertEquals(curseStack4P.getAmountOfCards(), 30);
    }

    @Test
    public void testAmountEstate() {
        VictoryCard estate = new VictoryCard("estate", 2, 1);
        CardStack estateStack2P = new CardStack(2, estate);
        assertEquals(estateStack2P.getAmountOfCards(), 8);
        CardStack estateStack3P = new CardStack(3, estate);
        assertEquals(estateStack3P.getAmountOfCards(), 12);
        CardStack estateStack4P = new CardStack(4, estate);
        assertEquals(estateStack4P.getAmountOfCards(), 12);
    }

    @Test
    public void testAmountDuchy() {
        VictoryCard duchy = new VictoryCard("duchy", 5, 3);
        CardStack duchyStack2P = new CardStack(2, duchy);
        assertEquals(duchyStack2P.getAmountOfCards(), 8);
        CardStack duchyStack3P = new CardStack(3, duchy);
        assertEquals(duchyStack3P.getAmountOfCards(), 12);
        CardStack duchyStack4P = new CardStack(4, duchy);
        assertEquals(duchyStack4P.getAmountOfCards(), 12);
    }

    @Test
    public void testAmountProvince() {
        VictoryCard province = new VictoryCard("province", 8, 6);
        CardStack provinceStack2P = new CardStack(2, province);
        assertEquals(provinceStack2P.getAmountOfCards(), 8);
        CardStack provinceStack3P = new CardStack(3, province);
        assertEquals(provinceStack3P.getAmountOfCards(), 12);
        CardStack provinceStack4P = new CardStack(4, province);
        assertEquals(provinceStack4P.getAmountOfCards(), 12);
    }

    @Test
    public void testDecrementStack() {
        Smithy smithy = new Smithy();
        CardStack smithyStack = new CardStack(2, smithy);
        smithyStack.decrementStack();
        assertEquals(smithyStack.getAmountOfCards(), 9);
        smithyStack.decrementStack();
        smithyStack.decrementStack();
        assertEquals(smithyStack.getAmountOfCards(), 7);
    }

    @Test
    public void testSavedStack() {
        Smithy smithy = new Smithy();
        CardStack savedSmithyStack = new CardStack(smithy, 5);
        assertEquals(savedSmithyStack.getAmountOfCards(), 5);
        Garden garden = new Garden();
        CardStack savedGardenStack = new CardStack(garden, 7);
        assertEquals(savedGardenStack.getAmountOfCards(), 7);
        ActionCard actionCard = new ActionCard("actionCard", 4);
        CardStack savedActionCardStack = new CardStack(actionCard, 9);
        assertEquals(savedActionCardStack.getAmountOfCards(), 9);
    }
}

package logic;

/**
  * Created by Indy Van Mol on 23/03/2016.
 **/


public class GameEngine {

    BasicCard copper = new TreasureCard(0, 1);
    BasicCard silver = new TreasureCard(3, 2);
    BasicCard gold = new TreasureCard(6, 3);
    BasicCard actionCard = new ActionCard(4);
    BasicCard estate = new VictoryCard(2, 1);
    BasicCard duchy = new VictoryCard(5, 3);
    BasicCard province = new VictoryCard(8, 6);
    BasicCard curse = new VictoryCard(0, -1);

    CardStack copperStack = new CardStack(2, copper);
    CardStack silverStack = new CardStack(2, silver);
    CardStack goldStack = new CardStack(2, gold);
    CardStack estateStack = new CardStack(2, estate);
    CardStack duchyStack = new CardStack(2, duchy);
    CardStack provinceStack = new CardStack(2, province);
    CardStack curseStack = new CardStack(2, curse);

    CardStack actionCardStack1 = new CardStack(2, actionCard);
    CardStack actionCardStack2 = new CardStack(2, actionCard);
    CardStack actionCardStack3 = new CardStack(2, actionCard);
    CardStack actionCardStack4 = new CardStack(2, actionCard);
    CardStack actionCardStack5 = new CardStack(2, actionCard);
    CardStack actionCardStack6 = new CardStack(2, actionCard);
    CardStack actionCardStack7 = new CardStack(2, actionCard);
    CardStack actionCardStack8 = new CardStack(2, actionCard);
    CardStack actionCardStack9 = new CardStack(2, actionCard);
    CardStack actionCardStack10 = new CardStack(2, actionCard);

    Shop shop;

    private Player[] players;

    public void GameEngine(String player1, String player2) {
        players = new Player[]{new Player(player1, copper, estate), new Player(player2, copper, estate)};
        shop = new Shop(copperStack, silverStack, goldStack, actionCardStack1, actionCardStack2
                , actionCardStack3, actionCardStack4, actionCardStack5, actionCardStack6
                , actionCardStack7, actionCardStack8, actionCardStack9, actionCardStack10
                , estateStack, duchyStack, provinceStack, curseStack);

    }

    public void gameLoop() {
        while (shop.isOpen()) {
            
        }
    }


}
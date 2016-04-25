package logic;

import java.util.List;

/**
  * Created by Indy Van Mol on 23/03/2016.
 **/


public class GameEngine {

    private TreasureCard copper = new TreasureCard("copper", 0, 1);
    private BasicCard silver = new TreasureCard("silver" ,3, 2);
    private BasicCard gold = new TreasureCard("gold", 6, 3);
    private BasicCard actionCard = new ActionCard("testactioncard" ,4);
    private VictoryCard estate = new VictoryCard("estate" ,2, 1);
    private BasicCard duchy = new VictoryCard("duchy", 5, 3);
    private BasicCard province = new VictoryCard("province", 8, 6);
    private BasicCard curse = new VictoryCard("curse", 0, -1);

    private CardStack copperStack = new CardStack(2, copper);
    private CardStack silverStack = new CardStack(2, silver);
    private CardStack goldStack = new CardStack(2, gold);
    private CardStack estateStack = new CardStack(2, estate);
    private CardStack duchyStack = new CardStack(2, duchy);
    private CardStack provinceStack = new CardStack(2, province);
    private CardStack curseStack = new CardStack(2, curse);

    private CardStack actionCardStack1 = new CardStack(2, actionCard);
    private CardStack actionCardStack2 = new CardStack(2, actionCard);
    private CardStack actionCardStack3 = new CardStack(2, actionCard);
    private CardStack actionCardStack4 = new CardStack(2, actionCard);
    private CardStack actionCardStack5 = new CardStack(2, actionCard);
    private CardStack actionCardStack6 = new CardStack(2, actionCard);
    private CardStack actionCardStack7 = new CardStack(2, actionCard);
    private CardStack actionCardStack8 = new CardStack(2, actionCard);
    private CardStack actionCardStack9 = new CardStack(2, actionCard);
    private CardStack actionCardStack10 = new CardStack(2, actionCard);

    private Shop shop;

    private Player[] players;

    private int playerTurn;

    public GameEngine(String... players) {
        this.players = new Player[players.length];
        for (int i = 0; i < players.length; i++) {
            this.players[i] = new Player(players[i], copper, estate);
        }
        shop = new Shop(copperStack, silverStack, goldStack, actionCardStack1, actionCardStack2
                , actionCardStack3, actionCardStack4, actionCardStack5, actionCardStack6
                , actionCardStack7, actionCardStack8, actionCardStack9, actionCardStack10
                , estateStack, duchyStack, provinceStack, curseStack);
        playerTurn = 0;
    }

    public Player getPlayer() {
        return players[playerTurn];
    }

    public Shop getShop() {
        return shop;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void nextTurn() {
        playerTurn = (playerTurn + 1) % players.length;
    }

    public int calculateTreasure() {
        int coinsInHand = 0;
        List<BasicCard> hand = getPlayer().getHand();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getClass().equals(TreasureCard.class)) {
                TreasureCard temp = (TreasureCard) hand.get(i);
                coinsInHand += (temp.getCoinValue());
            }
        }
        return coinsInHand;
    }

}
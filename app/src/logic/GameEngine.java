package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
  * Created by Indy Van Mol on 23/03/2016.
 **/


public class GameEngine {

    private TreasureCard copper = new TreasureCard("copper", 0, 1);
    private TreasureCard silver = new TreasureCard("silver" ,3, 2);
    private TreasureCard gold = new TreasureCard("gold", 6, 3);

    private Garden garden = new Garden();
    private Smithy smithy = new Smithy(this);
    private Village village = new Village(this);
    private Festival festival = new Festival(this);
    private Market market = new Market(this);
    private Laboratory laboratory = new Laboratory(this);
    private Moat moat = new Moat(this);
    private Woodcutter woodcutter = new Woodcutter(this);
    private Chancellor chancellor = new Chancellor(this);
    private Adventurer adventurer = new Adventurer(this);
    private CouncilRoom councilRoom = new CouncilRoom(this);
    private Witch witch = new Witch(this);
    private ThroneRoom throneRoom = new ThroneRoom(this);
    private Chapel chapel = new Chapel(this);
    private Moneylender moneylender = new Moneylender(this);
    private Cellar cellar = new Cellar(this);
    private Workshop workshop = new Workshop(this);
    private Feast feast = new Feast(this);
    private Remodel remodel = new Remodel(this);
    private Library library = new Library(this);
    private Mine mine = new Mine(this);
    private Spy spy = new Spy(this);
    private Thief thief = new Thief(this);
    private Militia militia = new Militia(this);
    private Bureaucrat bureaucrat = new Bureaucrat(this);

    private VictoryCard estate = new VictoryCard("estate" ,2, 1);
    private VictoryCard duchy = new VictoryCard("duchy", 5, 3);
    private VictoryCard province = new VictoryCard("province", 8, 6);
    private VictoryCard curse = new VictoryCard("curse", 0, -1);

    private CardStack copperStack = new CardStack(2, copper);
    private CardStack silverStack = new CardStack(2, silver);
    private CardStack goldStack = new CardStack(2, gold);
    private CardStack estateStack = new CardStack(2, estate);
    private CardStack duchyStack = new CardStack(2, duchy);
    private CardStack provinceStack = new CardStack(province, 1);
    private CardStack curseStack = new CardStack(curse, 0);

    private CardStack actionCardStack1 = new CardStack(2, adventurer);
    private CardStack actionCardStack2 = new CardStack(2, moneylender);
    private CardStack actionCardStack3 = new CardStack(2, thief);
    private CardStack actionCardStack4 = new CardStack(2, spy);
    private CardStack actionCardStack5 = new CardStack(2, moat);
    private CardStack actionCardStack6 = new CardStack(2, witch);
    private CardStack actionCardStack7 = new CardStack(2, throneRoom);
    private CardStack actionCardStack8 = new CardStack(2, library);
    private CardStack actionCardStack9 = new CardStack(2, mine);
    private CardStack actionCardStack10 = new CardStack(2, chapel);

    private Shop shop;

    private Player[] players;

    private int playerTurn;

    private int turnActions;
    private int turnBuys;
    private int turnCoins;

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

    public BasicCard getCopper() {
        return copper;
    }

    public void startNewturn() {
        turnActions = 1;
        turnBuys = 1;
        turnCoins = 0;
    }

    public int getAmountDrawCards(Player player) {
        List<BasicCard> allCardsPlayers = new ArrayList<BasicCard>();
        allCardsPlayers.addAll(player.getHand());
        allCardsPlayers.addAll(player.getDiscard());
        return allCardsPlayers.size();
    }

    public Boolean playerHasTreasure(Player player) {
        List<BasicCard> allCardsPlayers = new ArrayList<BasicCard>();
        allCardsPlayers.addAll(player.getHand());
        allCardsPlayers.addAll(player.getDiscard());
        ListIterator it = allCardsPlayers.listIterator();
        int i = 0;
        while(it.hasNext()) {
            BasicCard card = (BasicCard) it.next();
            if (card.getClass().equals(TreasureCard.class)) i++;
        }
        return i > 0;
    }

    public void addTurnActions(int amount) {
        turnActions += amount;
    }
    public void decrementTurnActions() {
        turnActions--;
    }

    public int getTurnActions() {
        return turnActions;
    }

    public void endTurnActions() {
        turnActions = 0;
    }

    public void addTurnBuys(int amount) {
        turnBuys += amount;
    }
    public void decrementTurnBuys() {
        turnBuys--;
    }

    public int getTurnBuys() {
        return turnBuys;
    }

    public void endTurnBuys() {
        turnBuys = 0;
    }

    public void addTurnCoins(int amount) {
        turnCoins += amount;
    }

    public int getTurnCoins() {
        return turnCoins;
    }

    public Player getPlayer() {
        return players[playerTurn];
    }

    public Shop getShop() {
        return shop;
    }

    public Player[] getPlayers() {return players;}

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void nextTurn() {
        playerTurn = (playerTurn + 1) % players.length;
    }

    public int calculateTreasureInHand() {
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

    public int calculatePoints(int playerPosition) {
        Player player = players[playerPosition];
        List<BasicCard> allCardsPlayer = new ArrayList<BasicCard>(player.getHand());
        allCardsPlayer.addAll(player.getDrawDeck());
        allCardsPlayer.addAll(player.getDiscard());
        int gardens = 0;
        int victoryPoints = 0;
        for (int i = 0; i < allCardsPlayer.size(); i++) {
            if (allCardsPlayer.get(i).getName() == "garden") gardens++;
            if (allCardsPlayer.get(i).getClass().getName() == "logic.VictoryCard") {
                VictoryCard card = (VictoryCard) allCardsPlayer.get(i);
                victoryPoints += card.getVictoryPoints();
            }
        }
        victoryPoints += (allCardsPlayer.size() / 10) * gardens;
        return victoryPoints;
    }

    public Player[] getOtherPlayers() {
        Player[] otherPlayers = new Player[players.length - 1];
        int i  = 0;
        for (int j = 0; j < players.length; j++) {
            if (j != playerTurn) {
                otherPlayers[i] = players[j];
                i++;
            }
        }
        return otherPlayers;
    }

    public Moat getMoat() {
        return moat;
    }

    public int[][] playersAndScore() {
        int[][] playerAndScore = new int[players.length][2];
        for (int i = 0; i < players.length; i++) {
            playerAndScore[i][0] = i;
            playerAndScore[i][1] = calculatePoints(i);
        }
        return playerAndScore;
    }

    public int[][] playerScoreRank() {
        int[][] array = playersAndScore();
        for (int i = 0; i < (array.length - 1); i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j][1] < array[j+1][1]) {
                    int[] swap       = array[j];
                    array[j]   = array[j+1];
                    array[j+1] = swap;
                }
            }
        }
        return array;
    }

}
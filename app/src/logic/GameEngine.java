package logic;

/**
 * Created by jasper on 23/03/2016.
 **/


public class GameEngine {
    TreasureCard copper = new TreasureCard(0, 1);
    VictoryCard estate = new VictoryCard(2, 1);
    private Player[] players;

    public void GameEngine(String player1, String player2) {
        players = new Player[]{new Player(player1, copper, estate), new Player(player2, copper, estate)};
    }


}
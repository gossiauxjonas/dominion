package logic;

/**
 * Created by jasper on 23/03/2016.
 */


public class GameEngine {
    private int turn = 1;
    private int AmountOfPlayers = 2;
    private int[] ArrayPlayers = new int[AmountOfPlayers];
    //Player player1 = new Player();
    //Player player2 = new Player();


    public GameEngine() {
        boolean gameEnd = false;
        if (!gameEnd) gameLoop();

    }

    public void gameLoop() {
        //if (turn == 1 && Player == player1) {choseStartingPlayer()}
        //startTurn();
        //endTurn();
        //cleanTable();
        //nextTurn();
        //winCondition();
        //endOfGame();
    }

    public int[] choseStartingPlayer(int AmountOfPlayers) {

        for (int i = 0; i < AmountOfPlayers * 50; i++) {
            //shufflePlayers


        }
        return ArrayPlayers;
    }
    public boolean winCondition(){
        boolean victory = false;

        return victory;
    }


    public void endTurn() {

    }


    public void cleanTable() {

    }


    public void nextTurn() {

    }


    public boolean endOfGame(boolean gameEnd) {
        if (winCondition() == false) gameEnd = true;

        return gameEnd;

    }


}
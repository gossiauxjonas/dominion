package logic;

/**
 * Created by jasper on 23/03/2016.
 **/


public class GameEngine {
    private int round = 1;
    private int AmountOfPlayers = 2;
    private int[] ArrayPlayers = new int[AmountOfPlayers];
    //Player player1 = new Player();
    //Player player2 = new Player();


    public void GameEngine() {
        gameLoop();

    }

    public void gameLoop() {
        boolean gameEnd = false;

        while (!gameEnd) if (round == 1) {
            ShufflePlayers(AmountOfPlayers);
        }
        //startTurn();
        //endTurn();
        //cleanTable();
        //nextTurn();
        //winCondition();
        //endOfGame(gameEnd);
    }

    public int[] ShufflePlayers(int AmountOfPlayers) {

        for (int i = 0; i < AmountOfPlayers * 50; i++) {
            //shufflePlayers


        }
        return ArrayPlayers;
    }




    public void startTurn(int[] ArrayPlayers) {
        int action = 1;
        int buy = 1;
        int AmountOfCoins = 0;
        //selecteer player 1

    }

    private boolean winCondition() {
        boolean victory = false;
        while (!victory) {
            //check als de 3 stapels leeg zijn of province stapel leeg is
            //roep method op die in shop zit die de stapels overloopt
        }
        return victory;
    }


    public void endTurn() {
        round += 1;


    }


    public void cleanTable() {

    }


    public void nextTurn() {

    }


    public boolean endOfGame(boolean gameEnd) //stoppen met spelen method
    {
        if (!winCondition()) gameEnd = true;

        return gameEnd;

    }


}
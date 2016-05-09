package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Festival extends ActionCard{

    public Festival(GameEngine game) {
        super("festival", 5, game);
    }

    public void playAction() {
        game.addTurnActions(2);
        game.addTurnBuys(1);
        game.addTurnCoins(2);
    }
}

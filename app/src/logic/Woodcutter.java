package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Woodcutter extends ActionCard {

    public Woodcutter(GameEngine game) {
        super("woodcutter", 3, game);
    }

    public void playAction() {
        game.addTurnBuys(1);
        game.addTurnCoins(2);
    }
}

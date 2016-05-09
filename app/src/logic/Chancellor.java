package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Chancellor extends ActionCard {

    public Chancellor(GameEngine game) {
        super("chancellor", 3, game);
    }

    public void playAction() {
        game.addTurnCoins(2);
        game.getPlayer().putDeckOnDiscard();
    }
}

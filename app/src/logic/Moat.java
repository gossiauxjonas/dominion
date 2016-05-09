package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Moat extends ActionCard {

    public Moat(GameEngine game) {
        super("moat", 2, game);
    }

    public void playAction() {
        game.getPlayer().drawCardsToHand(2);
    }
}

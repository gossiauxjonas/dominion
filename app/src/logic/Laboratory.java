package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Laboratory extends ActionCard {

    public Laboratory(GameEngine game) {
        super("laboratory", 5, game);
    }

    public void playAction() {
        game.getPlayer().drawCardsToHand(2);
        game.addTurnActions(1);
    }
}

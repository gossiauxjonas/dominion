package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Village extends ActionCard {

    public Village(GameEngine game) {
        super("village", 3, game);
    }

    public void playAction() {
        game.getPlayer().drawCardsToHand(1);
        game.addTurnActions(2);
    }

}

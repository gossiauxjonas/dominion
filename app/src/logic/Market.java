package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Market extends ActionCard {

    public Market(GameEngine game) {
        super("market", 5, game);
    }

    public void playAction() {
        game.getPlayer().drawCardsToHand(1);
        game.addTurnActions(1);
        game.addTurnBuys(1);
        game.addTurnCoins(1);
    }


}
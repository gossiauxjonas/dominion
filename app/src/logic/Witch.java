package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Witch extends ActionCard {

    public Witch(GameEngine game) {
        super("witch", 5, game);
    }

    public void playAction() {
        game.getPlayer().drawCardsToHand(2);
        Player[] otherPlayers = game.getOtherPlayers();
        for (int i = 0; i < otherPlayers.length; i++) {
            otherPlayers[i].toDiscard(game.getShop().buyCard(16));
        }
    }
}

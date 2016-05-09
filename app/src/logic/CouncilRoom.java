package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class CouncilRoom extends ActionCard {

    public CouncilRoom(GameEngine game) {
        super("council Room", 5, game);
    }

    public void playAction() {
        game.getPlayer().drawCardsToHand(4);
        game.addTurnBuys(1);
        Player[] otherPlayers = game.getOtherPlayers();
        for (int i = 0; i < otherPlayers.length; i++) {
            otherPlayers[i].drawCardsToHand(1);
        }
    }
}

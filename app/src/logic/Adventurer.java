package logic;

/**
 * Created by Asus on 09/05/16.
 */
public class Adventurer extends ActionCard {

    public Adventurer(GameEngine game) {
        super("adventurer", 6, game);
    }

    public void playAction() {
        for (int i = 0; i < 2; i++) {
            BasicCard pulledCard = game.getPlayer().drawCardFromDeck();
            while (!(pulledCard.getClass().equals(TreasureCard.class))) {
                game.getPlayer().toDiscard(pulledCard);
                pulledCard = game.getPlayer().drawCardFromDeck();
            }
            game.getPlayer().putCardInHand(pulledCard);
        }
    }
}

package logic;

/**
 * Created by Indy Van Mol on 24/03/16.
 */
public class Smithy extends ActionCard {

    public Smithy() {
        super("smithy", 4);
    }

    public void playAction(Player player) {
        player.drawCardToHand(3);
    }

}

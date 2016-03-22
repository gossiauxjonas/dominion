package logic;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class VictoryCard extends BasicCard {

    private int victoryPoints;

    public VictoryCard(String cardName, int price, int victoryPoints) {
        super(cardName, price);
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

}

package logic;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class VictoryCard extends BasicCard {

    private int victoryPoints;

    public VictoryCard(int price, int victoryPoints) {
        super(price);
        this.victoryPoints = victoryPoints;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

}

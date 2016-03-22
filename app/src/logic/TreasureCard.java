package logic;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TreasureCard extends BasicCard {

    private int coinValue;

    public TreasureCard(int price, int coinValue) {
        super(price);
        this.coinValue = coinValue;
    }

    public int getCoinValue() {
        return coinValue;
    }

}

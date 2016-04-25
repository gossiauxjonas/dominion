package logic;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TreasureCard extends BasicCard {

    private int coinValue;

    public TreasureCard(String name, int price, int coinValue) {
        super(name, price);
        this.coinValue = coinValue;
    }

    public int getCoinValue() {
        return coinValue;
    }

}
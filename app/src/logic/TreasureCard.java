package logic;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class TreasureCard extends BasicCard {

    private int coinValue;

    public TreasureCard(String cardName, int price, int coinValue) {
        super(cardName, price);
        this.coinValue = coinValue;
    }

    public int getCoinValue() {
        return coinValue;
    }

}

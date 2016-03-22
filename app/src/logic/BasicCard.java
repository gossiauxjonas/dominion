package logic;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class BasicCard {

    String cardName;
    private int price;

    public BasicCard(String cardName, int price) {
        this.cardName = cardName;
        this.price = price;
    }

    public String getCardName() {
        return cardName;
    }

    public int getPrice() {
        return price;
    }

}

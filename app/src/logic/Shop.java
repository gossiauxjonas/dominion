package logic;

/**
 * Created by Indy Van Mol on 23/03/16.
 */

public class Shop {

    private CardStack[] shopArray;
    private int emptyStacks;

    public Shop() {
        shopArray = new CardStack[17];
        emptyStacks = 0;
        CreateShopArray();
    }

    private void CreateShopArray(CardStack... card) {
        for (int i = 0; i< card.length; i++) {
                shopArray[i] = card[i];
        }
    }

}
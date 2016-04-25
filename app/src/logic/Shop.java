package logic;

/*
 * Created by Indy Van Mol on 23/03/16.
 */

public class Shop {

    private CardStack[] shopArray;
    private int emptyStacks;
    private boolean open;

    public Shop(CardStack copperStack, CardStack silverStack, CardStack goldStack, CardStack action1, CardStack action2
            , CardStack action3, CardStack action4, CardStack action5, CardStack action6, CardStack action7
            , CardStack action8, CardStack action9, CardStack action10, CardStack estateStack, CardStack duchy
            , CardStack Province, CardStack curse) {
        shopArray = new CardStack[17];
        emptyStacks = 0;
        open = true;
        CreateShopArray(copperStack, silverStack, goldStack, action1, action2, action3, action4, action5, action6, action7
                , action8, action9, action10, estateStack, duchy
                , Province, curse);
    }

    public void CreateShopArray(CardStack... card) {
        for (int i = 0; i < card.length; i++) {
            shopArray[i] = card[i];
        }

    }

    public int priceOfCard(int place) {
        return shopArray[place].getPriceOfCard();
    }

    public int cardsLeftInStack(int place) {
        return shopArray[place].getAmountOfCards();
    }

    public BasicCard buyCard(int place) {
        shopArray[place].decrementStack();
        CanShop(place);
        return shopArray[place].getCard();
    }

    public void CanShop(int place) {
        CardStack stack = shopArray[place];
        BasicCard card = stack.getCard();
        if (stack.getAmountOfCards() == 0) {
            emptyStacks += 1;
            if (emptyStacks == 3 || (card.getClass().equals(VictoryCard.class) && card.getPrice() == 8)) {
                open = false;
            }
        }
    }

    public CardStack[] getShopArray() {
        return shopArray;
    }

    public boolean isOpen() {
        return open;
    }

}
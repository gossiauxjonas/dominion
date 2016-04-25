package logic;

/**
 * Created by Indy Van Mol on 24/03/16.
 */

public class CardStack {

    private int amountOfCards;
    private BasicCard card;

    public CardStack(int amountPlayers, BasicCard card) {
        this.card = card;
        setAmountOfCards(amountPlayers);
    }

    public CardStack(BasicCard card, int cardsLeft) {
        this.card = card;
        amountOfCards = cardsLeft;
    }

    public void setAmountOfCards(int amountPlayers) { // refactor
        switch (card.getClass().getName()) {
            case "logic.TreasureCard":
                setAmountTreasureCard(amountPlayers);
                break;
            case "logic.VictoryCard":
                setAmountVictoryCard(amountPlayers);
                break;
            default: setAmountActionCard(amountPlayers);
                break;
        }
        /*if (ActionCard.class.isAssignableFrom(card.getClass())) setAmountActionCard(amountPlayers);
        else if (card.getClass().equals(TreasureCard.class)) setAmountTreasureCard(amountPlayers);
        else if (card.getClass().equals(VictoryCard.class)) setAmountVictoryCard(amountPlayers);*/
    }

    public void setAmountActionCard(int amountPlayers) {
        if (card.getClass().equals(Garden.class)) {
            if (amountPlayers > 2) amountOfCards = 12;
            else amountOfCards = 8;
        } else {
            amountOfCards = 10;
        }
    }

    public void setAmountTreasureCard(int amountPlayers) {
        switch (card.getName()) {
            case "copper":
                amountOfCards = 60 - amountPlayers * 7;
                break;
            case "silver":
                amountOfCards = 40;
                break;
            case "gold":
                amountOfCards = 30;
                break;
        }
    }

    public void setAmountVictoryCard(int amountPlayers) {
        if (card.getPrice() > 0) {
            if (amountPlayers > 2) amountOfCards = 12;
            else amountOfCards = 8;
        } else amountOfCards = amountPlayers * 10 - 10;
    }

    public BasicCard getCard() {
        return card;
    }

    public int getPriceOfCard() {return card.getPrice();}

    public int getAmountOfCards() {
        return amountOfCards;
    }

    public void decrementStack() {
        amountOfCards--;
    }

}
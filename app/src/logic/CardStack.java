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

    private void setAmountOfCards(int amountPlayers) {
        if (ActionCard.class.isAssignableFrom(card.getClass())) setAmountActionCard(amountPlayers);
        else if (card.getClass().equals(TreasureCard.class)) setAmountTreasureCard(amountPlayers);
        else if (card.getClass().equals(VictoryCard.class)) setAmountVictoryCard(amountPlayers);
    }

    private void setAmountActionCard(int amountPlayers) {
        if (card.getClass().equals(Garden.class)) {
            if (amountPlayers > 2) amountOfCards = 12;
            else amountOfCards = 8;
        } else  {
            amountOfCards = 10;
        }
    }

    private void setAmountTreasureCard(int amountPlayers) {
        switch (card.getPrice()) {
            case 0:
                amountOfCards = 60-amountPlayers*7;
                break;
            case 3:
                amountOfCards = 40;
                break;
            case 6:
                amountOfCards = 30;
                break;
        }
    }

    private void setAmountVictoryCard(int amountPlayers) {
        if (card.getPrice() > 0) {
            if (amountPlayers > 2) amountOfCards = 12;
            else amountOfCards = 8;
        } else amountOfCards = amountPlayers*10-10;
    }

    public BasicCard getCard() {
        return card;
    }

    public int getAmountOfCards() {
        return amountOfCards;
    }

    public void decrementStack() {
        amountOfCards--;
    }

}
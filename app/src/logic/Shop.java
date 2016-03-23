package logic;

/**
 * Created by Indy Van Mol on 23/03/16.
 */

public class Shop {

    private int[] amountOfCards;
    private BasicCard[] cards;

    public Shop(int amountPlayers, TreasureCard copper, TreasureCard silver, TreasureCard gold, VictoryCard curse,
                VictoryCard estate, VictoryCard duchy, VictoryCard provincy, ActionCard actCard1, ActionCard actCard2
                , ActionCard actCard3, ActionCard actCard4, ActionCard actCard5, ActionCard actCard6 , ActionCard actCard7
                , ActionCard actCard8, ActionCard actCard9 , ActionCard actCard10) {
        CreateCardArray(actCard1, actCard2, actCard3, actCard4, actCard5, actCard6 , actCard7, actCard8, actCard9 , actCard10);
        CreateAmountArray(amountPlayers);
    }

    private void CreateCardArray(ActionCard... actCard) {
        cards = new BasicCard[17];
        for (int i = 0; i<actCard.length; i++) {
            for (int j = 4; i < 14; j++) {
                cards[j] = actCard[i];
            }
        }
    }

    private void CreateAmountArray(int amountPlayers) {
        amountOfCards = new int[17];
        setAmountVictoryCards(amountPlayers);
        setAmountTreasureCards(amountPlayers);
        setAmountActionCards();
    }

    private void setAmountVictoryCards(int amountPlayers) {
        amountOfCards[0] = amountPlayers*10-10;
        int amountVictoryCards = 8;
        if (amountPlayers > 2) amountVictoryCards = 12;
        for (int i = 1; i < 4; i++) {
            amountOfCards[i] = amountVictoryCards;
        }
    }

    private void setAmountTreasureCards(int amountPlayers) {
        amountOfCards[14] = 60-amountPlayers*7;
        amountOfCards[15] = 40;
        amountOfCards[16] = 30;
    }

    private void setAmountActionCards() {
        for (int i = 4; i < 14; i++) {
            amountOfCards[i] = 10;
            if (cards[i].getClass().equals(Garden.class)) amountOfCards[i] = amountOfCards[2];
        }
    }
}

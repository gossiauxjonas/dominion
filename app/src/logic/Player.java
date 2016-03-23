package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Indy Van Mol on 22/03/16.
 */

public class Player {

    private Random random = new Random(500);
    private List hand;
    private List discard;
    private List deck;

    public Player(TreasureCard copper, VictoryCard estate) {
        hand = new ArrayList<>();
        discard = new ArrayList<>();
        deck = createNewDeck(copper, estate);
        drawCardToHand(5);
    }

    private List createNewDeck(TreasureCard copper, VictoryCard estate) {
        List<Object> newDeck = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            newDeck.add(copper);
        }
        for (int i = 7; i < 10; i++) {
            newDeck.add(estate);
        }
        shuffle(newDeck);
        return newDeck;
    }

    public List getDeck() {
        return deck;
    }

    public List getHand() {
        return hand;
    }

    public int amountCardsDeck() {
        return deck.size();
    }

    public int amountCardsHand() {
        return hand.size();
    }

    public int amountCardsDiscard() {
        return discard.size();
    }

    public Object drawCardFromDeck() {
        return deck.remove(amountCardsDeck()-1);
    }

    public void drawCardToHand(int amount) {
        for(int i = 0; i < amount; i++) {
            if(amountCardsDeck() == 0) {
                fillDeck();
            }
            hand.add(drawCardFromDeck()); //remove() returns object that was removed
        }
    }

    public void discardCardFromHand(int place) {
        toDiscard(hand.remove(place));
    }

    public void emptyHand() {
        int cardsInHand = amountCardsHand();
        for (int i = 0; i < cardsInHand; i++) {
            discardCardFromHand(0);
        }
    }

    public void fillDeck() {
        int cardsInDiscard = amountCardsDiscard();
        for (int i = 0; i < cardsInDiscard; i++) {
            deck.add(discard.remove(0));
        }
        shuffle(deck);
    }

    public void endTurn() {
        emptyHand();
        drawCardToHand(5);
    }

    public Object playCard(int place) {
        return hand.get(place);
    }

    public void putDeckOnDiscard() {
        int cardsInDeck = amountCardsDeck();
        for (int i = 0; i < cardsInDeck; i++) {
            toDiscard(drawCardFromDeck());
        }
    }

    public void toDiscard(Object card) {
        discard.add(card);
    }

    public void shuffle(List cardArray) {
        for (int i = 0; i < cardArray.size()*10; i++) {
            int first = random.nextInt(cardArray.size());
            int second = random.nextInt(cardArray.size());
            Object temp = cardArray.get(first);
            cardArray.set(first, cardArray.get(second));
            cardArray.set(second, temp);
        }
    }

}
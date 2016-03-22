package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Indy Van Mol on 22/03/16.
 */

public class Player {

    Random random = new Random(500);
    private List hand;
    private List discard;
    private List deck;

    public Player(TreasureCard copper, VictoryCard estate) {
        hand = new ArrayList<>();
        discard = new ArrayList<>();
        deck = createNewDeck(copper, estate);
        drawCard(5);
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

    public List getDiscard() { return discard; }

    public void drawCard(int amount) {
        for(int i = 0; i < amount; i++) {
            if(deck.size() == 0) {
                fillDeck();
            }
            hand.add(deck.remove(deck.size()-1));
        }
    }

    public void removeCardFromHand(int place) {
        discard.add(hand.remove(place));
    }

    public void emptyHand() {
        int cardsInHand = hand.size();
        for (int i = 0; i < cardsInHand; i++) {
            removeCardFromHand(0);
        }
    }

    public void endTurn() {
        emptyHand();
        drawCard(5);
    }

    public void fillDeck() {
        List temp = discard;
        discard = null;
        shuffle(temp);
        deck = temp;
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

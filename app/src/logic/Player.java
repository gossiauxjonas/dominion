package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Indy Van Mol on 22/03/16.
 */

public class Player {

    private Random random = new Random();
    private String name;
    private List<BasicCard> hand;
    private List<BasicCard> discardDeck;
    private List<BasicCard> drawDeck;


    public Player(String name, TreasureCard copper, VictoryCard estate) {
        this.name = name;
        hand = new ArrayList<BasicCard>();
        discardDeck = new ArrayList<BasicCard>();
        drawDeck = createNewDeck(copper, estate);
        drawCardToHand(5);
    }

    //public Player(){}

    public List createNewDeck(TreasureCard copper, VictoryCard estate) {
        List<BasicCard> newDeck = new ArrayList<BasicCard>();
        for (int i = 0; i < 7; i++) {
            newDeck.add(copper);
        }
        for (int i = 0; i < 3; i++) {
            newDeck.add(estate);
        }
        shuffle(newDeck);
        return newDeck;
    }

    public List getDrawDeck() {
        return drawDeck;
    }

    public List getHand() {
        return hand;
    }

    public int amountCardsDeck() {
        return drawDeck.size();
    }

    public int amountCardsHand() {
        return hand.size();
    }

    public int amountCardsDiscard() {
        return discardDeck.size();
    }

    public BasicCard drawCardFromDeck() {
        return drawDeck.remove(amountCardsDeck() - 1);
    } //remove() returns BasicCard that was removed

    public void drawCardToHand(int amount) {
        for (int i = 0; i < amount; i++) {
            if (amountCardsDeck() == 0) {
                fillDeck();
            }
            hand.add(drawCardFromDeck());
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

    public void fillDeck() { //refactor drawDeck = shuffle(Discard);
        int cardsInDiscard = amountCardsDiscard();
        for (int i = 0; i < cardsInDiscard; i++) {
            drawDeck.add(discardDeck.remove(0));
        }
        shuffle(drawDeck);
    }

    public void endTurn() {
        emptyHand();
        drawCardToHand(5);
    }

    public BasicCard playCard(int place) {
        return hand.get(place);
    }

    public void putDeckOnDiscard() {
        int cardsInDeck = amountCardsDeck();
        for (int i = 0; i < cardsInDeck; i++) {
            toDiscard(drawCardFromDeck());
        }
    }

    public void toDiscard(BasicCard card) {
        discardDeck.add(card);
    }

    public void shuffle(List<BasicCard> cardArray) {
        for (int i = 0; i < cardArray.size() * 10; i++) {
            int first = random.nextInt(cardArray.size());
            int second = random.nextInt(cardArray.size());
            BasicCard temp = cardArray.get(first);
            cardArray.set(first, cardArray.get(second));
            cardArray.set(second, temp);
        }
    }

    public String getName() {
        return name;
    }

}
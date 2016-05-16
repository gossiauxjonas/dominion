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
        drawCardsToHand(5);
    }

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

    //Load saved Player TO DO

    public String getName() {
        return name;
    }

    //Hand
    public List getHand() {
        return hand;
    }

    public int amountCardsHand() {
        return hand.size();
    }

    public BasicCard getCardInHandOn(int index) {
        return hand.get(index);
    }

    //Draw Deck
    public List getDrawDeck() {
        return drawDeck;
    }

    public int amountCardsDeck() {
        return drawDeck.size();
    }

    //Discard Deck
    public List getDiscard() {return discardDeck; }

    public int amountCardsDiscard() {
        return discardDeck.size();
    }

    public BasicCard drawCardFromDeck() {
        if (amountCardsDeck() == 0) {
            fillDeck();
        }
        return drawDeck.remove(amountCardsDeck() - 1);
    }

    public BasicCard getCardOnTopOfDeck() {
        return drawDeck.get(amountCardsDeck()-1);
    }

    public void discardTopCardOfDeck() {
        toDiscard(drawCardFromDeck());
    }

    //Void methods
    public void drawCardsToHand(int amount) {
        for (int i = 0; i < amount; i++) {
            if (amountCardsDeck() == 0) {
                fillDeck();
            }
            if (amountCardsDeck() == 0) {
                return;
            }
            hand.add(drawCardFromDeck());
        }
    }

    public void putCardInHand(BasicCard card) {
        hand.add(card);
    }

    //public void putCardsInHand(List<BasicCard> cards) {hand.addAll(cards); }

    public void discardCardFromHand(int place) {
        toDiscard(hand.remove(place));
    }

    public void destroyCardFromHand(int place) { hand.remove(place); }

    public BasicCard removeCardFromHand(int place) { return hand.remove(place); }

    public void emptyHand() {
        discardDeck.addAll(hand);
        hand.clear();
    }

    public void fillDeck() { //refactor drawDeck = shuffle(Discard);
        int cardsInDiscard = amountCardsDiscard();
        drawDeck.addAll(discardDeck);
        discardDeck.clear();
        shuffle(drawDeck);
    }

    public void endTurn() {
        emptyHand();
        drawCardsToHand(5);
    }

    public void putDeckOnDiscard() {
        int cardsInDeck = amountCardsDeck();
        for (int i = 0; i < cardsInDeck; i++) {
            toDiscard(drawCardFromDeck());
        }
    }

    public Boolean handContainsVictory() {
        Boolean result = false;
        for (BasicCard card : hand) {
            if (card.getClass().equals(VictoryCard.class)) {
                result = true;
            }
        }
        return result;
    }

    public void putCardOnDrawDeck(BasicCard card) {
        drawDeck.add(card);
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

}
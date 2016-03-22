package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Indy Van Mol on 22/03/16.
 */

public class Player {
    Random random = new Random(25);
    private List hand;
    private List discard;
    private List deck;

    public Player(TreasureCard copper, VictoryCard estate) {
        hand = new ArrayList<>();
        discard = new ArrayList<>();
        deck = createNewDeck(copper, estate);
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

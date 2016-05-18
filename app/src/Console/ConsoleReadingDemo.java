package Console;

import logic.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Created by Jasper and Indy on 21/04/2016.
 */

public class ConsoleReadingDemo {

    private GameEngine game;

    public void printAll() {
        for (Player player : game.getPlayers()) {
            System.out.println("\n" + player.getName());
            List<BasicCard> allCards = new ArrayList<BasicCard>();
            allCards.addAll(player.getHand());
            allCards.addAll(player.getDiscard());
            allCards.addAll(player.getDrawDeck());
            for (BasicCard card : allCards) {
                System.out.println(card.getName());
            }
        }
    }

    public void printHand(Player player) {
        System.out.println(player.getName() + " his hand:");
        for (int i = 0; i < player.getHand().size(); i++)
            System.out.println("Card "+ i + ": " + player.getCardInHandOn(i).getName());
    }

    public void printShop(Shop shop) {
        System.out.println("Shop");
        for (int i = 0; i < shop.getShopArray().length; i++) {
            CardStack stack = shop.getShopArray()[i];
            System.out.println("Stack " + i + ": " + stack.getCard().getName() + " price: " + stack.getCard().getPrice() +" cards left: " + stack.getAmountOfCards());
        }
    }



    public void chapelAction() {
        int i = 4;
        while (i > 0) {
            printHand(game.getPlayer());
            int handSize = game.getPlayer().amountCardsHand();
            System.out.println("Choose a card to destroy or type " + handSize + " to quit, you can still destroy " + i + " more card(s)");
            int choice = choicePlay();
            if (choice == handSize) i = 0;
            else {
                game.getPlayer().destroyCardFromHand(choice);
                i--;
            }
        }
    }

    public void moneylenderAction() {
        if (game.getPlayer().getHand().contains(game.getCopper())) {
            int handSize = game.getPlayer().amountCardsHand();
            printHand(game.getPlayer());
            System.out.println("Choose a copper or type " + handSize + " to quit");
            int actionChoice = choicePlay();
            if (!(handSize == actionChoice)) {
                game.getPlayer().destroyCardFromHand(actionChoice);
                game.addTurnCoins(3);
            }
        } else {
            System.out.println("There is no copper in your hand!");
        }
    }

    public void cellarAction() {
        game.addTurnActions(1);
        int amountOfDiscardCards = 0;
        while (game.getPlayer().amountCardsHand() > 0) {
            int handSize = game.getPlayer().amountCardsHand();
            printHand(game.getPlayer());
            System.out.println("Choose a card to discard or type " + handSize + " to quit");
            int choice = choicePlay();
            if (choice == handSize) break;
            game.getPlayer().discardCardFromHand(choice);
            amountOfDiscardCards++;
        }
        game.getPlayer().drawCardsToHand(amountOfDiscardCards);
    }

    public void workshopAction() {
        printShop(game.getShop());
        System.out.println("Pick a card that costs 4 coins or less.");
        int choice = choicePlay();
        game.getPlayer().toDiscard(game.getShop().buyCard(choice));
    }

    public void feastAction() {
        printShop(game.getShop());
        System.out.println("Pick a card that costs 5 coins or less.");
        int choice = choicePlay();
        game.getPlayer().toDiscard(game.getShop().buyCard(choice));
    }

    public void remodelAction() {
        int maxCost = 2;
        printHand(game.getPlayer());
        System.out.println("Pick a card that you want to destroy");
        int choice = choicePlay();
        maxCost += game.getPlayer().getCardInHandOn(choice).getPrice();
        game.getPlayer().destroyCardFromHand(choice);
        printShop(game.getShop());
        System.out.println("Pick a card that costs "+ maxCost + " coins or less.");
        int buyChoice = choicePlay();
        game.getPlayer().toDiscard(game.getShop().buyCard(choice));
    }

    public void libraryAction() {
        while (game.getPlayer().amountCardsHand() != 7) {
            BasicCard pulledCard = game.getPlayer().drawCardFromDeck();
            System.out.println("The pulled card is " + pulledCard.getName());
            if (ActionCard.class.isInstance(pulledCard)) {
                System.out.println("If you want to hold this action card type 1 else type 0.");
                int coice = choicePlay();
                if (coice == 1) game.getPlayer().putCardInHand(pulledCard);
                else game.getPlayer().toDiscard(pulledCard);
            } else {
                game.getPlayer().putCardInHand(pulledCard);
            }
        }
    }

    public void mineAction() {
        if (game.getPlayer().handContainsTreasure()) {
            printHand(game.getPlayer());
            System.out.println("Pick a treasure card that you want to destroy");
            int choice = choicePlay();
            switch (game.getPlayer().getCardInHandOn(choice).getName()) {
                case "copper":
                    if (game.getShop().cardsLeftInStack(1) > 0) {
                        game.getPlayer().destroyCardFromHand(choice);
                        game.getPlayer().putCardInHand(game.getShop().buyCard(1));
                    } else {
                        System.out.println("There are no silver cards left");
                    }
                    break;
                case "silver":
                    if (game.getShop().cardsLeftInStack(2) > 0) {
                        game.getPlayer().destroyCardFromHand(choice);
                        game.getPlayer().putCardInHand(game.getShop().buyCard(2));
                    } else {
                        System.out.println("There are no Gold cards left");
                    }
                    break;
                case "gold":
                    if (game.getShop().cardsLeftInStack(2) > 0) {
                        game.getPlayer().destroyCardFromHand(choice);
                        game.getPlayer().putCardInHand(game.getShop().buyCard(2));
                    } else {
                        System.out.println("There are no Gold cards left");
                    }
                    break;
            }
        } else {
            System.out.println("You have no Treasure cards in your hand!");
        }

    }

    public Boolean doesPlayerReact(Player player) {
        if (player.getHand().contains(game.getMoat())) {
            System.out.println(player.getName() + " if you want to react type 0 if you don't type 1.");
            int choice = choicePlay();
            return choice != 1;
        } else {
            return false;
        }
    }

    public void spyAction() {
        game.getPlayer().drawCardsToHand(1);
        game.addTurnActions(1);
        for (Player player : game.getOtherPlayers()) {
            if (!doesPlayerReact(player)) {
                if (player.getDiscard().size() > 0 || player.getDrawDeck().size() > 0) {
                    BasicCard cardOnTopOfDeck = player.getCardOnTopOfDeck();
                    System.out.println(cardOnTopOfDeck.getName() + " is the card on top of " + player.getName() + "s deck.");
                    System.out.println("Type 0 to let it lay or 1 to discard");
                    int choice = choicePlay();
                    if (choice == 1) player.discardTopCardOfDeck();
                } else  {
                    System.out.println(player.getName() + " has no cards to draw.");
                }
            }
        }

        if (game.getPlayer().getDiscard().size() > 0 || game.getPlayer().getDrawDeck().size() > 0) {
            BasicCard cardOnTopOfDeck = game.getPlayer().getCardOnTopOfDeck();
            System.out.println(cardOnTopOfDeck.getName() + " is the card on top of your deck.");
            System.out.println("Type 0 to let it lay or 1 to discard");
            int choice = choicePlay();
            if (choice == 1) game.getPlayer().discardTopCardOfDeck();
        } else {
            System.out.println("there are no cards you can draw.");
        }
    }

    public void witchAction() {
        game.getPlayer().drawCardsToHand(2);
        for (Player player : game.getOtherPlayers()) {
            if (!doesPlayerReact(player)) {
                if (game.getShop().cardsLeftInStack(16) > 0) {
                    player.toDiscard(game.getShop().buyCard(16));
                } else {
                    System.out.println("there are no curse cards left to give to " + player.getName() + "!");
                }
            }
        }
    }

    public void thiefAction() {
        List<BasicCard> allDestoryedCards = new ArrayList<BasicCard>();
        for (Player player : game.getOtherPlayers()) {
            if (!doesPlayerReact(player)) {
                System.out.println(player.getName() + "s cards are");
                List<BasicCard> pulledCards = new ArrayList<BasicCard>();
                List<BasicCard> useless = new ArrayList<BasicCard>();
                System.out.println("discard" + player.getDiscard().size());
                System.out.println("draw" + player.getDrawDeck().size());
                for (int i = 0; i < 2; i++) {
                    if (player.getDiscard().size() > 0 || player.getDrawDeck().size() > 0) {
                        BasicCard card = player.drawCardFromDeck();
                        System.out.println(card.getName());
                        if (card.getClass().equals(TreasureCard.class)) {
                            pulledCards.add(card);
                        } else {
                            useless.add(card);
                        }
                    } else {
                        System.out.println("There are no more cards to pull from " + player.getName() + "!");
                        break;
                    }
                }
                player.getDiscard().addAll(useless);
                switch (pulledCards.size()) {
                    case 1:
                        allDestoryedCards.add(pulledCards.remove(0));
                        break;
                    case 2:
                        for (int i = 0; i < 2; i++) {
                            System.out.println(i + ": " + pulledCards.get(i).getName());
                        }
                        System.out.println("Type the number of the card you want to destroy");
                        int choice = choicePlay();
                        allDestoryedCards.add(pulledCards.remove(choice));
                        player.toDiscard(pulledCards.remove(0));
                        break;
                }
            }
            }
        if (allDestoryedCards.size() > 0) {
            while (allDestoryedCards.size() > 0) {
                for (int i = 0; i < allDestoryedCards.size(); i++) {
                    System.out.println(i + ": " + allDestoryedCards.get(i).getName());
                }
                System.out.println("type the number of the card you want to steal or type " + allDestoryedCards.size() + " to destroy all.");
                int choice = choicePlay();
                if (choice == allDestoryedCards.size()) {
                    allDestoryedCards.clear();
                } else {
                    game.getPlayer().toDiscard(allDestoryedCards.remove(choice));
                }
            }
        }
        }

    public void militiaAction() {
        game.addTurnCoins(2);
        for (Player player : game.getOtherPlayers()) {
            if (!doesPlayerReact(player))
                while (player.amountCardsHand() > 3) {
                    printHand(player);
                    System.out.println("Type the number of the card you want to discard.");
                    int choice = choicePlay();
                    player.destroyCardFromHand(choice);
                }
            }
        }
    public void bureaucratAction() {
        game.getPlayer().putCardOnDrawDeck(game.getShop().buyCard(1));
        for (Player player : game.getOtherPlayers()) {
            if (!doesPlayerReact(player)) {
                printHand(player);
                if (player.handContainsVictory()) {
                    System.out.println("Type the number of the card you want to put back on your drawdeck");
                    int choice = choicePlay();
                    player.putCardOnDrawDeck(player.removeCardFromHand(choice));
                }
            }
        }

    }

    public void defaultAction(ActionCard card) {
        card.playAction();
    }

    public void throneRoomAction() {
        printHand(game.getPlayer());
        System.out.println("Choose a action card to play twice");
        int actionChoice = choicePlay();
            if (game.getPlayer().getCardInHandOn(actionChoice).getName() == "throne room") {
                playAction(actionChoice);
            } else {
                BasicCard cardToPlayTwice = game.getPlayer().removeCardFromHand(actionChoice);
                game.getPlayer().putCardInHand(cardToPlayTwice);
                playAction(game.getPlayer().amountCardsHand()-1, false);
                game.getPlayer().putCardInHand(cardToPlayTwice);
                playAction(game.getPlayer().amountCardsHand()-1, true);
            }
    }

    public void adventurerAction() {
        for (int i = 0; i < 2; i++) {
            if (game.playerHasTreasure(game.getPlayer())) {
                BasicCard pulledCard = game.getPlayer().drawCardFromDeck();
                System.out.println("You have drawn a " + pulledCard.getName() + ".");
                while (!(pulledCard.getClass().equals(TreasureCard.class))) {
                    game.getPlayer().toDiscard(pulledCard);
                    pulledCard = game.getPlayer().drawCardFromDeck();
                    System.out.println("You have drawn a " + pulledCard.getName() + ".");
                }
                game.getPlayer().putCardInHand(pulledCard);
            } else {
                System.out.println("There aren't any treasure cards left in your decks!");
                break;
            }
        }
    }

    public void playAction(int indexCardInHand) {
        playAction(indexCardInHand, true);
    }

    public void playAction(int indexCardInHand, Boolean removeCard) {
        System.out.println("Play action " + game.getPlayer().getCardInHandOn(indexCardInHand).getName());
        ActionCard card = (ActionCard) game.getPlayer().getCardInHandOn(indexCardInHand);
            switch (card.getName()) {
                case "throne room":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    throneRoomAction();
                    break;
                case "chapel":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    chapelAction();
                    break;
                case "moneylender":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    moneylenderAction();
                    break;
                case "cellar":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    cellarAction();
                    break;
                case "workshop":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    workshopAction();
                    break;
                case "feast":
                    game.getPlayer().destroyCardFromHand(indexCardInHand);
                    feastAction();
                    break;
                case "remodel":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    remodelAction();
                    break;
                case "library":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    libraryAction();
                    break;
                case "mine":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    mineAction();
                    break;
                case "spy":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    spyAction();
                    break;
                case "witch":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    witchAction();
                    break;
                case "thief":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    thiefAction();
                    break;
                case "militia":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    militiaAction();
                    break;
                case "bureaucrat":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    bureaucratAction();
                    break;
                case "adventurer":
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    adventurerAction();
                    break;
                default:
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
                    else game.getPlayer().destroyCardFromHand(indexCardInHand);
                    defaultAction(card);
        }
    }

    public int choicePlay() {
        Scanner in = new Scanner(System.in);
        System.out.print("");
        return in.nextInt();
    }

    public void turn() {
        game.startNewturn();
        System.out.println();
        System.out.println(game.getPlayer().getName() + "s Turn");
        System.out.println("Action Fase");
        while (game.getTurnActions() > 0) {
            System.out.println();
            System.out.println("Actions left: " + game.getTurnActions());
            printHand(game.getPlayer());
            int actionChoice = choicePlay();
            if (actionChoice == game.getPlayer().getHand().size()) {
                game.endTurnActions();
            } else {
                game.decrementTurnActions();
                playAction(actionChoice);
                if (!game.getShop().isOpen()) return;
            } }
            System.out.println("Buy Fase");
            int coins = game.calculateTreasureInHand() + game.getTurnCoins();
            while (game.getTurnBuys() > 0) {
                System.out.println();
                printHand(game.getPlayer());
                System.out.println();
                System.out.println("Buys left: " + game.getTurnBuys());
                printShop(game.getShop());
                System.out.println("coins: " + coins);
                int buyChoice = choicePlay();
                if (buyChoice == 17) {
                    game.endTurnBuys();
                } else {
                    game.getPlayer().toDiscard(game.getShop().buyCard(buyChoice));
                    coins -= game.getShop().priceOfCard(buyChoice);
                    game.decrementTurnBuys();
                    if (!game.getShop().isOpen()) return;
                }
                System.out.println("******************************************************************");
            }
            game.getPlayer().endTurn();
            game.nextTurn();
            System.out.println();
            System.out.println("------------------------------------------------------------------");
    }

    private void run() {
        Scanner in = new Scanner(System.in);

        System.out.print("Name player1: ");
        String player1 = (String) in.next();
        System.out.print("Name player2: ");
        String player2 = (String) in.next();
        //System.out.print("Name player3: ");
        //String player3 = (String) in.next();
        System.out.println("The players are: "+ player1 + " and "+ player2);
        game = new GameEngine(player1, player2);
        while (game.getShop().isOpen()) {
            turn();
        }
        printAll();
        int[][] playerRank = game.playerScoreRank();
        System.out.println("The winner is: " + game.getPlayers()[playerRank[0][0]].getName() + " with " + playerRank[0][1] + " points");
        System.out.println(game.getPlayers()[playerRank[1][0]].getName() + " has " + playerRank[1][1] + " points");
    }

    public static void main(String[] args) {
        ConsoleReadingDemo consoleReadingDemo = new ConsoleReadingDemo();
        consoleReadingDemo.run();
    }

}

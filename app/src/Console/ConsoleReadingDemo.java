package Console;

import logic.*;

import java.util.Scanner;

/**
 * Created by Jasper and Indy on 21/04/2016.
 */

public class ConsoleReadingDemo {

    private GameEngine game;

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

    public void throneRoomAction() {
        int quit = game.getPlayer().amountCardsHand();
        printHand(game.getPlayer());
        System.out.println("Choose a action card to play twice or " + quit + " to leave.");
        int actionChoice = choicePlay();
        if (actionChoice == quit) return;
        else {
            if (game.getPlayer().getCardInHandOn(actionChoice).getName() == "throne room") {
                playAction(actionChoice, true);
            } else {
                playAction(actionChoice, false);
                playAction(actionChoice, true);
            }
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

    public void defaultAction(ActionCard card) {
        card.playAction();
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
                    chapelAction();
                    break;
                default:
                    if (removeCard) game.getPlayer().discardCardFromHand(indexCardInHand);
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
        int[][] playerRank = game.playerScoreRank();
        System.out.println("The winner is: " + game.getPlayers()[playerRank[0][0]].getName() + " with " + playerRank[0][1] + " points");
        System.out.println(game.getPlayers()[playerRank[1][0]].getName() + " has " + playerRank[1][1] + " points");
    }

    public static void main(String[] args) {
        ConsoleReadingDemo consoleReadingDemo = new ConsoleReadingDemo();
        consoleReadingDemo.run();
    }

}

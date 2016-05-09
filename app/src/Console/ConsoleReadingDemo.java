package Console;

import logic.*;

import java.util.Scanner;

/**
 * Created by jasper on 21/04/2016.
 */

public class ConsoleReadingDemo {

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

    public int choicePlay() {
        Scanner in = new Scanner(System.in);
        System.out.print("");
        return in.nextInt();
    }

    public void turn(GameEngine game) {
        game.startNewturn();
        System.out.println();
        System.out.println(game.getPlayer().getName() + "s Turn");
        System.out.println("Action Fase");
        while (game.getTurnActions() > 0) {
            System.out.println();
            System.out.println("Actions left: " + game.getTurnActions());
            printHand(game.getPlayer());
            int choice = choicePlay();
            if (choice == game.getPlayer().getHand().size()) {
                game.endTurnActions();
            } else {
                game.decrementTurnActions();
                System.out.println("Play action ");
                ActionCard card = (ActionCard) game.getPlayer().getCardInHandOn(choice);
                card.playAction();
                game.getPlayer().discardCardFromHand(choice);
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
                int choice2 = choicePlay();
                if (choice2 == 17) {
                    game.endTurnBuys();
                } else {
                    game.getPlayer().toDiscard(game.getShop().buyCard(choice2));
                    coins -= game.getShop().priceOfCard(choice2);
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

        GameEngine game = new GameEngine(player1, player2);

        while (game.getShop().isOpen()) {
            turn(game);
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

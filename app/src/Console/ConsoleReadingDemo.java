package Console;

import logic.CardStack;
import logic.GameEngine;
import logic.Player;
import logic.Shop;

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
        printHand(game.getPlayer());
        int actions = 0;
        int buys = 1;
        while (actions > 0) {
            System.out.println("actions left: " + actions);
            int choice = choicePlay();
            if (choice == game.getPlayer().getHand().size()) {
                actions = 0;
            } else {
                if (!game.getShop().isOpen()) return;
            } }
            while (buys > 0) {
                printShop(game.getShop());
                System.out.println("coins: " + game.calculateTreasureInHand());
                int choice2 = choicePlay();
                if (choice2 == 17) {
                    buys = 0;
                } else {
                    game.getPlayer().toDiscard(game.getShop().buyCard(choice2));
                    buys--;
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
        System.out.println("The players are: "+ player1 + " and "+ player2);
        System.out.println();

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

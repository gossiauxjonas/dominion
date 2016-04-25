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
        System.out.println();
        printHand(game.getPlayer());
        int actions = 1;
        int buys = 1;
        while (actions > 0) {
            System.out.println("actions left: " + actions);
            int choice = choicePlay();
            if (choice == game.getPlayer().getHand().size()) {
                actions = 0;
            } else {

            }
            while (buys > 0) {
                printShop(game.getShop());
                System.out.println("coins: " + game.calculateTreasure());
                int choice2 = choicePlay();
                if (choice2 == 17) {
                    buys = 0;
                } else {
                    game.getPlayer().toDiscard(game.getShop().buyCard(choice2));
                    buys--;
                }
                System.out.println("******************************************************************");

            }
            game.getPlayer().endTurn();
            game.nextTurn();
            System.out.println();
            System.out.println("----------------------------------------------------------------------");

        }

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
    }

    public static void main(String[] args) {
        ConsoleReadingDemo consoleReadingDemo = new ConsoleReadingDemo();
        consoleReadingDemo.run();
    }

}

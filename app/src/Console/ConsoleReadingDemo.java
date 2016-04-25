package Console;

import logic.*;

import java.util.List;
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

    public int actionPlay(GameEngine game) {
        Scanner in = new Scanner(System.in);
        System.out.print("Play an action card nummer or " + game.getPlayer(game.getPlayerTurn()).getHand().size() + " to play treaser");
        return in.nextInt();
    }

    public int calculateTreasure(GameEngine game) {
        int coinsInHand = 0;
        List<BasicCard> hand = game.getPlayer(game.getPlayerTurn()).getHand();
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getClass().equals(TreasureCard.class)) {
                TreasureCard temp = (TreasureCard) hand.get(i);
                coinsInHand += (temp.getCoinValue());
            }
        }
        return coinsInHand;
    }

    public void turn(GameEngine game) {
        printShop(game.getShop());
        System.out.println();
        printHand(game.getPlayer(game.getPlayerTurn()));
        int actions = 1;
        while (actions > 0) {
            System.out.println("actions left: " + actions);
            int choice = actionPlay(game);
            if (choice == game.getPlayer(game.getPlayerTurn()).getHand().size()) {
                actions = 0;
            }
            int treasure = calculateTreasure(game);
            System.out.println("coins: " + treasure);
            game.getPlayer(game.getPlayerTurn()).endTurn();
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

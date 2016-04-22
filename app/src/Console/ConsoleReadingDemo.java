package Console;

import logic.GameEngine;
import logic.Player;

import java.util.Scanner;

/**
 * Created by jasper on 21/04/2016.
 */
public class ConsoleReadingDemo {

    public static void printHand(Player player) {
        System.out.println(player.getName() + " his hand:");
        System.out.println(player.getHand());
    }

    public static void main(String[] args) {
        int playersTurn = 0;
        Scanner in = new Scanner(System.in);

        System.out.print("Name player1: ");
        String player1 = (String) in.next();
        System.out.print("Name player2: ");
        String player2 = (String) in.next();
        System.out.println("The players are: "+ player1 + " and "+ player2);
        System.out.println();

        GameEngine game = new GameEngine(player1, player2);
        printHand(game.getPlayer(playersTurn));

    }


}

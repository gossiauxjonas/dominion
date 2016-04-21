package Console;

import logic.GameEngine;

import java.util.Scanner;

/**
 * Created by jasper on 21/04/2016.
 */
public class ConsoleReadingDemo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //int i = in.nextInt();
        System.out.print("Name player1: ");
        String player1 = in.next();
        System.out.print("Name player2: ");
        String player2 = in.next();
        System.out.println();
        System.out.println("The players are: "+ player1 + " and "+ player2);
        GameEngine game = new gameLogic(player1, player2);
        



    }



}

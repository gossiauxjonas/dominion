package Console;

import java.util.Scanner;

/**
 * Created by jasper on 21/04/2016.
 */
public class ConsoleReadingDemo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //int i = in.nextInt();
        System.out.print("Name player1: ");
        String s = in.next();
        System.out.println();
        System.out.println(s);
    }



}

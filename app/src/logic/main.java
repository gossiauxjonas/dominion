package logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Indy Van Mol on 22/03/16.
 */

public class main {

    public static void main(String [ ] args) {
        /*List<Integer> myList = new ArrayList<Integer>();
        System.out.println(myList);
        System.out.println(myList.size());
        myList.add(0, 15);
        myList.add(16);
        myList.add(17);
        System.out.println(myList);
        myList.add(1, 9);
        System.out.println(myList);
        System.out.println(myList.remove(1));
        System.out.println(myList);

        Random random = new Random(25);
        for (int i = 0; i < 50; i++) {
            System.out.println(random.nextInt(3));
        }*/
        TreasureCard copper = new TreasureCard("copper", 0, 1);
        VictoryCard estate = new VictoryCard("estate", 2, 1);
        Player player1 = new Player(copper, estate);
        System.out.println(player1.getDeck().size());
        System.out.println(player1.getHand().size());
    }

}

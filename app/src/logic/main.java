package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 23/03/16.
 */
public class main {

    public static void main(String[] args) {
        BasicCard tuin = new Garden();
        System.out.println(tuin.getClass().equals(Garden.class));
        System.out.println(tuin.getClass().equals(BasicCard.class));
        System.out.println(BasicCard.class.isAssignableFrom(tuin.getClass()));
        System.out.println(tuin.getClass().isAssignableFrom(BasicCard.class));
        System.out.println(60-2*7);
    }
}

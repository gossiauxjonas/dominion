package logic;

/**
 * Created by Indy on 23/03/16.
 */

public class ActionCard extends BasicCard {

    protected GameEngine game;

    public ActionCard(String name, int price, GameEngine game) {
        super(name, price);
        this.game = game;
    }

    public void playAction() {}

}
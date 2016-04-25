package logic;

/**
 * Created by Indy Van Mol on 21/03/16.
 */

public class BasicCard {

    private String name;
    private int price;

    public BasicCard(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() { return name; }

}
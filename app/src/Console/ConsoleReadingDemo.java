package Console;

/**
 * Created by jasper on 21/04/2016.
 */
import java.io.Console;

public class ConsoleReadingDemo {
    public static void main(String[] args) {

        Console cnsl = null;
        String name = null;

        try{
            // creates a console object
            cnsl = System.console();

            // if console is not null
            if (cnsl != null) {

                // read line from the user input
                name = cnsl.readLine("Name: ");

                // prints
                System.out.println("Name entered : " + name);
            }
        }catch(Exception ex){

            // if any error occurs
            ex.printStackTrace();
        }
    }



}

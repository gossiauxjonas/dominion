package backend;

import jdk.nashorn.internal.parser.JSONParser;
import jdk.nashorn.internal.runtime.JSONFunctions;
import logic.GameEngine;
import logic.Player;
import logic.Shop;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;


public class DominionServlet extends javax.servlet.http.HttpServlet {

    // dit is een bug;
    private GameEngine gameEngine;

    public void startNewGame(PrintWriter pw, String firstplayer, String otherPlayer, String lastPlayer) {

        if (lastPlayer != null) {
            gameEngine = new GameEngine(firstplayer, otherPlayer, lastPlayer);
            pw.write(firstplayer + otherPlayer + lastPlayer);

        } else {
            gameEngine = new GameEngine(firstplayer, otherPlayer);
            pw.write(firstplayer + otherPlayer);
        }
    }

    public String[] sendChosenCards(Shop shop) {
        String[] actionCards = new String[10];
        for (int i = 0; i < 10; i++) {
            actionCards[i] = shop.getShopArray()[i + 3].getCard().getName();
        }
        return actionCards;
    }


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();


        // String cardArray = request.getParameter("data");


        //GameEngine gameEngine = (GameEngine) request.getServletContext().getAttribute("gameEngine");
        if (gameEngine == null) {
            //gameEngine = new GameEngine();
            //request.getServletContext().setAttribute("gameEngine", gameEngine);
        }





       // String json = request.getParameter("json");

        //  JSONParser parser = new JSONParser(json);

        String operation = "init";
       // operation = request.getParameter("operation");
        System.out.println(operation);

        switch (operation) {
            case "init":
                String firstplayer = request.getParameter("player1");
                String otherPlayer = request.getParameter("player2");
                String lastPlayer = request.getParameter("player3");

                startNewGame(pw, firstplayer, otherPlayer, lastPlayer);


                String[] sendChosenCards = sendChosenCards(gameEngine.getShop());
                for (String card : sendChosenCards) {
                    System.out.println( card.toString());

                }


                response.sendRedirect("/playfield.html");

                operation = request.getParameter("operation");


                break;


            // verwacht bv parameters: nrPlayers en name1 tot namen met n = aantal spelers
            // roep bv method op waar je request aan meegeeft
            // en die leest de vereiste parameters
            // en roept uiteindelijk een method op GameEngine op om de gevraagde
            // acties uit te voeren

            // en uiteindelijk ook een method om bv de hele toestand van het spel nu
            // (= bv wie is aan de beurt, welke kaarten in hand van speler 1,
            // welke op tafel, idem voor speler 2, welke global pile...)

            case "loop":

                System.out.println("in loop");

                break;

            default:

                break;
        }
    }
}

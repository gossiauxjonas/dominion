package backend;

import logic.GameEngine;
import logic.Shop;
<<<<<<< HEAD
<<<<<<< HEAD
import org.json.simple.*;
=======
import org.json.JSONArray;
>>>>>>> 82fa8aba640903058e7f12adfcb976119379fd0c
=======
import org.json.JSONArray;
import org.json.JSONObject;
>>>>>>> parent of 24535ba... JSON problems with servlet solved

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;




public class DominionServlet extends javax.servlet.http.HttpServlet {

    // dit is een bug;
    private GameEngine gameEngine;

    private void startNewGame(PrintWriter pw, String firstplayer, String otherPlayer, String lastPlayer) {

        if (lastPlayer != null) {
            gameEngine = new GameEngine(firstplayer, otherPlayer, lastPlayer);
            this.getServletContext().setAttribute("gameEngine", gameEngine);
            pw.write(firstplayer + otherPlayer + lastPlayer);

        } else {
            gameEngine = new GameEngine(firstplayer, otherPlayer);
            this.getServletContext().setAttribute("gameEngine", gameEngine);
            pw.write(firstplayer + otherPlayer);
        }
    }


    private String[] sendChosenCards(Shop shop) {
        String[] actionCards = new String[10];
        for (int i = 0; i < 10; i++) {
            actionCards[i] = shop.getShopArray()[i + 3].getCard().getName();
        }
        return actionCards;
    }



        public String parseJSONarray(String[] array)
      {

               JSONArray jsonArray = new JSONArray();

          for (String anArray : array) {

              jsonArray.put(anArray);

          }


               return jsonArray.toString();
           }


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();

        String operation;
        operation = request.getParameter("operation");
        System.out.println(operation + "operationTest");


        switch (operation) {


            case "init":
                System.out.println("in init");

                Initialize(request, response);


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
                pw.append(" { 'status':'nok', 'errormessage':'Invalid Operation' } ");
                break;
        }

    }





    private void Initialize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter pw = response.getWriter();


        this.getServletContext().setAttribute("gameEngine", gameEngine);

        String firstplayer = request.getParameter("player1");
        String otherPlayer = request.getParameter("player2");
        String lastPlayer = request.getParameter("player3");


        startNewGame(pw, firstplayer, otherPlayer, lastPlayer);


        String[] sendChosenCards = sendChosenCards(gameEngine.getShop());

        System.out.println(firstplayer+otherPlayer+lastPlayer);
        System.out.println(sendChosenCards.length);

        String geparsteArray = parseJSONarray(sendChosenCards);


        for (int i = 0; i < sendChosenCards.length; i++) {

            System.out.println("lol");

        }

      System.out.println(geparsteArray+"jsonobjectTest");
       pw.write(geparsteArray);









    }

}
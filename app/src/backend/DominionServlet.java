package backend;

import logic.GameEngine;
import logic.Shop;


import org.json.JSONArray;
import org.json.JSONObject;

import org.json.simple.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class DominionServlet extends javax.servlet.http.HttpServlet {


    private GameEngine gameEngine;
    private int[] startCardArray = new int[10];
    String firstplayer;
    String otherPlayer;
    String lastPlayer;


    private void startNewGame(int[] startCardArray, PrintWriter pw, String firstplayer, String otherPlayer, String lastPlayer) {

        if (lastPlayer != null) {
            gameEngine = new GameEngine(startCardArray, firstplayer, otherPlayer, lastPlayer);
            this.getServletContext().setAttribute("gameEngine", gameEngine);
            pw.write(firstplayer + otherPlayer + lastPlayer);

        } else {
            gameEngine = new GameEngine(startCardArray, firstplayer, otherPlayer);
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


    private JSONArray parseJSONarray(String[] array) {

        JSONArray jsonArray = new JSONArray();

        for (String anArray : array) {

            jsonArray.put(anArray);

        }


        return jsonArray;
    }


    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


    }


    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();

        String operation;
        operation = request.getParameter("operation");


        switch (operation) {


            case "init":
                this.firstplayer = request.getParameter("player1");
                this.otherPlayer = request.getParameter("player2");
                this.lastPlayer = request.getParameter("player3");

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

                JSONObject startObject = new JSONObject();

                String[] sendChosenCards = sendChosenCards(gameEngine.getShop());

                startObject.put("shopCards", parseJSONarray(sendChosenCards));


                pw.write(startObject.toString());


                break;

            case "startingCards":

                JSONArray startingCards = new JSONArray(request.getParameter("json"));

                for (int i = 0; i < startingCards.length(); i++) {

                    this.startCardArray[i] = startingCards.optInt(i);

                }

                Initialize(request, response, startCardArray);



                break;



            case "turn":



                break;

            default:
                pw.append(" { 'status':'nok', 'errormessage':'Invalid Operation' } ");
                break;
        }

    }


    private void Initialize(HttpServletRequest request, HttpServletResponse response, int[] startCardArray) throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        this.getServletContext().setAttribute("gameEngine", gameEngine);
        startNewGame(startCardArray, pw, firstplayer, otherPlayer, lastPlayer);


    }

}
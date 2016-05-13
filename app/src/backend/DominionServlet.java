package backend;

import jdk.nashorn.internal.runtime.JSONFunctions;
import logic.GameEngine;
import logic.Player;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Jonas on 14/04/16.
 */

public class DominionServlet extends javax.servlet.http.HttpServlet {

    // dit is een bug;
    private GameEngine gameEngine;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {


        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();












        //GameEngine gameEngine = (GameEngine) request.getServletContext().getAttribute("gameEngine");
        if(gameEngine == null)
        {
            //gameEngine = new GameEngine();
            //request.getServletContext().setAttribute("gameEngine", gameEngine);
        }




        // operation=

        String operation = "init";
        //request.getParameter("operation");

        switch(operation)
        {
            case "init":
                String firstplayer= request.getParameter("player1");
                String otherPlayer= request.getParameter("player2");
                String lastPlayer = request.getParameter("player3");


                if(lastPlayer != null){
                    gameEngine = new GameEngine(firstplayer,otherPlayer,lastPlayer);
                    pw.write(firstplayer+otherPlayer+lastPlayer);

                }
                else {
                    gameEngine = new GameEngine(firstplayer,otherPlayer);
                    pw.write(firstplayer+otherPlayer);
                }

                // verwacht bv parameters: nrPlayers en name1 tot namen met n = aantal spelers
                // roep bv method op waar je request aan meegeeft
                // en die leest de vereiste parameters
                // en roept uiteindelijk een method op GameEngine op om de gevraagde
                // acties uit te voeren

                // en uiteindelijk ook een method om bv de hele toestand van het spel nu
                // (= bv wie is aan de beurt, welke kaarten in hand van speler 1,
                // welke op tafel, idem voor speler 2, welke global pile...)


            default:
        }
    }
}

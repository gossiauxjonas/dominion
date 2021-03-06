package backend;

import logic.*;


import org.json.JSONArray;
import org.json.JSONObject;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * Created by Jonas Gossiaux.
 */
public class DominionServlet extends javax.servlet.http.HttpServlet {


    private GameEngine game;
    private int[] startCardArray = new int[10];
    String firstplayer;
    String otherPlayer;
    String lastPlayer;


    private void startNewGame(int[] startCardArray, PrintWriter pw, String firstplayer, String otherPlayer, String lastPlayer) {

        if (lastPlayer != "") {
            game = new GameEngine(startCardArray, firstplayer, otherPlayer, lastPlayer);
            this.getServletContext().setAttribute("gameEngine", game);
            pw.write(firstplayer + otherPlayer + lastPlayer);

        } else {
            game = new GameEngine(startCardArray, firstplayer, otherPlayer);
            this.getServletContext().setAttribute("gameEngine", game);
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

    private int getPlaceInShop(String card, Shop shop) {
        for (int i = 0; i < shop.getShopArray().length; i++) {
            if (shop.getShopArray()[i].getCard().getName().equals(card)) {
                return i;
            }

        }
        return 0;
    }


    private JSONArray parseJSONarray(String[] array) {

        JSONArray jsonArray = new JSONArray();

        for (String anArray : array) {

            jsonArray.put(anArray);

        }


        return jsonArray;
    }


    public List makeHandArray(Player player) {
        List<String> hand = new ArrayList<String>();
        for (int i = 0; i < player.getHand().size(); i++)
            hand.add(player.getCardInHandOn(i).getName());
        return hand;
    }


    public JSONObject turn() {
        JSONObject playerTurn = new JSONObject();
        game.startNewturn();
        playerTurn.put("playerName", game.getPlayer().getName());
        playerTurn.put("actions", game.getTurnActions());
        playerTurn.put("buys", game.getTurnActions());

        playerTurn.put("hand", makeHandArray(game.getPlayer()));

        playerTurn.put("treasure", game.getTurnCoins());


        if (!game.getPlayer().handContainsActionCards()) {
            playerTurn.put("actionsInHand", "false");
            game.endTurnActions();
        } else {
            playerTurn.put("actionsInHand", "true");
        }


        return playerTurn;


    }


    private void doAction(String cardName, int cardPlaceInHand) {

        ActionCard card = (ActionCard) game.getPlayer().getCardInHandOn(cardPlaceInHand);

        switch (cardName) {

            case "chapel":

                defaultAction(card);
                 break;
            case "moneylender":

                break;
            case "cellar":

                break;
            case "workshop":

                break;
            case "feast":
                game.getPlayer().destroyCardFromHand(findCardPlaceInHand("feast"));
                break;
            case "remodel":

                break;
            case "library":

                break;
            case "mine":

                break;
            case "spy":

                break;
            case "witch":

                break;
            case "thief":

                break;
            case "militia":

                break;
            case "bureaucrat":

                break;

            case "throne room":

                break;

            case "adventurer":

                break;


            default:
                defaultAction(card);

        }
        game.getPlayer().discardCardFromHand(cardPlaceInHand);
    }

    private void defaultAction(ActionCard actionCard){
        actionCard.playAction();
    }

    private int findCardPlaceInHand(String cardName) {
        int cardPlace = -1;
        for (int i = 0; i < game.getPlayer().getHand().size(); i++) {


            if (game.getPlayer().getCardInHandOn(i).getName().toString().equals(cardName)) {
                cardPlace = i;

            }
        }

        return cardPlace;
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


            case "beginSetup":

                JSONObject startObject = new JSONObject();

                String[] sendChosenCards = sendChosenCards(game.getShop());

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


            case "startTurn":
                System.out.println("startTurn operation is ok ");

                // while (game.getShop().isOpen()) {
                if (game.getPlayer().amountCardsHand() < 5) {
                    game.getPlayer().drawCardsToHand(5);
                }
                System.out.println(turn().toString());
                pw.write(turn().toString());


                break;

            case "playTreasure":
                JSONObject treasureObject = new JSONObject();
                int coins = game.getTurnCoins() + game.calculateTreasureInHand();
                treasureObject.put("coins", coins);
                pw.write(treasureObject.toString());

                break;

            case "buyCard":
                JSONObject endOfBuy = new JSONObject();
                if ((game.getTurnBuys() > 0)) {
                    String Card = request.getParameter("cardPlace");
                    int treasureLeft = Integer.parseInt(request.getParameter("coins"));

                    int CardPlaceInShop = getPlaceInShop(Card, game.getShop());
                    if (treasureLeft >= game.getShop().priceOfCard(CardPlaceInShop) && game.getShop().getShopArray()[CardPlaceInShop].getAmountOfCards() > 0) {
                        game.getPlayer().toDiscard(game.getShop().buyCard(CardPlaceInShop));
                        treasureLeft -= game.getShop().priceOfCard(CardPlaceInShop);
                        endOfBuy.put("treasureLeft", treasureLeft);
                        endOfBuy.put("bought", "true");
                        endOfBuy.put("cardBought", game.getPlayer().getDiscard().toString());
                        game.decrementTurnBuys();
                        endOfBuy.put("buysLeft", game.getTurnBuys());
                    }
                } else {
                    endOfBuy.put("bought", "false");
                }

                pw.write(endOfBuy.toString());


                break;

            case "actionCard":
                JSONObject afterAction = new JSONObject();

                String cardName = request.getParameter("cardName");
                System.out.println(cardName);
                int cardPlaceInHand = findCardPlaceInHand(cardName);



                doAction(cardName, cardPlaceInHand);
                game.decrementTurnActions();
                afterAction.put("coinsLeft", game.getTurnCoins());
                afterAction.put("newHand", makeHandArray(game.getPlayer()));
                afterAction.put("actions", game.getTurnActions());
                afterAction.put("buys", game.getTurnBuys());
                afterAction.put("actionsInHand", game.getPlayer().handContainsActionCards());
                afterAction.put("askPlayer", cardName);
                pw.write(afterAction.toString());

                break;


            case "endTurn":

                for (int i = 0; i < game.getPlayer().getHand().size(); i++) {
                    game.getPlayer().emptyHand();
                }

                game.nextTurn();


                break;


            case "discard":

                JSONArray jsontrashCards = new JSONArray(request.getParameter("cards"));
                String[] trashCards = new String[jsontrashCards.length()];

                for (int i = 0; i <trashCards.length ; i++) {
                    trashCards[i] = jsontrashCards.get(i).toString();

                }

                for (String trashCard : trashCards) {
                    game.getPlayer().destroyCardFromHand(findCardPlaceInHand(trashCard));
                    game.getPlayer().removeCardFromHand(findCardPlaceInHand(trashCard));

                }

                break;

            case "oneTimeBuy":
                JSONObject jason = new JSONObject();
                String card = request.getParameter("card");
                int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));
                int Place = getPlaceInShop(card,game.getShop());
                int cardPrice = game.getShop().getShopArray()[Place].getPriceOfCard();

                boolean bought = false;



                if(cardPrice<= maxPrice){
                    game.getPlayer().toDiscard(game.getShop().getShopArray()[Place].getCard());
                    game.getShop().getShopArray()[Place].decrementStack();
                    bought = true;
                }


                jason.put("bought",bought);
                pw.write(jason.toString());

                break;

            case "shopIsopen":
                JSONObject shopObject = new JSONObject();

                Boolean isOpen = game.getShop().isOpen();
                shopObject.put("shopIsopen",isOpen);
                pw.write(shopObject.toString());


                break;


            case "endGame":
                JSONObject winnersAndLosers = new JSONObject();
                int[][] playerRank = game.playerScoreRank();
                winnersAndLosers.put("winner",game.getPlayers()[playerRank[0][0]].getName());
                winnersAndLosers.put("winnerPoints",playerRank[0][1]);
                winnersAndLosers.put("loser1",game.getPlayers()[playerRank[1][0]].getName());
                winnersAndLosers.put("loser1points",playerRank[1][1]);
                if(game.getPlayers().length>2){
                    winnersAndLosers.put("loser2",game.getPlayers()[playerRank[2][0]].getName());
                    winnersAndLosers.put("loser2points",playerRank[2][1]);
                }

                pw.write(winnersAndLosers.toString());


                break;



            default:
                pw.append(" { 'status':'nok', 'errormessage':'Invalid Operation' } ");
                break;
        }

    }


    private void Initialize(HttpServletRequest request, HttpServletResponse response, int[] startCardArray) throws ServletException, IOException {

        PrintWriter pw = response.getWriter();
        this.getServletContext().setAttribute("gameEngine", game);
        startNewGame(startCardArray, pw, firstplayer, otherPlayer, lastPlayer);


    }

}
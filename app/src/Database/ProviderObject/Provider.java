 package Database.ProviderObject;

import javax.print.DocFlavor;
import java.sql.*;

/**
 * Created by Nick Casier on 12/05/2016.
 * try's met resources zodat connecties automatisch sluiten (geneste try's)
 */
public class Provider {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";
    private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
    private static final String USER_ID="root";
    private static final String PASSWD="";

    public static void main(String[] args){
        try{
            Class.forName(JDBC_DRIVER);
            try(Connection connection = DriverManager.getConnection(JDBC_URL, USER_ID, PASSWD)){
                getCards(connection);
                getPlayerCards(connection);
                getSavedGame(connection);
                getUsers(connection);
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Driver not found: " + e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Driver not found: " + e);
        }
    }

    private static void getCards(Connection connection) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.Cards")){
           try (ResultSet resultSet = statement.executeQuery()){
               while (resultSet.next()) {
                   System.out.println(resultSet.getString("cardNr")+"|"+resultSet.getString("cardName")+"|"+resultSet.getString("cardType")+"|"+resultSet.getString("cardValue"));
               }
           }
        }
    }

    private static void getUsers(Connection connection) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.Users")){
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("userNr")+"|"+resultSet.getString("userName"));
                }
            }
        }
    }

    private static void getSavedGame(Connection connection) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.SavedGame")){
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("gameNr")+"|"+resultSet.getString("date")+"|"+resultSet.getString("userNr"));
                }
            }
        }
    }

    private static void getPlayerCards(Connection connection) throws SQLException{
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.PlayerCards")){
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("userNr")+"|"+resultSet.getString("gameNr")+"|"+resultSet.getString("handNr")+"|"+resultSet.getString("deckNr")+"|"+resultSet.getString("discardNr")+"|"+resultSet.getString("shopNr"));
                }
            }
        }
    }
}
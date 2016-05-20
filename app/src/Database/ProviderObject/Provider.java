package Database.ProviderObject;

import java.sql.*;

import static Database.DAO.CardsDAO.getCards;
import static Database.DAO.PlayerCardsDAO.getPlayerCards;
import static Database.DAO.SavedGameDAO.getSavedGame;
import static Database.DAO.UsersDAO.getUsers;
import static Database.DMO.SavedGameDMO.setSavedGame;
import static Database.DMO.UserDMO.setUsers;



public class Provider {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER_ID = "root";
    private static final String PASSWD = "";

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            try (Connection connection = DriverManager.getConnection(JDBC_URL, USER_ID, PASSWD)) {
                getCards(connection);
                getPlayerCards(connection);
                getSavedGame(connection);
                getUsers(connection);
                setUsers(connection);
                setSavedGame(connection);
                setSavedGame(connection);
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Driver not found: " + e);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Driver not found: " + e);
        }
    }
}
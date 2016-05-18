package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PlayerCardsDAO {
    public static void getPlayerCards(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.PlayerCards")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("userNr") + "|" + resultSet.getString("gameNr") + "|" + resultSet.getString("handNr") + "|" + resultSet.getString("deckNr") + "|" + resultSet.getString("discardNr") + "|" + resultSet.getString("shopNr"));
                }
            }
        }
    }
}

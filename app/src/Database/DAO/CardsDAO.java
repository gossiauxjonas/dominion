package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CardsDAO {
    public static void getCards(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.Cards")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("cardNr") + "|" + resultSet.getString("cardName") + "|" + resultSet.getString("cardType") + "|" + resultSet.getString("cardValue"));
                }
            }
        }
    }
}

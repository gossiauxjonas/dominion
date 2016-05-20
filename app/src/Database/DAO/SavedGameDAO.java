package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SavedGameDAO {
    public static void getSavedGame(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.SavedGame")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("gameNr") + "|" + resultSet.getString("gameDate") + "|" + resultSet.getString("userNr"));
                }
            }
        }
    }
}

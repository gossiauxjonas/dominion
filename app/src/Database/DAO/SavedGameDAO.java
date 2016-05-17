package Database.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nick Casier on 12/05/2016.
 */
public class SavedGameDAO {
    public static void getSavedGame(Connection connection) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM dominion.SavedGame")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString("gameNr") + "|" + resultSet.getString("date") + "|" + resultSet.getString("userNr"));
                }
            }
        }
    }
}

package Database.DMO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nick Casier on 12/05/2016.
 */
public class SavedGameDMO {
    public static void setSavedGame(Connection connection) throws SQLException {
        PreparedStatement pstmt;
        try {
            String query = "insert into dominion.SavedGame (userNr) values(0)";
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



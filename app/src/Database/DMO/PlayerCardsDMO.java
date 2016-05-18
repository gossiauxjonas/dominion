package Database.DMO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PlayerCardsDMO {
    public static void setPlayerCards(Connection connection) throws SQLException {
        PreparedStatement pstmt;
        try {
            String query = "insert into dominion.PlayerVards (userNr, gameNr, handNr, deckNr, discardNr, shopNr) values(0, 0, 0, 0, 0, 0)";
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

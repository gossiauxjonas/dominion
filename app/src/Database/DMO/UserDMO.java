package Database.DMO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nick Casier on 12/05/2016.
 */
public class UserDMO {
    public static void setUsers(Connection connection) throws SQLException {
        PreparedStatement pstmt;
        try {
            String query = "insert into dominion.Users (userName) values('testUser')";
            pstmt = connection.prepareStatement(query);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


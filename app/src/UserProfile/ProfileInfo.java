package UserProfile;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Nick Casier on 23/03/16.
 */
public class ProfileInfo {
    private String name;
    private String mail;
    private String password;
    private Date birthDate;
    private int gameID; //for the moment int, will probably change

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getGameID() {
        return gameID;
    }


    public static Object[] profileSetup = {"Nick", "casiernick@gmail.com", "Password1234", "13/10/1996", 55};


}
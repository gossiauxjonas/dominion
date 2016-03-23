package UserProfile;

import java.util.Date;

/**
 * Created by Nick Casier on 23/03/16.
 */
public class Profile {
    private String name;
    private String mail;
    private String password;
    private Date birthDate;
    private int gameID; //for the moment String, will change

    public String getName(){
        return name;
    }

    public String getMail(){
        return mail;
    }

    public String getPassword(){
        return password;
    }

    public Date getBirthDate(){
        return birthDate;
    }

    public int getGameID()
    {
        return gameID;
    }

}

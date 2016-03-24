package UserProfile;

/**
 * Created by Nick Casier on 23/03/16.
 */
public class ProfileInfo {
    private String name;
    private String mail;
    private String password;
    private String birthDate;
    private int gameID; //for the moment int, will probably change

    private void setName() {name = "Nick";}
    private void setMail() {mail = "casiernick@gmail.com";}
    private void setPassword() {password = "Password1234";}
    private void setBirthDate() {birthDate = "13/10/1996";}
    private void setGameID(){gameID=55;}

    public String getName(){
        return name;
    }

    public String getMail(){
        return mail;
    }

    public String getPassword(){
        return password;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public int getGameID() {return gameID; }

    public Object[] profileSetupObjects = {name, mail, password, birthDate, gameID};

    public Object[] getProfileSetupObjects() {
        return profileSetupObjects;
    }

}
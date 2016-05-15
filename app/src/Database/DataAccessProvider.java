package Database;

import Database.DAO.CardsDAO;
import Database.DAO.SavedGameDAO;
import Database.DAO.UsersDAO;

/**
 * Created by Nick Casier on 12/05/2016.
 */
public interface DataAccessProvider {
    CardsDAO getCardsDAO();
    SavedGameDAO getSavedGameDAO();
    UsersDAO getUserDAO();
}

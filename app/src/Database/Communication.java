package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nick Casier on 3/05/2016.
 */
public class Communication {
        private String databaseURL;
        private String user;
        private String password;
        private Connection conn;
        private Statement stmt;
        private String[] result;
        private ArrayList<String> results;
        private HashMap<String,String> results2;

        public Communication()
        {
            getMySQLConnection();
        }
        public void getMySQLConnection() //open de databaseconnectie
        {
            databaseURL = "jdbc:mysql://localhost:3306/Dominion2";
            user = "root";
            password = "";
            conn = null;

            try {

                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(databaseURL,user,password);
                if(conn != null)
                {
                    //System.out.println("Connected to database");
                }


            }catch (ClassNotFoundException ex) {
                System.out.println("Could not find database driver class");
                ex.printStackTrace();
            } catch (SQLException ex) {
                System.out.println("An error occurred. Maybe user/password is invalid");
                ex.printStackTrace();
            }


        }
        public void closeConnection() //sluit de databaseconnectie
        {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        public ArrayList<String> SelectStatement(String whatAreYouLookingFor,String WhereStatement,String table,String value)
        {//zoek in databank bij nood aan where statement
            stmt = null;
            results = new ArrayList<String>();


            String updatedQuery = "SELECT "+whatAreYouLookingFor+" FROM "+table+" WHERE "+WhereStatement +"= "+value;
            //System.out.println(updatedQuery);
            try{

                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(updatedQuery);

                while(rs.next())
                {

                    for (int i = 1;i<rs.getMetaData().getColumnCount();i++)
                    {
                        results.add(rs.getString(i));
                        //System.out.print(rs.getString(i) + " ");
                    }
                    System.out.println();


                }



            }catch (SQLException e ) {
                System.out.println(e.getMessage());

            }
            try
            {

                stmt.close();

            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());

            }
            // System.out.println(results);
            return results;


        }

        public HashMap<String,String> SelectInfoWithColumnName(String whatDoYouWant,String WhereStatement,String table,String value)
        {//haal info uit de database samen met de kolomnaam waar deze info bijhoort.
            stmt = null;
            results2 = new HashMap<String,String>();


            String updatedQuery = "SELECT "+whatDoYouWant+" FROM "+table+" WHERE "+WhereStatement +" = '" + value + "'";
            //System.out.println(updatedQuery);
            try{

                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(updatedQuery);

                while(rs.next())
                {

                    for (int i = 1;i<=rs.getMetaData().getColumnCount();i++)
                    {
                        results2.put(rs.getMetaData().getColumnName(i),rs.getString(i));
                        //System.out.println(rs.getMetaData().getColumnName(i) + " " +rs.getString(i) );


                    }}

            }catch (SQLException e ) {
                System.out.println(e.getMessage());
            }

            try
            {

                stmt.close();

            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());

            }

            //System.out.println(results2);
            return results2;


        }

        public ArrayList<String> SelectWithoutWhere(String whatDoYouWant,String table)
        {

            stmt = null;
            results = new ArrayList<String>();
            String updatedQuery = "SELECT "+whatDoYouWant+" FROM "+table;
            try{

                stmt = conn.prepareStatement(updatedQuery);

                ResultSet rs = stmt.executeQuery(updatedQuery);

                while(rs.next())
                {
                    results.add(rs.getString(1));
                }
                System.out.println();
            }

            catch (SQLException e ) {
                System.out.println(e.getMessage());
            }

            try
            {

                stmt.close();

            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());

            }


            return results;


        }




        public  ArrayList<String> SelectCardsMatchingChosenDeck( String modenaam)
        {//selecteer kaartnamen die horen bij een gekozen vast deck (vb Big Money)
            stmt = null;
            ArrayList<String> kaartenInGameMode = new ArrayList<String>();
            String updatedQuery = "SELECT kaartnaam FROM kaarten JOIN kaartingamemode ON kaarten.kaartnr= kaartingamemode.kaartnr JOIN gamemodes ON kaartingamemode.modenr = gamemodes.modenr WHERE modenaam = '" + modenaam + "' ORDER BY kaartnaam";
            try{
                stmt = conn.prepareStatement(updatedQuery);
                ResultSet rs = stmt.executeQuery(updatedQuery);

                while(rs.next())
                {
                    kaartenInGameMode.add(rs.getString(1));

                }

            }

            catch (SQLException e ) {
                System.out.println(e.getMessage());
            }

            try
            {

                stmt.close();

            }
            catch(SQLException e)
            {
                System.out.println(e.getMessage());

            }

            return kaartenInGameMode;


        }



        public void ShowResult(ArrayList<String> resultaten)
        {//toon resulaten van select statement waarbij geen kolomnamen werden opgevraagd
            for (int i = 0;i<resultaten.size();i++)
            {
                System.out.print(resultaten.get(i).toString());
            }
        }

        public static void main(String[] args)
        {
            //DatabaseHelper d = new DatabaseHelper();
            //d.SelectStatement("*","kaartnr","kaarten","2");
            //d.SelectInfoWithColumnName("*","kaartnr","kaarten","2");
        }
}
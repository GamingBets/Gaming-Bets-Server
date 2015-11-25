package de.blogsiteloremipsum.gamingbets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by quint_000 on 05.11.2015.
 */
public class Database {

    public static Connection connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            String name = "GamingBets";
            String user = "gamebet";
            String password = "gamebetdb";

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + name,
                    user,
                    password);
            return con;

        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IllegalAccessException e){
            e.printStackTrace();
        }
        catch (InstantiationException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(String userName, String password, Date dob, boolean admin){

        Connection con = connect();
        try {

            String query = "INSERT INTO `user` (`iD`, `userName`, `password`, `bets`, `loggedIn`, `admin`, `active`, `dob`, `email`) VALUES (NULL, '"+userName+"', '"+password+"', 'nichts', '0', '1', '1', '2015-11-18', 'hey@nichts');";
            Statement stmt = con.createStatement();
            boolean succ = stmt.execute(query, Statement.EXECUTE_FAILED);

            stmt.close();
            con.close();
            return !succ;

        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static User getUser(String userName){

        int id=0;
        String uName = userName;
        String password="";
        String bets="";
        boolean admin=false;
        boolean active=true;
        String email="";
        Date dob = new Date(100);

        Connection con = connect();
        try{
            Statement stmt = con.createStatement();
            String query = "SELECT * FROM `user` WHERE `userName` = '"+userName+"' ";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                id = rs.getInt("iD");
                password = rs.getString("password");
                bets = rs.getString("bets");
                admin = rs.getBoolean("admin");
                active = rs.getBoolean("active");
                email = rs.getString("email");
                dob = rs.getDate("dob");
            }
            return new User(id, uName, email, password, bets, admin, active, dob );
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }

}

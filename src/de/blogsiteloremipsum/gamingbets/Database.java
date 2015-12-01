package de.blogsiteloremipsum.gamingbets;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
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
    
    public static boolean edit(User user){
    	Connection con = connect();
    	try{
    		String query = "UPDATE 'user' SET ('password' = ?, 'email' = ?, 'dob' = ? )WHERE  'userName'= ?";
		    PreparedStatement preparedStmt = con.prepareStatement(query);
		    preparedStmt.setString   (1, user.getPassword());
		    preparedStmt.setString(2, user.getEmail());
		    preparedStmt.setDate(3, user.getDob());
		    preparedStmt.setString(4, user.getUserName());
    	 
    	      // execute the java preparedstatement
    	      preparedStmt.executeUpdate();
    		return true;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean userExist(String userName){
    	
    	Connection con = connect();
    	try{
    		String query = "SELECT 'userName' FROM 'user' WHERE 'userName'=?";
    		PreparedStatement stmt = con.prepareStatement(query);
    		stmt.setString(1, userName);
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			return true;
    		}
    		return false;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }

    public static boolean register(UnregisteredUser user){

        Connection con = connect();
        try {

            String query = "INSERT INTO `user` ( `userName`, `password`, `bets`, `loggedIn`, `admin`, `active`, `dob`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, "");
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, false);
            stmt.setBoolean(6, true);
            stmt.setDate(7, user.getDob());
            stmt.setString(8, user.getEmail());
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
            
            String query = "SELECT * FROM `user` WHERE `userName` = ? ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
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

    public ArrayList<User> getAllUser(){
    	Connection con = connect();
    	User u;
    	ArrayList<User> users = new ArrayList<User>();
    	try{    		
	    	
	    	String query = "SELECT * FROM 'user'";	
	    	PreparedStatement stmt = con.prepareStatement(query);
	    	ResultSet rs = stmt.executeQuery();
	    	
	    	while(rs.next()){
	    		u = new User(rs.getInt("iD"), rs.getString("userName"), rs.getString("email"), rs.getString("password"), rs.getString("bets"), rs.getBoolean("admin"), rs.getBoolean("active"), rs.getDate("dob") );
	    		users.add(u);
	    	}
	    	return users;
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return users;
    }
}
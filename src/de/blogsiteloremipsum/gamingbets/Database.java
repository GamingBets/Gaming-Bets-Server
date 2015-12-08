package de.blogsiteloremipsum.gamingbets;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import de.blogsiteloremipsum.gamingbets.classes.Ticket;
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
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    public static ArrayList<Ticket> getTickets(){
    	Connection con = connect();
    	
    	Ticket t;
    	ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    	try{
    		String query = "SELECT * FROM `ticket`";
    		PreparedStatement stmt = con.prepareStatement(query);
    		ResultSet rs = stmt.executeQuery();
    		while(rs.next()){
    			t = new Ticket(rs.getInt("iD"), rs.getInt("userID"), rs.getInt("status"), rs.getDate("date"), null, rs.getString("content"));
    			tickets.add(t);
    		}
    		return tickets;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return null;
    }
    
    /*Ticket Bearbeitungsstatus 
     * 0=done
     * 1=answered
     * 2=pending
     */
    public static boolean setStatus(int id, int status){
    	Connection con = connect();
    	
    	try{
    		String query = "UPDATE `ticket` SET(`status`=?) WHERE `iD`=?";
    		PreparedStatement stmt = con.prepareStatement(query);
    		stmt.setInt(1, status);
    		stmt.setInt(2, id);
    		
    		stmt.executeQuery();
    		
    		return true;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	
    	return false;
    	
    }
    
    public static ArrayList<User> getScores(){
    	Connection con = connect();    	
    	User u;
    	ArrayList<User> users = new ArrayList<User>();
    	
    	try{    		
	    	
	    	String query = "SELECT * FROM `user` ORDER BY `score`";	
	    	PreparedStatement stmt = con.prepareStatement(query);
	    	ResultSet rs = stmt.executeQuery();
	    	
	    	while(rs.next()){
	    		u = new User(rs.getInt("iD"), rs.getString("userName"), rs.getString("email"), rs.getString("password"), rs.getString("bets"), rs.getBoolean("admin"), rs.getBoolean("active"), rs.getDate("dob"), rs.getInt("score") );
	    		users.add(u);
	    	}
	    	
	    	rs.close();
	    	stmt.close();
	    	con.close();
	    	
	    	return users;
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return users;
    }
    
    public static boolean postTicket(Ticket ticket){
    	Connection con = connect();
    	try{
    		String query = "INSERT INTO `ticket` (`userID`, `content`, `date`) VALUES(?, ?, ?)";
    		PreparedStatement stmt = con.prepareStatement(query);
    		stmt.setInt(1, ticket.getID());
    		stmt.setString(2, ticket.getContent());
    		stmt.setDate(3, ticket.getDate());
    		stmt.execute();
    		
    		stmt.close();
    		con.close();
    		
    		return true;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean edit(User user){
    	Connection con = connect();
    	try{
    		
    		String generatedPassword = "";
            
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(user.getPassword().getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
                
		    String query = "UPDATE `GamingBets`.`user` SET `email`=?, `password`=? WHERE `userName`=?";
    		PreparedStatement stmt = con.prepareStatement(query);
		    
		    stmt.setString(1, user.getEmail());
		    stmt.setString(2, generatedPassword);
		    stmt.setString(3, user.getUserName());
		    stmt.executeUpdate();
		    
		    stmt.close();
		    con.close();
		    
    		return true;
    		    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }
    
    public static boolean userExist(String userName){
    	
    	Connection con = connect();
    	try{
    		String query = "SELECT `userName` FROM `user` WHERE `userName`=?";
    		PreparedStatement stmt = con.prepareStatement(query);
    		stmt.setString(1, userName);
    		
    		ResultSet rs = stmt.executeQuery();
    		if(rs.next()){
    			return true;
    		}
    		
    		rs.close();
    		stmt.close();
    		con.close();
    		
    		return false;
    		
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return false;
    }

    public static boolean register(UnregisteredUser user){

        Connection con = connect();
        try {

            String query = "INSERT INTO `user` ( `userName`, `password`, `bets`, `loggedIn`, `admin`, `active`, `dob`, `email`, `score`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, "");
            stmt.setBoolean(4, false);
            stmt.setBoolean(5, false);
            stmt.setBoolean(6, true);
            stmt.setDate(7, user.getDob());
            stmt.setString(8, user.getEmail());
            stmt.setInt(9, 0);
            
            stmt.execute();

            stmt.close();
            con.close();
            
            return true;

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
        int score = 0;

        Connection con = connect();
        try{
            
            String query = "SELECT * FROM `user` WHERE `userName` = ? ";
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, userName);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                id = rs.getInt("iD");
                password = rs.getString("password");
                bets = rs.getString("bets");
                admin = rs.getBoolean("admin");
                active = rs.getBoolean("active");
                email = rs.getString("email");
                dob = rs.getDate("dob");
                score = rs.getInt("score");
            }
            
            return new User(id, uName, email, password, bets, admin, active, dob, score );
            
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }

    public static ArrayList<User> getAllUser(){
    	Connection con = connect();
    	User u;
    	ArrayList<User> users = new ArrayList<User>();
    	
    	try{    		
	    	
	    	String query = "SELECT * FROM 'user'";	
	    	PreparedStatement stmt = con.prepareStatement(query);
	    	
	    	ResultSet rs = stmt.executeQuery();	    	
	    	while(rs.next()){
	    		u = new User(rs.getInt("iD"), rs.getString("userName"), rs.getString("email"), rs.getString("password"), rs.getString("bets"), rs.getBoolean("admin"), rs.getBoolean("active"), rs.getDate("dob"), rs.getInt("score") );
	    		users.add(u);
	    	}
	    	
	    	rs.close();
	    	stmt.close();
	    	con.close();
	    	
	    	return users;
	    	
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return users;
    }
}
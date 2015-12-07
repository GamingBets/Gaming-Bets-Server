package de.blogsiteloremipsum.gamingbets.communication.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.blogsiteloremipsum.gamingbets.Database;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.CommunicationPackage;
import de.blogsiteloremipsum.gamingbets.communication.communication_types;

/**
 * Created by Felix on 17.11.2015.
 */
public class CommunicationPackageHandler {

    private CommunicationPackage cp;

    public CommunicationPackageHandler(CommunicationPackage cp) {
        this.cp = cp;
    }

    public String handle() {
        //TODO Needs Implementation
        /**
         * Returns "done" when Succesfully done Transaction
         * Otherwise returns Error as String
         */
        switch (cp.getType()){
            case EDIT:
                return edit();
            case EDITADMIN:
            	return editAdmin();
            case LOGIN:             
                return login();
            case LOGOUT:
                break;
            case REGISTER:
                return register();
            case POSTTICKET:
            	return postTicket();
            default:
                return "kein gültiger Package Type";
        }
        return "done";
    }
    
    public CommunicationPackage handleObject(){
    	switch (cp.getType()){
        case SENDLEADERBOARD:
            return getLeaderboard();
        case SENDTICKETS:
        	return getTickets();
        case SENDUSERS:             
            return getUsers();
        default:
            return null;
    	}
    }

    public CommunicationPackage getUser(){
    	CommunicationPackage cp = new CommunicationPackage(communication_types.SENDUSER, null, null, null, null, null);
    	cp.setUser(Database.getUser(this.cp.getUser().getUserName()));
    	return cp;
    }
    public CommunicationPackage getLeaderboard(){
    	CommunicationPackage cp = new CommunicationPackage(communication_types.SENDLEADERBOARD, null, null, null, null, null);
    	cp.setAllUser(Database.getScores());
    	return cp;
    }
    
    public CommunicationPackage getTickets(){
    	CommunicationPackage cp = new CommunicationPackage(communication_types.SENDTICKETS, null, null, null, null, null);
    	cp.setAllTickets(Database.getTickets());
    	return cp;
    }
    
    public CommunicationPackage getUsers(){
    	CommunicationPackage cp = new CommunicationPackage(communication_types.SENDUSERS, null, null, null, null, null);
    	cp.setAllUser(cp.getAllUser());
    	return cp;
    }
    public String postTicket(){
    	if(Database.postTicket(cp.getTicket()))
    	return "done";
    	else return "not done";
    }
    public String edit(){
    	if(Database.edit(cp.getUser()))
    	return "done";
    	else return "not done";
    }
    
    public String editAdmin(){
    	cp.setAllUser(Database.getAllUser());
    	return "done";
    }
    public String login(){
        
        User user = cp.getUser();
        User userDB = Database.getUser(user.getUserName());

        String generatedPassword = "";
        try {
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
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        if(generatedPassword.equals(userDB.getPassword())){
            return "done";
        }
        return "Login fehlgeschlagen";

    }

    public String register(){

        UnregisteredUser user = cp.getUnregisteredUser();
        String generatedPassword = "";
        try {
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
            user.setPassword(generatedPassword);
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        boolean test = Database.register(user);
        System.out.println(test);
        if(test){
            System.out.println("Erfolgreich registriert");
            return "done";
        }
        System.out.println("nicht Erfolgreich registriert");
        return "nicht Erfolgreich registriert";
    }

    public void logout(){

    }

    public CommunicationPackage getCp() {
        return cp;
    }

    public void setCp(CommunicationPackage cp) {
        this.cp = cp;
    }
}

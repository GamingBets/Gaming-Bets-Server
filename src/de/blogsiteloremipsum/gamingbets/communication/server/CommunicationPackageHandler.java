package de.blogsiteloremipsum.gamingbets.communication.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import de.blogsiteloremipsum.gamingbets.Database;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.CommunicationPackage;

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
                break;
            case EDITADMIN:
                break;
            case LOGIN:             
                return login();
            case LOGOUT:
                break;
            case REGISTER:
                return register();
            default:
                return "kein gültiger Package Type";
        }
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
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        boolean test = Database.register(user.getUserName(), generatedPassword, user.getDob(), false);
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

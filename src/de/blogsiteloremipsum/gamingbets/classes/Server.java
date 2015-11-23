package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class Server {

    public boolean login(User user){
        user.setLoggedin(true);
        return true;
    }

    public boolean logout(User user){
        user.setLoggedin(false);
        return true;
    }

    public boolean register(String userName, String password, String email, Date dob){
        return true;
    }

    public boolean edit(User user){
        return true;
    }

    public boolean editAdmin(User user){
        return true;
    }
}
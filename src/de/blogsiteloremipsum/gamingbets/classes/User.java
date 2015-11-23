package de.blogsiteloremipsum.gamingbets.classes;

import java.util.List;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class User{

    private int ID;
    private String userName;
    private String email;
    private String password;
    private String bets;
    private boolean admin;
    private boolean active;

    public User(int ID, String userName, String email, String password, String bets, boolean admin, boolean active) {
        this.ID = ID;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.bets = bets;
        this.admin = admin;
        this.active = active;
    }

    public User(){}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBets() {
        return bets;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isLoggedin() {
        return active;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBets(String bets) {
        this.bets = bets;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setLoggedin(boolean loggedin) {
        this.active = loggedin;
    }
}

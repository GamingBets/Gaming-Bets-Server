package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;
import java.util.List;

/**
 * Created by Felix on 17.11.2015.
 */
public class UnregisteredUser {

    private String userName;
    private String email;
    private String password;
    private Date dob;

    public UnregisteredUser(String userName, String email, String password, Date dob) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}

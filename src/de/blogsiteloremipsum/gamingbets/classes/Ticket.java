package de.blogsiteloremipsum.gamingbets.classes;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class Ticket implements Serializable{

	private static final long serialVersionUID = 1L;
    private int ID;
    private int userID;
    private int status;
    private Date date;
    private List<User> processors;
    private String content;
    private String email;

    public Ticket(int ID, int userID, int status, Date date, List<User> processors, String content) {
        this.ID = ID;
        this.userID = userID;
        this.status = status;
        this.date = date;
        this.processors = processors;
        this.content = content;
    }

    
    public int getUserID() {
		return userID;
	}


	public void setUserID(int userID) {
		this.userID = userID;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public int getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public List<User> getProcessors() {
        return processors;
    }


    public void setStatus(int status) {
        this.status = status;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProcessors(List<User> processors) {
        this.processors = processors;
    }

    
}

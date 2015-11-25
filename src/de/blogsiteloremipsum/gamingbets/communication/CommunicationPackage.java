package de.blogsiteloremipsum.gamingbets.communication;

import java.io.Serializable;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.UnregisteredUser;
import de.blogsiteloremipsum.gamingbets.classes.User;
import de.blogsiteloremipsum.gamingbets.communication.communication_types;

/**
 * Created by Felix on 17.11.2015.
 *
 *
 * Contains any Information Relevant for a Communication
 *
 */
public class CommunicationPackage implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;




    private communication_types type;
    private User user;
    private Bet bet;
    private Ticket ticket;
    private UnregisteredUser unregisteredUser;

    public CommunicationPackage(communication_types type, User user, Bet bet, Ticket ticket, UnregisteredUser unregisteredUser) {
        this.type = type;
        this.user = user;
        this.bet = bet;
        this.ticket = ticket;
        this.unregisteredUser = unregisteredUser;
    }

    public static long getSerialVerssionUID() {
        return getSerialVerssionUID();
    }

    public UnregisteredUser getUnregisteredUser() {
        return unregisteredUser;
    }

    public void setUnregisteredUser(UnregisteredUser unregisteredUser) {
        this.unregisteredUser = unregisteredUser;
    }

    public communication_types getType() {
        return type;
    }

    public void setType(communication_types type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}

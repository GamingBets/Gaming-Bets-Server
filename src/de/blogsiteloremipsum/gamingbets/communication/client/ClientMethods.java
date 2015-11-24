package de.blogsiteloremipsum.gamingbets.communication.client;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Felix Morsbach on 13.11.2015.
 */
public interface ClientMethods {

    boolean login(String userName, String password);

    boolean logout(User user);

    boolean register(String username, String email, String pw, java.sql.Date dob);

    boolean edit(User user);

    boolean editAdmin(User user);

    boolean placeBet(Bet bet);

    boolean sendTicket(Ticket ticket);
}

package de.blogsiteloremipsum.gamingbets.communication.client;

import java.util.Date;

import de.blogsiteloremipsum.gamingbets.classes.Bet;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;
import de.blogsiteloremipsum.gamingbets.classes.User;

/**
 * Created by Felix Morsbach on 13.11.2015.
 *
 * @NICK:
 * hab hier jetzt nur das Interface Implementiert damit du siehst was für Funktionen es gibt.
 * Das solltest du dann bitte Entfernen sobald du das implementierst.
 * Wenn du ne Aktion ausführen willst, einfach über die Methoden des Feldes "server" arbeiten
 *
 * Auswertung ob die Aktion erfolgreich war gibts es aktuell nur über die bool´schen Rückgabewerte der Funktionen
 * Soll aber mal als Fehler-String implementiert sein, später!
 */
public class Logic implements ClientMethods {


    ClientMethods server = new LocalClientSocket();     //Hier dran bitte nichts aendern!

    public Logic() {
    }

    @Override
    public boolean login(User user) {
        return server.login(user);
    }

    @Override
    public boolean logout(User user) {
        return server.logout(user);
    }

    @Override
    public boolean register(String username, String email, String pw, Date dob) {
        return server.register(username, email, pw, dob);
    }

    // And so on....
    // @nick, dein kram

    @Override
    public boolean edit(User user) {
        return false;
    }

    @Override
    public boolean editAdmin(User user) {
        return false;
    }

    @Override
    public boolean placeBet(Bet bet) {
        return false;
    }

    @Override
    public boolean sendTicket(Ticket ticket) {
        return false;
    }
}



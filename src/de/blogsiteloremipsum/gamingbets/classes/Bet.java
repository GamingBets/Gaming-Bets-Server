package de.blogsiteloremipsum.gamingbets.classes;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class Bet {

    private int ID;
    private User user;
    private boolean done;
    private int matchID;
    private String betCode;

    public Bet(int ID, User user, boolean done, int matchID, String betCode) {
        this.ID = ID;
        this.user = user;
        this.done = done;
        this.matchID = matchID;
        this.betCode = betCode;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public String getBetCode() {
        return betCode;
    }

    public void setBetCode(String betCode) {
        this.betCode = betCode;
    }
}

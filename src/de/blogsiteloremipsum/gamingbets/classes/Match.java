package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class Match {

    private int ID;
    private String player1;
    private String player2;
    private Date date;
    private int length;
    private boolean winner;
    private int league;

    public Match(String player1, String player2, Date date, int length, boolean winner, int league) {
        this.player1 = player1;
        this.player2 = player2;
        this.date = date;
        this.length = length;
        this.winner = winner;
        this.league = league;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}

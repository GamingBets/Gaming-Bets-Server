package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class MatchSc2 extends Match {

    private int racePlayer1;
    private int racePlayer2;

    public MatchSc2(String player1, String player2, Date date, int length, boolean winner, int league, int racePlayer1, int racePlayer2) {
        super(player1, player2, date, length, winner, league);
        this.racePlayer1 = racePlayer1;
        this.racePlayer2 = racePlayer2;
    }

    public int getRacePlayer1() {
        return racePlayer1;
    }

    public int getRacePlayer2() {
        return racePlayer2;
    }
}

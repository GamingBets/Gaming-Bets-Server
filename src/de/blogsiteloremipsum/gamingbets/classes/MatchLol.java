package de.blogsiteloremipsum.gamingbets.classes;

import java.util.Date;

/**
 * Created by quint_000 on 12.11.2015.
 */
public class MatchLol extends Match {

    private int firstBlood;
    private int firstDragon;
    private int firstBaron;
    private String[] bans;
    private String[] picks;
    private int killsTeam1;
    private int killsTeam2;

    public MatchLol(String player1, String player2, Date date, int length, boolean winner, int league, int firstBlood, int firstDragon, int firstBaron, String[] bans, String[] picks, int killsTeam1, int killsTeam2) {
        super(player1, player2, date, length, winner, league);
        this.firstBlood = firstBlood;
        this.firstDragon = firstDragon;
        this.firstBaron = firstBaron;
        this.bans = bans;
        this.picks = picks;
        this.killsTeam1 = killsTeam1;
        this.killsTeam2 = killsTeam2;
    }

    public void setFirstBlood(int firstBlood) {
        this.firstBlood = firstBlood;
    }

    public void setFirstDragon(int firstDragon) {
        this.firstDragon = firstDragon;
    }

    public void setFirstBaron(int firstBaron) {
        this.firstBaron = firstBaron;
    }

    public void setBans(String[] bans) {
        this.bans = bans;
    }

    public void setPicks(String[] picks) {
        this.picks = picks;
    }

    public void setKillsTeam1(int killsTeam1) {
        this.killsTeam1 = killsTeam1;
    }

    public void setKillsTeam2(int killsTeam2) {
        this.killsTeam2 = killsTeam2;
    }

    public int getFirstBlood() {
        return firstBlood;
    }

    public int getFirstDragon() {
        return firstDragon;
    }

    public int getFirstBaron() {
        return firstBaron;
    }

    public String[] getBans() {
        return bans;
    }

    public String[] getPicks() {
        return picks;
    }

    public int getKillsTeam1() {
        return killsTeam1;
    }

    public int getKillsTeam2() {
        return killsTeam2;
    }
}

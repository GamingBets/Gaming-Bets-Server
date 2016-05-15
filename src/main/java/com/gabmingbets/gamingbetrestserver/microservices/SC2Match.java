package com.gabmingbets.gamingbetrestserver.microservices;

import java.util.ArrayList;

public class SC2Match {

	private String player1;
	private String player2;
	private String tournament;
	private int score;
	private int type_as_boX;
	private ArrayList<SC2Set> sets;

	public SC2Match() {
		player1 = "";
		player2 = "";
		tournament = "";
		score = -1;
		sets = new ArrayList<SC2Set>();
	}

	public int getType_as_boX() {
		switch (sets.size()) {
		case 1:
			return 1;
		case 2:
			return 3;
		case 3:
			if (sets.get(0).getWinner() == sets.get(1).getWinner()&& 
				sets.get(1).getWinner() == sets.get(2).getWinner()) {
				
				return 5;
			}
			return 3;
		case 4:
			if (sets.get(0).getWinner() == sets.get(1).getWinner()&& 
				sets.get(1).getWinner() == sets.get(2).getWinner()&&
				sets.get(2).getWinner() == sets.get(3).getWinner()){
				
				return 7;
			}
			return 5;
		case 5:
			if (sets.get(0).getWinner() == sets.get(1).getWinner()&& 
				sets.get(1).getWinner() == sets.get(2).getWinner()&&
				sets.get(2).getWinner() == sets.get(3).getWinner()&&
				sets.get(3).getWinner() == sets.get(4).getWinner()){
				
				return 9;
			}else {
				int wins_p1 = 0;
				
				for (SC2Set sc2Set : sets) {
					wins_p1 += sc2Set.getScorePlayer(1);
				}
				if (wins_p1 == 2 || wins_p1 == 3) {
					return 5;
				}
			}
			return 7;
		case 6:
			//TODO Needs further Implementation
			return 7;
		case 7:
			//TODO Needs further Implementation
			return 7;
		default:
			break;
		}
		return -1;
	}

	public void addSC2Set(SC2Set set) {
		this.sets.add(set);
	}

	public String getPlayer(int i) {
		if (i == 1) {
			return player1;
		}
		return player2;
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

	public String getTournament() {
		return tournament;
	}

	public void setTournament(String tournament) {
		this.tournament = tournament;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		StringBuilder out = new StringBuilder("");
		out.append("Tournament: " + tournament + System.lineSeparator());
		out.append("Player1: " + player1 + System.lineSeparator());
		out.append("Player2: " + player2 + System.lineSeparator());
		out.append("Score: " + score + System.lineSeparator());
		return out.toString();

	}

	public void setSets(ArrayList<SC2Set> sets) {
		this.sets = sets;
		
	}
}

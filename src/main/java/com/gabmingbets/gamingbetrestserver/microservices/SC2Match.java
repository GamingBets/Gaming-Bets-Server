package com.gabmingbets.gamingbetrestserver.microservices;

public class SC2Match {
	
	private String player1;
	private String player2;
	private String tournament;
	private int score;

	public SC2Match() {
		player1 = "";
		player2 = "";
		tournament = "";
		score = -1;
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
	public String toString(){
		StringBuilder out = new StringBuilder("");
		out.append("Tournament: "+tournament+System.lineSeparator());
		out.append("Player1: "+player1+System.lineSeparator());
		out.append("Player2: "+player2+System.lineSeparator());
		out.append("Score: "+score+System.lineSeparator());
		return out.toString();
		
	}
}

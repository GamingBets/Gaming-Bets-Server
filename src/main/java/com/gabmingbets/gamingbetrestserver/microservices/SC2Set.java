package com.gabmingbets.gamingbetrestserver.microservices;

public class SC2Set {

	private int scorePlayer1;
	private int scorePlayer2;
	private int match_position;
	
	
	
	public SC2Set(int scorePlayer1, int scorePlayer2, int match_position) {
		super();
		this.scorePlayer1 = scorePlayer1;
		this.scorePlayer2 = scorePlayer2;
		this.match_position = match_position;
	}

	public int getWinner() {
		if (scorePlayer1 == 1) {
			return 1;
		}
		return 2;
	}

	public int getScorePlayer(int i){
		if (i==1) {
			return scorePlayer1;
		}else if (i==2){
			return scorePlayer2;
		}
		return 0;
		
	}

	public int getMatch_position() {
		return match_position;
	}

	public void setMatch_position(int match_position) {
		this.match_position = match_position;
	}
	
	
}

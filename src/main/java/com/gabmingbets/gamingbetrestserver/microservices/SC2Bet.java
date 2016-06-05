package com.gabmingbets.gamingbetrestserver.microservices;

public class SC2Bet {

	private int available_bet_id;
	private int user_id;
	private int betted_result;

	public SC2Bet(int available_bet, int user_id, int betted_result) {
		super();
		this.available_bet_id = available_bet;
		this.user_id = user_id;
		this.betted_result = betted_result;
	}

	public int getAvailable_bet_id() {
		return available_bet_id;
	}

	public void setAvailable_bet_id(int available_bet_id) {
		this.available_bet_id = available_bet_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getBetted_result() {
		return betted_result;
	}

	public void setBetted_result(int betted_result) {
		this.betted_result = betted_result;
	}

}

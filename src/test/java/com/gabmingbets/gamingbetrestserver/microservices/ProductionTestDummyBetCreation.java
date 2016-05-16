package com.gabmingbets.gamingbetrestserver.microservices;

public class ProductionTestDummyBetCreation {
	
	public static void main(String[] args) {
		CreateBet.InsertBet(new SC2Bet(1, 19, 1));
	}
	
	

}

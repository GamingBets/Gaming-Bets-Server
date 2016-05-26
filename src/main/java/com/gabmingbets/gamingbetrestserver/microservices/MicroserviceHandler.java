package com.gabmingbets.gamingbetrestserver.microservices;

public class MicroserviceHandler {
	
	public static void evaluateBetsSC2(){
		Evaluate_Bet_SC2 eb = new Evaluate_Bet_SC2();
		eb.run();
	}
	
//	public static void evaluateBetsLol(){
//		Evaluate_Bet_Lol eb = new Evaluate_Bet_Lol();
//		eb.run();
//	}
	
	public static void evaluateBetsAll(){
//		evaluateBetsLol();
		evaluateBetsSC2();
	}
	
	public static void createAvailableBetsSC2(){
		Create_Available_Bets_SC2 cab = new Create_Available_Bets_SC2();
		cab.run();
	}
	
//	public static void createAvailableBetsLoL(){
//		Create_Available_Bets_Lol cab = new Create_Available_Bets_Lol();
//		cab.run();
//	}
	
	public static void createAvailableBetsAll(){
//		createAvailableBetsLoL();
		createAvailableBetsSC2();
	}
	
	public static void adjustScoreSC2(){
		AdjustScoreSC2 as = new AdjustScoreSC2();
		as.run();
	}
	
	public static void adjustScoreAll(){
		adjustScoreSC2();
	}
}
package com.gabmingbets.gamingbetrestserver.microservices;

import java.util.List;

import com.gabmingbets.gamingbetrestserver.domain.service.Sc2MatchesFacadeREST;

public class MicroserviceHandler {

	// ############## STARCRAFT 2 ###################

	public static void createAvailableBetsSC2() {
		Create_Available_Bets_SC2 cab = new Create_Available_Bets_SC2();
		cab.run();
	}

	public static void evaluateBetsSC2() {
		Evaluate_Bet_SC2 eb = new Evaluate_Bet_SC2();
		eb.run();
	}

	public static void adjustScoreSC2() {
		AdjustScoreSC2 as = new AdjustScoreSC2();
		as.run();
	}

	// ############ League of Legends ###############

	public static void createAvailableBetsLoL() {
		Create_Available_Bets_Lol cab = new Create_Available_Bets_Lol();
		cab.run();
	}

	public static void evaluateBetsLol() {
		Evaluate_Bet_Lol eb = new Evaluate_Bet_Lol();
		eb.run();
	}

}

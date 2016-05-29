package com.gabmingbets.gamingbetrestserver.microservices;

import com.gabmingbets.gamingbetrestserver.domain.Sc2AvailableBets;
import com.gabmingbets.gamingbetrestserver.domain.Sc2Bet;
import com.gabmingbets.gamingbetrestserver.domain.Sc2Matches;
import com.gabmingbets.gamingbetrestserver.domain.service.Sc2AvailableBetsFacadeREST;
import com.gabmingbets.gamingbetrestserver.domain.service.Sc2MatchesFacadeREST;

public class ProductionTestDummyBetCreation {
	
	public static void main(String[] args) {
            Sc2Bet bet = new Sc2Bet();
            bet.setBettedResult(1);
            Sc2AvailableBets bets = new Sc2AvailableBetsFacadeREST().find(1);
            bet.setBetId(bets);
            bet.setUserId(19);
	}
	
	

}

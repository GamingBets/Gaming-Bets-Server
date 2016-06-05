package com.gabmingbets.gamingbetrestserver.microservices;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_For_SC2_Bet {
	
	@Test
	public void BeanTestSC2Bet(){
		SC2Bet bet = new SC2Bet(0, 1, 2);
		assertEquals(0, bet.getAvailable_bet_id());
		assertEquals(1, bet.getUser_id());
		assertEquals(2, bet.getBetted_result());
	}

}

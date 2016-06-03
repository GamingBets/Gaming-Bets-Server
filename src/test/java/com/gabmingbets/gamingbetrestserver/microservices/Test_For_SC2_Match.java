package com.gabmingbets.gamingbetrestserver.microservices;

import static org.junit.Assert.*;
import org.junit.Test;

public class Test_For_SC2_Match {

	@Test
	public void BeanTestSc2Match() {
		SC2Match sc2match = new SC2Match();
		sc2match.setPlayer1("SomePlayer1");
		sc2match.setPlayer2("SomePlayer2");
		sc2match.setScore(4532);
		sc2match.setTournament("dummyTournament");
		String expected = 
				"Tournament: dummyTournament" 
				+ System.lineSeparator() 
				+ "Player1: SomePlayer1"
				+ System.lineSeparator() 
				+ "Player2: SomePlayer2" 
				+ System.lineSeparator() 
				+ "Score: 4532"
				+ System.lineSeparator();
		assertEquals(expected, sc2match.toString());
	}

}

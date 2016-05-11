package com.gabmingbets.gamingbetrestserver.microservices;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test_For_Bet_Creation_Microservice_SC2 {

	private Create_Bet_SC2 cb;

	@Before
	public void init() {
		cb = new Create_Bet_SC2();
	}

	@Test
	public void QueryForMatchesWithoutCreatedFlag() {
		assertEquals("SELECT * FROM GamingBets.sc2_matches WHERE bet_created = 0;", cb.createSelectQuery());
	}

	@Test
	public void CreateInsertQueryForBetsTable() {
		assertEquals("INSERT INTO `gamingbets`.`sc2_available_bets` (`match_id`) VALUES ('1');", cb.createInsertQuery(1));

	}

	@Test
	public void CreateUpdateQueryForBetsTable() {
		assertEquals("UPDATE `GamingBets`.`sc2_matches` SET `bet_created`='1' WHERE `id`='1';", cb.createUpdateQuery(1));

	}

}

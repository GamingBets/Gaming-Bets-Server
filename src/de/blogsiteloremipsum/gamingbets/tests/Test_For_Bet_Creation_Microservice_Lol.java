package de.blogsiteloremipsum.gamingbets.tests;

import de.blogsiteloremipsum.gamingbets.server.microservices.Create_Bet_Lol;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Test_For_Bet_Creation_Microservice_Lol {
	Create_Bet_Lol cb;

	@Before
	public void init() {
		cb = new Create_Bet_Lol();
	}


	@Test
	public void QueryForMatchesWithoutCreatedFlag() {
		assertEquals("SELECT * FROM GamingBets.lol_matches WHERE bet_created = 0;", cb.createSelectQuery());
	}

	@Test
	public void CreateInsertQueryForBetsTable() {
		assertEquals("INSERT INTO `gamingbets`.`lol_available_bets` (`match_id`) VALUES ('1');", cb.createInsertQuery(1));

	}

	@Test
	public void CreateUpdateQueryForBetsTable() {
		assertEquals("UPDATE `GamingBets`.`lol_matches` SET `bet_created`='1' WHERE `id`='1';", cb.createUpdateQuery(1));

	}
}

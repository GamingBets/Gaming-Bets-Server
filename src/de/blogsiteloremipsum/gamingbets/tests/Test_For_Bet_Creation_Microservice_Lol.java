package de.blogsiteloremipsum.gamingbets.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import de.blogsiteloremipsum.gamingbets.server.microservices.Create_Bet_Lol;

public class Test_For_Bet_Creation_Microservice_Lol {
	Create_Bet_Lol cb;

	@Before
	public void init() {
		cb = new Create_Bet_Lol();
	}

	@Test
	public void QueryForMatchesWithoutCreatedFlag() {
		assertEquals(cb.createSelectQuery(), "SELECT * FROM SC2.matches WHERE bet_created = 0;");
	}

	@Test
	public void CreateInsertQueryForBetsTable() {
		assertEquals(cb.createInsertQuery(), "Some Insert Statement - tbd");

	}

	@Test
	public void CreateUpdateQueryForBetsTable() {
		assertEquals(cb.createUpdateQuery(), "Some Update Statement, yet to be written");

	}
}

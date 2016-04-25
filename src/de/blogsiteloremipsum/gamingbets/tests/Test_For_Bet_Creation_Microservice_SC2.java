package de.blogsiteloremipsum.gamingbets.tests;

import static org.junit.Assert.*;

import org.junit.*;

import de.blogsiteloremipsum.gamingbets.server.microservices.Create_Bet_SC2;

public class Test_For_Bet_Creation_Microservice_SC2 {

	Create_Bet_SC2 cb;

	@Before
	public void init() {
		cb = new Create_Bet_SC2();
	}

	@Test
	public void QueryForMatchesWithoutCreatedFlag() {
		assertEquals(cb.createSelectQuery(), "SELECT * FROM SC2.matches WHERE bet_created = 0;");
	}

	@Test
	public void CreateInsertQueryForBetsTable() {
		assertEquals(cb.createInsertQuery(1), "Some Insert Statement, yet to be written");

	}

	@Test
	public void CreateUpdateQueryForBetsTable() {
		assertEquals(cb.createUpdateQuery(1), "Some Update Statement, yet to be written");

	}

}

package com.gabmingbets.gamingbetrestserver.microservices;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;

public class Test_For_Bet_Creation_Microservice_Lol {
	private Create_Available_Bets_Lol cb;
	private static Connection con;

	@BeforeClass
	public static void firstInit() {
		con = Database.connect();
	}

	@AfterClass
	public static void lastClearUp() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void init() {
		cb = new Create_Available_Bets_Lol();

		String query;
		PreparedStatement stmt;

		// Create new Dummy Matches

		try {
			query = "INSERT INTO `gamingbets`.`lol_matches` (`result`, `team_1`, `team_2`, `bet_created`, comment) VALUES ('1', '1', '2', '0', 'debug');";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

			query = "INSERT INTO `gamingbets`.`lol_matches` (`result`, `team_1`, `team_2`, `bet_created`, comment) VALUES ('2', '2', '1', '0', 'debug');";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void cleanUp(){
		String query;
		PreparedStatement stmt;

		// clear Available Bets

		query = "SELECT m.idmatches FROM lol_matches m WHERE m.comment LIKE 'debug'";
		ResultSet rs;
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {

				query = "DELETE FROM `gamingbets`.`lol_available_bets` WHERE `match_id`='" + rs.getInt("idmatches") + "'";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// clear DummyMatches
		query = "DELETE FROM `gamingbets`.`lol_matches` WHERE `comment` LIKE 'debug'";
		try {
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void checkIfAllBetsForFinishedMatchesWereEvaluated() {

		String query = "SELECT m.* FROM lol_matches m WHERE m.comment = 'debug';";
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<Integer> bets = new ArrayList<Integer>();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bets.add(rs.getInt("idmatches"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		MicroserviceHandler.createAvailableBetsLoL();

		for (int i = 0; i < bets.size(); i++) {
			try {
				query = "SELECT * FROM lol_matches WHERE idmatches = " + bets.get(i) + ";";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getInt("bet_created") < 1) {
						String failmessage = "At least one Match was not evaluated!";
						fail(failmessage);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void QueryForMatchesWithoutCreatedFlag() {
		assertEquals("SELECT * FROM gamingbets.lol_matches WHERE bet_created = 0;", cb.createSelectQuery());
	}

	@Test
	public void CreateInsertQueryForBetsTable() {
		assertEquals("INSERT INTO `gamingbets`.`lol_available_bets` (`match_id`) VALUES ('1');",
				cb.createInsertQuery(1));

	}

	@Test
	public void CreateUpdateQueryForBetsTable() {
		assertEquals("UPDATE `GamingBets`.`lol_matches` SET `bet_created`='1' WHERE `idmatches`='1';",
				cb.createUpdateQuery(1));

	}
}

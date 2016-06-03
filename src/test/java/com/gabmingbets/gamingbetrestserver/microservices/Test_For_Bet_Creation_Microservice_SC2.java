package com.gabmingbets.gamingbetrestserver.microservices;

import org.junit.After;
import org.junit.AfterClass;
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

public class Test_For_Bet_Creation_Microservice_SC2 {

	private Create_Available_Bets_SC2 cb;
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
		cb = new Create_Available_Bets_SC2();

		String query;
		PreparedStatement stmt;

		// Create new Dummy Matches

		try {
			query = "INSERT INTO `gamingbets`.`sc2_matches` (`result`, `player1`, `player2`, `bet_created`, comment, finished) VALUES ('20', '1', '2', '0', 'debug', 0);";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

			query = "INSERT INTO `gamingbets`.`sc2_matches` (`result`, `player1`, `player2`, `bet_created`, comment, finished) VALUES ('3', '1', '2', '0', 'debug', 0);";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@After
	public void clearUp() {

		String query;
		PreparedStatement stmt;

		// clear Available Bets

		query = "SELECT m.id FROM sc2_matches m WHERE m.comment LIKE 'debug'";
		ResultSet rs;
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {

				query = "DELETE FROM `gamingbets`.`sc2_available_bets` WHERE `match_id`='" + rs.getInt("id") + "'";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// clear DummyMatches
		query = "DELETE FROM `gamingbets`.`sc2_matches` WHERE `comment` LIKE 'debug'";
		try {
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void CheckIfAllBetsAreCreated() {
		String query = "SELECT m.* FROM sc2_matches m WHERE m.comment = 'debug';";
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<Integer> bets = new ArrayList<Integer>();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bets.add(rs.getInt("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		MicroserviceHandler.createAvailableBetsSC2();

		for (int i = 0; i < bets.size(); i++) {
			try {
				query = "SELECT m.* FROM sc2_matches m WHERE m.id = " + bets.get(i) + ";";
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
		assertEquals("SELECT * FROM gamingbets.sc2_matches WHERE bet_created = 0;", cb.createSelectQuery());
	}

	@Test
	public void CreateInsertQueryForBetsTable() {
		assertEquals("INSERT INTO `gamingbets`.`sc2_available_bets` (`match_id`) VALUES ('1');",
				cb.createInsertQuery(1));

	}

	@Test
	public void CreateUpdateQueryForBetsTable() {
		assertEquals("UPDATE `gamingbets`.`sc2_matches` SET `bet_created`='1' WHERE `id`='1';",
				cb.createUpdateQuery(1));

	}

}

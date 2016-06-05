package com.gabmingbets.gamingbetrestserver.microservices;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_For_Bet_Evaluation_Lol {

	private static Connection con;

	@BeforeClass
	public static void firstInit() {
		con = Database.connect();
	}
	
	@AfterClass
	public static void finalCleanUp(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Before
	public void init() {

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

		// Create new Dummy Available Bets
		query = "SELECT m.idmatches FROM lol_matches m WHERE m.comment LIKE 'debug'";
		int a = 1;
		ResultSet rs;
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				a = rs.getInt("idmatches");
				query = "INSERT INTO lol_available_bets (match_id) VALUES (" + a + ");";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();

				query = "SELECT ab.idlol_available_bets FROM lol_available_bets ab WHERE ab.match_id = " + a + ";";
				PreparedStatement temp_2;
				temp_2 = con.prepareStatement(query);
				ResultSet temp = temp_2.executeQuery();
				int b = 0;
				while (temp.next()) {
					b = temp.getInt("idlol_available_bets");
				}

				// Create new Dummy Bets
				query = "INSERT INTO `gamingbets`.`lol_bet` (`bet_id`, `user_id`, `betted_result`, `status`) VALUES ('"
						+ b + "', '19', '" + 1 + "', '0');";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				query = "INSERT INTO `gamingbets`.`lol_bet` (`bet_id`, `user_id`, `betted_result`, `status`) VALUES ('"
						+ b + "', '19', '" + 2 + "', '0');";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@After
	public void clearUp() {
		String query;
		PreparedStatement stmt;

		// clear Bets
		query = "DELETE FROM `gamingbets`.`lol_bet` WHERE `user_id`='19'";

		try {
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

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

		String query = "SELECT b.idlol_bet FROM gamingbets.lol_bet b, lol_available_bets ab, lol_matches m WHERE b.bet_id = ab.idlol_available_bets and ab.match_id = m.idmatches and m.result != 0 and m.comment = 'debug';";
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<Integer> bets = new ArrayList<Integer>();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bets.add(rs.getInt("idlol_bet"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		MicroserviceHandler.evaluateBetsLol();

		for (int i = 0; i < bets.size(); i++) {
			try {
				query = "SELECT * FROM lol_bet WHERE idlol_bet = " + bets.get(i) + ";";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getInt("status") < 3) {
						String failmessage = "At least one Bet was not evaluated!";
						fail(failmessage);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void checkIfEvaluationIsCorrect() {
		
		MicroserviceHandler.evaluateBetsLol();
		try {
			String query = "SELECT b.status, m.result, b.betted_result, b.bet_id, b.idlol_bet, m.idmatches from gamingbets.lol_bet b, lol_available_bets ab, lol_matches m WHERE b.bet_id = ab.idlol_available_bets and ab.match_id = m.idmatches and b.user_id = 19;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("result") == rs.getInt("betted_result") && rs.getInt("status") == 4) {
					String failmessage = "A bet was evaluated as wrong, altough it should be right!";
					fail(failmessage);
				}
				if (rs.getInt("result") != rs.getInt("betted_result") && rs.getInt("status") == 3) {
					String failmessage = "A bet was evaluated as right, altough it should be wrong!";
					fail(failmessage);
				}
				assertFalse("Status was not valid!"+rs.getInt("status"), rs.getInt("status")>4 || rs.getInt("status")< 3);
				
			}
		} catch (SQLException e) {

		}
	}
}

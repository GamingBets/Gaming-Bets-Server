package com.gabmingbets.gamingbetrestserver.microservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class Test_For_Adjust_Score_SC2 {
	
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
			query = "INSERT INTO `gamingbets`.`sc2_matches` (`result`, `player1`, `player2`, `bet_created`, comment, finished) VALUES ('20', '1', '2', '0', 'debug', 1);";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

			query = "INSERT INTO `gamingbets`.`sc2_matches` (`result`, `player1`, `player2`, `bet_created`, comment, finished) VALUES ('3', '1', '2', '0', 'debug', 1);";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Create new Dummy Available Bets
		query = "SELECT m.id FROM sc2_matches m WHERE m.comment LIKE 'debug'";
		int a = 1;
		ResultSet rs;
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				a = rs.getInt("id");
				query = "INSERT INTO sc2_available_bets (match_id) VALUES (" + a + ");";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();

				query = "SELECT ab.idsc2_available_bets FROM sc2_available_bets ab WHERE ab.match_id = " + a + ";";
				PreparedStatement temp_2;
				temp_2 = con.prepareStatement(query);
				ResultSet temp = temp_2.executeQuery();
				int b = 0;
				while (temp.next()) {
					b = temp.getInt("idsc2_available_bets");
				}

				// Create new Dummy Bets
				query = "INSERT INTO `gamingbets`.`sc2_bet` (`bet_id`, `user_id`, `betted_result`, `status`) VALUES ('"
						+ b + "', '19', '" + 1 + "', '0');";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				query = "INSERT INTO `gamingbets`.`sc2_bet` (`bet_id`, `user_id`, `betted_result`, `status`) VALUES ('"
						+ b + "', '19', '" + 2 + "', '0');";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Evaluate_Bet_SC2 eb = new Evaluate_Bet_SC2();
		eb.run();		

	}

	@After
	public void clearUp() {
		String query;
		PreparedStatement stmt;

		// clear Bets
		query = "DELETE FROM `gamingbets`.`sc2_bet` WHERE `user_id`='19'";

		try {
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

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
	public void dummyTest(){
		assertEquals(1,1);
	}
	

	@Test
	public void checkIfAllBetsForFinishedMatchesWereProcessed() {

		String query = "SELECT b.idsc2_bet, b.processed FROM gamingbets.sc2_bet b, sc2_available_bets ab, sc2_matches m WHERE b.bet_id = ab.idsc2_available_bets and ab.match_id = m.id and m.result != 0 and m.comment = 'debug' and b.processed = 0;";
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<Integer> bets = new ArrayList<Integer>();
		try {
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				bets.add(rs.getInt("idsc2_bet"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		MicroserviceHandler.adjustScoreSC2();

		for (int i = 0; i < bets.size(); i++) {
			try {
				query = "SELECT b.idsc2_bet, b.processed FROM sc2_bet b WHERE b.idsc2_bet = " + bets.get(i) + ";";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getInt("processed") == 0) {
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
	public void checkIfScoreAdjustmentIsCorrect() {
		
		int score_before = 0;
		int score_after = 1;
		
		try {
			String query = "SELECT * FROM gamingbets.user u WHERE iD = 19;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				score_before = rs.getInt("score");
			}
		} catch (SQLException e) {

		}
		
		MicroserviceHandler.adjustScoreSC2();
		
		try {
			String query = "SELECT * FROM gamingbets.user u WHERE iD = 19;";
			PreparedStatement stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				score_after = rs.getInt("score");
			}
		} catch (SQLException e) {

		}
		
		assertEquals(score_before+20, score_after);
	}

}

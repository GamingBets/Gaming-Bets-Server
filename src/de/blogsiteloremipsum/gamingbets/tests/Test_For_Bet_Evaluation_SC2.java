package de.blogsiteloremipsum.gamingbets.tests;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.ArrayList;

import org.junit.*;

import de.blogsiteloremipsum.gamingbets.Database;
import de.blogsiteloremipsum.gamingbets.server.microservices.Evaluate_Bet_Lol;
import de.blogsiteloremipsum.gamingbets.server.microservices.Evaluate_Bet_SC2;

public class Test_For_Bet_Evaluation_SC2 {

	private Evaluate_Bet_SC2 test_object;
	private static Connection con;

	@BeforeClass
	public static void firstInit() {
		con = Database.connect();
	}

	@Before
	public void init() {

		test_object = new Evaluate_Bet_SC2();
		String query;
		PreparedStatement stmt;

		// Create new Dummy Matches

		try {
			query = "INSERT INTO `gamingbets`.`sc2_matches` (`result`, `player1`, `player2`, `bet_created`, comment) VALUES ('2', '1', '2', '0', 'debug');";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();

			query = "INSERT INTO `gamingbets`.`sc2_matches` (`result`, `player1`, `player2`, `bet_created`, comment) VALUES ('1', '1', '2', '0', 'debug');";
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
	public void checkIfAllBetsForFinishedMatchesWereEvaluated() {

		String query = "SELECT b.idsc2_bet FROM gamingbets.sc2_bet b, sc2_available_bets ab, sc2_matches m WHERE b.bet_id = ab.idsc2_available_bets and ab.match_id = m.id and m.result != 0 and m.comment = 'debug';";
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

		test_object.run();

		for (int i = 0; i < bets.size(); i++) {
			try {
				query = "SELECT * FROM sc2_bet WHERE idsc2_bet = " + bets.get(i) + ";";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				while (rs.next()) {
					if (rs.getInt("status") <= 3) {
						String failmessage = "At least one Bet was not evaluated!";
						fail(failmessage);
					}
				}
			} catch (SQLException e) {

			}
		}
	}

	@Test
	public void checkIfEvaluationIsCorrect() {
		try {
			String query = "SELECT b.status, m.result, b.betted_result, b.bet_id, b.idsc2_bet, m.id from gamingbets.sc2_bet b, sc2_available_bets ab, sc2_matches m WHERE b.bet_id = ab.idsc2_available_bets and ab.match_id = m.id and b.user_id = 19;";
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
				assertFalse("Status was not valid!", rs.getInt("status")>4 || rs.getInt("status")< 3);
				
			}
		} catch (SQLException e) {

		}
	}

}

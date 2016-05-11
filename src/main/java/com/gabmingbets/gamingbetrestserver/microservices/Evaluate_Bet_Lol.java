package com.gabmingbets.gamingbetrestserver.microservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Evaluate_Bet_Lol {

	public void run() {
		String query;
		PreparedStatement stmt;
		ResultSet rs;
		Connection con = Database.connect();
		int counter = 0;
		// Check if there are bets which corresponding matches ended but the bets weren�t evaluated!
		try {
			query = "SELECT b.idlol_bet, b.betted_result, m.result FROM lol_matches m, lol_bet b, lol_available_bets ab WHERE b.bet_id = ab.idlol_available_bets and ab.match_id = m.idmatches and b.status < 3 and m.result > 0;";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				if (rs.getInt("result") == rs.getInt("betted_result")) {
					query = "UPDATE `gamingbets`.`lol_bet` SET `status`='3' WHERE `idlol_bet`='"
							+ rs.getInt("idlol_bet") + "';";
				} else {
					query = "UPDATE `gamingbets`.`lol_bet` SET `status`='4' WHERE `idlol_bet`='"
							+ rs.getInt("idlol_bet") + "';";
				}
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				counter++;
			}
		} catch (SQLException e) {

		}
		System.out.println("" + counter + " rows were evaluated!");

	}

}

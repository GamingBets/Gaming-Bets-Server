package com.gabmingbets.gamingbetrestserver.microservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Evaluate_Bet_SC2 {

	public void run() {
		
		String query;
		PreparedStatement stmt;
		ResultSet rs;
		Connection con = Database.connect();
		int counter = 0;
		//Check if there are bets which corresponding matches ended but the bets weren�t evaluated!
		try {
			query = "SELECT b.idsc2_bet, b.betted_result, m.result FROM sc2_matches m, sc2_bet b, sc2_available_bets ab WHERE b.bet_id = ab.idsc2_available_bets and ab.match_id = m.id and b.status < 3 and m.result > 0;";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				if (rs.getInt("result") == rs.getInt("betted_result")) {
					query = "UPDATE `gamingbets`.`sc2_bet` SET `status`='3' WHERE `idsc2_bet`='"+rs.getInt("idsc2_bet")+"';";
				}else{
					query = "UPDATE `gamingbets`.`sc2_bet` SET `status`='4' WHERE `idsc2_bet`='"+rs.getInt("idsc2_bet")+"';";
				}
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				counter++;
			}
		} catch (SQLException e) {

		}
		System.out.println(""+counter+" rows were evaluated!");
		
	}


}

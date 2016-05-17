package com.gabmingbets.gamingbetrestserver.microservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdjustScoreSC2 {
	
public void run() {
		
		String query;
		PreparedStatement stmt;
		ResultSet rs;
		Connection con = Database.connect();
		int counter = 0;
		//Check if there are bets which corresponding matches ended but the bets weren�t evaluated!
		try {
			query = "SELECT b.user_id, u.score, b.idsc2_bet, b.status, b.input FROM gamingbets.sc2_bet b, user u WHERE processed = 0 and b.user_id = u.iD;";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			
			
			while (rs.next()) {
				if (rs.getInt("status")==3) {
					
					//Get The Right Score
					int score_adjust = 0;
					if (rs.getInt("input")==0) {
						score_adjust = 10;
					}else {
						score_adjust = rs.getInt("input");
						score_adjust = score_adjust * 3;
					}
					
					query = "SELECT * FROM gamingbets.user u WHERE u.iD = "+rs.getInt("user_id")+";";
					stmt = con.prepareStatement(query);
					ResultSet temp = stmt.executeQuery();
					temp.next();
					
					
					System.out.println(query);
					stmt = con.prepareStatement(query);
					stmt.executeUpdate();
				}
				query = "UPDATE `gamingbets`.`sc2_bet` SET `processed`='1' WHERE `idsc2_bet`='"+rs.getInt("idsc2_bet")+"';";
				stmt = con.prepareStatement(query);
				stmt.executeUpdate();
				counter++;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		System.out.println(""+counter+" scores were updated!");
		
	}

}

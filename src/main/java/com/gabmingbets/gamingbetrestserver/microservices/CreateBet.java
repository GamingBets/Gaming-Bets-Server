package com.gabmingbets.gamingbetrestserver.microservices;

import com.gabmingbets.gamingbetrestserver.domain.Sc2Bet;
import com.gabmingbets.gamingbetrestserver.domain.service.Sc2BetFacadeREST;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateBet {
	
	public static boolean InsertBet(Sc2Bet bet){
		/*
		Connection con = Database.connect();
		PreparedStatement stmt;
		
		String query = "INSERT INTO `gamingbets`.`sc2_bet` (`bet_id`, `user_id`, `betted_result`) VALUES ('"+bet.getAvailable_bet_id()+"', '"+bet.getUser_id()+"', '"+bet.getBetted_result()+"');"; 
		try {
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		*/            
            new Sc2BetFacadeREST().create(bet);
            return true;
               

	}

}

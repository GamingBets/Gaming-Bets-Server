package com.gabmingbets.gamingbetrestserver.microservices;

import com.gabmingbets.gamingbetrestserver.domain.Sc2Bet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Evaluate_Bet_SC2 {

    @PersistenceContext(unitName = "com.gabmingbets_gamingBetRestServer_war_1.0PU")
    private EntityManager em;
	public void run() {
	
            /*
            int counter = 0;

            TypedQuery<Sc2Bet> query = em.createNamedQuery("Sc2Bet.findAllMatchEndedNotEvaluated", Sc2Bet.class);
            List<Sc2Bet> bets = new ArrayList();
            bets = query.getResultList();

            boolean player1_won = false;

            for ( int i = 0; i < bets.size(); i++) {

                Sc2Bet bet = bets.get(i);

                if(bet.getBetId().getMatchId().getResult() < 10) {
                    player1_won = false;
                }else {
                    int player2_score = bet.getBetId().getMatchId().getResult()%10;
                    int player1_score = (bet.getBetId().getMatchId().getResult()-player2_score)/10;
                    player1_won = player1_score>player2_score;
                }

                if(player1_won && bet.getBettedResult() == 1 || !player1_won && bet.getBettedResult() !=1) {
                    Query q = em.createQuery("UPDATE Sc2Bet b SET b.status = 3 WHERE b.idsc2Bet = " + bet.getIdsc2Bet() + "");
                    q.executeUpdate();
                }else {
                    Query q = em.createQuery("Update Sc2Bet b SET status = 4 WHERE b.idsc2Bet = " + bet.getIdsc2Bet() + "");
                    q.executeUpdate();
                }
                counter ++;

            }
            System.out.println(""+counter+" rows were evaluated!");
            */
            /**/
		String query;
		PreparedStatement stmt;
		ResultSet rs;
		Connection con = Database.connect();
		int counter = 0;
		//Check if there are bets which corresponding matches ended but the bets weren�t evaluated!
		try {
			query = "SELECT b.idsc2_bet, b.betted_result, m.result FROM sc2_matches m, sc2_bet b, sc2_available_bets ab WHERE b.bet_id = ab.idsc2_available_bets and ab.match_id = m.id and b.status < 3 and m.finished > 0;";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			
			boolean player1_won = false;
			
			while (rs.next()) {
				
				if (rs.getInt("result")<10) {
					player1_won = false;
				}else {
					int player2_score = (rs.getInt("result")%10);
					int player1_score = ((rs.getInt("result")-player2_score)/10);
					player1_won = (player1_score>player2_score);
				}
				
				if (player1_won && 1 == rs.getInt("betted_result") || !player1_won && 1 != rs.getInt("betted_result")) {
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
		/**/
	}


}

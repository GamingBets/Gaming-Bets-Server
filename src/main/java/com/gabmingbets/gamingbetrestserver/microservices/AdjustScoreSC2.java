package com.gabmingbets.gamingbetrestserver.microservices;

import com.gabmingbets.gamingbetrestserver.domain.Sc2Bet;
import com.gabmingbets.gamingbetrestserver.domain.User;
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

public class AdjustScoreSC2 {
    
    @PersistenceContext(unitName = "com.gabmingbets_gamingBetRestServer_war_1.0PU")
    private EntityManager em;
    
public void run() {
		
    TypedQuery<Sc2Bet> query = em.createNamedQuery("Sc2Bet.findByProcessed", Sc2Bet.class).setParameter("processed", em);
    List<Sc2Bet> bets = new ArrayList();
    int counter = 0;
    bets = query.getResultList();
    
    for(int i = 0; i < bets.size(); i++) {
        if(bets.get(i).getStatus()==3) {
            int score_adjust = 0;
            if(bets.get(i).getInput()==0) {
                score_adjust = 10;
            }else {
                score_adjust = bets.get(i).getInput();
                score_adjust *= 3;
            }
            TypedQuery<User> query2 = em.createNamedQuery("User.findById", User.class).setParameter("id", bets.get(i).getUserId());
            User user = query2.getSingleResult();
            
            score_adjust += user.getScore();
            
            Query q = em.createQuery("UPDATE User u SET u.score = " + score_adjust + "WHERE u.id=" + user.getId() +"");
            q.executeUpdate();
        }
        Query q = em.createQuery("UPDATE Sc2Bet b SET b.processed = 1 WHERE b.idsc2Bet = " + bets.get(i).getIdsc2Bet() + "");
        q.executeUpdate();
        
        counter++;
    }
    System.out.println(""+counter+" scores were updated!");
    /*String query;
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
					
					
					query = "UPDATE `gamingbets`.`user` SET `score`='"+(temp.getInt("score")+score_adjust)+"' WHERE `iD`='"+rs.getInt("user_id")+"';";
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
		System.out.println(""+counter+" scores were updated!");*/
		
	}

}

package com.gabmingbets.gamingbetrestserver.microservices;

import com.gabmingbets.gamingbetrestserver.domain.Sc2AvailableBets;
import com.gabmingbets.gamingbetrestserver.domain.Sc2Matches;
import com.gabmingbets.gamingbetrestserver.domain.service.Sc2AvailableBetsFacadeREST;
import com.gabmingbets.gamingbetrestserver.domain.service.Sc2MatchesFacadeREST;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class Create_Available_Bets_SC2 {

    @PersistenceContext(unitName = "com.gabmingbets_gamingBetRestServer_war_1.0PU")
    private EntityManager em;
	private Date dateOfLastExecute;
	private Connection con;

	public Create_Available_Bets_SC2() {
		dateOfLastExecute = getDateOfLastExecuteFromDB();
		//this.con = Database.connect();
	}

	public void run() {
		/*

                // Get dateOfLastExecute

		// Query for SC2 Matches with bet_created flag = 0
		String query = createSelectQuery();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				
				stmt = con.prepareStatement(createInsertQuery(rs.getInt("id")));
				stmt.executeUpdate();
				
				
				stmt = con.prepareStatement(createUpdateQuery(rs.getInt("id")));
				stmt.executeUpdate();
				
				i++;
				
			}
		System.out.println(i+" available bets were created!");
		} catch (SQLException e) {
			//TODO Exception Handling
			e.printStackTrace();
		}*/
                
            TypedQuery<Sc2Matches> query = em.createNamedQuery("Sc2Matches.findByBetCreated", Sc2Matches.class).setParameter("betCreated", 0);
            List<Sc2Matches> matches = new ArrayList();
            matches = query.getResultList();
            
            for(int i = 0; i<matches.size();i++) {
                Sc2AvailableBets bet = new Sc2AvailableBets();
                bet.setMatchId(matches.get(i));
                Sc2AvailableBetsFacadeREST betfacade = new Sc2AvailableBetsFacadeREST();
                betfacade.create(bet);
                
                Query q = em.createQuery("UPDATE Sc2Matches m SET m.betCreated = " + 1 + " WHERE u.id=" + bet.getMatchId().getId()+ "");
                q.executeUpdate();
            }
            
            
            
            


	}

	private void resetTimer() {
		dateOfLastExecute = new Date(System.currentTimeMillis());
	}

	private Date getDateOfLastExecuteFromDB() {
		return new Date(System.currentTimeMillis());
	}

	public String createSelectQuery() {
            return "SELECT * FROM GamingBets.sc2_matches WHERE bet_created = 0;";
                              
	}

	public String createInsertQuery(int id) {
		return "INSERT INTO `gamingbets`.`sc2_available_bets` (`match_id`) VALUES ('"+id+"');";

	}

	public String createUpdateQuery(int id) {
		return "UPDATE `GamingBets`.`sc2_matches` SET `bet_created`='1' WHERE `id`='"+id+"';";
	}

}

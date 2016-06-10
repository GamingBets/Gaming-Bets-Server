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
	private Sc2MatchesFacadeREST facadeMatches;
	private Date dateOfLastExecute;
	private Connection con;

	public Create_Available_Bets_SC2() {
		this.con = Database.connect();
	}

	public void run() {

		// Get dateOfLastExecute

		// Query for SC2 Matches with bet_created flag = 0
		String query = createSelectQuery();
		PreparedStatement stmt = null;
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
			stmt.close();
                        con.close();
			System.out.println(i + " available bets were created!");
		} catch (SQLException e) {
			// TODO Exception Handling
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)this.con.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		/*
		 * TypedQuery<Sc2Matches> query =
		 * facadeMatches.getEntityManagerForMicroservices().createNamedQuery(
		 * "Sc2Matches.findByBetCreated",
		 * Sc2Matches.class).setParameter("betCreated", 0); List<Sc2Matches>
		 * matches = new ArrayList(); matches = query.getResultList();
		 * 
		 * Query q; for(int i = 0; i<matches.size();i++) {
		 * 
		 * //q =
		 * facadeMatches.createQuery(createInsertQuery(matches.get(i).getId()));
		 * //q.executeUpdate();
		 * 
		 * Sc2Matches temp = matches.get(i); temp.setBetCreated(1);
		 * facadeMatches.edit(temp);
		 * 
		 * 
		 * }
		 * 
		 * 
		 * return matches;
		 * 
		 */

	}

	private void resetTimer() {
		dateOfLastExecute = new Date(System.currentTimeMillis());
	}

	private Date getDateOfLastExecuteFromDB() {
		return new Date(System.currentTimeMillis());
	}

	public String createSelectQuery() {
		return "SELECT * FROM gamingbets.sc2_matches WHERE bet_created = 0;";

	}

	public String createInsertQuery(int id) {
		return "INSERT INTO `gamingbets`.`sc2_available_bets` (`match_id`) VALUES ('" + id + "');";

	}

	public String createUpdateQuery(int id) {
		return "UPDATE `gamingbets`.`sc2_matches` SET `bet_created`='1' WHERE `id`='" + id + "';";
	}

}

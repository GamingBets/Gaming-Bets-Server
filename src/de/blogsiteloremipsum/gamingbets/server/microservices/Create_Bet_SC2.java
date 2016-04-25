package de.blogsiteloremipsum.gamingbets.server.microservices;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import de.blogsiteloremipsum.gamingbets.Database;
import de.blogsiteloremipsum.gamingbets.classes.Ticket;

public class Create_Bet_SC2 {

	private Date dateOfLastExecute;
	private Connection con;

	public Create_Bet_SC2() {
		dateOfLastExecute = getDateOfLastExecuteFromDB();
		this.con = Database.connect();
	}

	public void run() {
		// Get dateOfLastExecute

		// Query for SC2 Matches with bet_created flag = 0
		String query = createSelectQuery();
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				
				stmt = con.prepareStatement(createInsertQuery(rs.getInt("id")));
				stmt.executeUpdate();
				
				
				stmt = con.prepareStatement(createUpdateQuery(rs.getInt("id")));
				stmt.executeUpdate();
			}
		System.out.println("Finished!");
		} catch (SQLException e) {
			//TODO Exception Handling
			e.printStackTrace();
		}

	}

	private void resetTimer() {
		dateOfLastExecute = new Date(System.currentTimeMillis());
	}

	private Date getDateOfLastExecuteFromDB() {
		return new Date(System.currentTimeMillis());
	}

	public String createSelectQuery() {
		return "SELECT * FROM GamingBets.sc2_matches WHERE bet_created = 0";
	}

	public String createInsertQuery(int id) {
		return "INSERT INTO `gamingbets`.`sc2_available_bets` (`match_id`) VALUES ('"+id+"');";

	}

	public String createUpdateQuery(int id) {
		return "UPDATE `GamingBets`.`sc2_matches` SET `bet_created`='1' WHERE `id`='"+id+"';";
	}

}

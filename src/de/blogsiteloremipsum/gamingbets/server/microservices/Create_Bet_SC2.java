package de.blogsiteloremipsum.gamingbets.server.microservices;

import de.blogsiteloremipsum.gamingbets.Database;

import java.sql.*;

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
			int i = 0;
			while (rs.next()) {
				
				stmt = con.prepareStatement(createInsertQuery(rs.getInt("id")));
				stmt.executeUpdate();
				
				
				stmt = con.prepareStatement(createUpdateQuery(rs.getInt("id")));
				stmt.executeUpdate();
				
				i++;
				
			}
		System.out.println(i+" rows affected!");
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
		return "SELECT * FROM GamingBets.sc2_matches WHERE bet_created = 0;";
	}

	public String createInsertQuery(int id) {
		return "INSERT INTO `gamingbets`.`sc2_available_bets` (`match_id`) VALUES ('"+id+"');";

	}

	public String createUpdateQuery(int id) {
		return "UPDATE `GamingBets`.`sc2_matches` SET `bet_created`='1' WHERE `id`='"+id+"';";
	}

}

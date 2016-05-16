package com.gabmingbets.gamingbetrestserver.microservices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WriteNewMatchesToDB {

	private Connection con;

	public WriteNewMatchesToDB() {
		this.con = Database.connect();
	}

	public static void main(String[] args) {
		WriteNewMatchesToDB a = new WriteNewMatchesToDB();

		a.run();
	}

	public void run() {
		ArrayList<SC2Tournament> tournaments;
		ArrayList<SC2Match> matches;

		// Get List of Tournaments out of DB
		tournaments = getTournamentList();

		// For each Tournament get Matches from Liquipedia and Update the
		// Matches in our DB
		for (SC2Tournament tournament : tournaments) {

			matches = GetSC2Data.getMatchesFromTournament(tournament.getLink());

			insertOrUpdateMatches(matches, tournament);

			try {
				Thread.sleep(60000); // 1000 milliseconds is one second, so 60
										// 000 milliseconds is one minute, we
										// need to wait at least one minute
										// between every api call
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
				ex.printStackTrace();
			}
		}

		// Create new Bets
		Create_Bet_SC2 create = new Create_Bet_SC2();
		create.run();

		// Evaluate the Bets
		Evaluate_Bet_SC2 evaluate = new Evaluate_Bet_SC2();
		evaluate.run();

		// Adjust the Score
		AdjustScoreSC2 adjustScore = new AdjustScoreSC2();
		adjustScore.run();

	}

	private void insertOrUpdateMatches(ArrayList<SC2Match> matches, SC2Tournament tournament) {
		String query;
		PreparedStatement stmt;
		ResultSet rs;

		for (SC2Match match : matches) {
			
			// Check if Players are in DB, and if not, add them
			int[] player_ids = validatePlayers(match);
			
			try {
				//Check if Match already exists!
				query = "SELECT * FROM gamingbets.sc2_matches WHERE player1 = '" + player_ids[0] + "' and player2 = '" + player_ids[1] + "' and tournament_id = '" + tournament.getId() + "';";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();
				
				if(rs.next()){
					if (rs.getInt("result")!=match.getScore()) {
						updateMatch(rs.getInt("id"), match.getScore());
					}					
				}else {
					insertMatch(tournament, match, player_ids);
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}


	private void updateMatch(int id, int score) {
		String query;
		PreparedStatement stmt;
		
		try {
			query = "UPDATE `gamingbets`.`sc2_matches` SET `result`='"+score+"' WHERE `id`='"+id+"';";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Updated SC2Match in DB with ID: "+id);
	}

	private void insertMatch(SC2Tournament tournament, SC2Match match, int[] player_ids) {
		String query;
		PreparedStatement stmt;
		
		//Now add the Matches
		try {
			
			query = "INSERT INTO `gamingbets`.`sc2_matches` (`player1`, `player2`, `bet_created`, `tournament_id`, `result`) VALUES ('"+player_ids[0]+"', '"+player_ids[1]+"', '0', '"+tournament.getId()+"', '"+match.getScore()+"');";
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();
			System.out.println("Added SC2Match to DB, Tournament: "+tournament.getName()+" Player1: "+match.getPlayer1()+" Player2: "+match.getPlayer2());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int[] validatePlayers(SC2Match match) {
		String query;
		PreparedStatement stmt;
		ResultSet rs;

		int[] result_int = new int[2];

		try {

			for (int i = 1; i < 3; i++) {
				query = "SELECT * FROM gamingbets.sc2_player WHERE ingame_name = '" + match.getPlayer(i) + "';";
				stmt = con.prepareStatement(query);
				rs = stmt.executeQuery();

				if (!rs.next()) {
					insertPlayerToDB(match, i);

					query = "SELECT * FROM gamingbets.sc2_player WHERE ingame_name = '" + match.getPlayer(i) + "';";
					stmt = con.prepareStatement(query);
					rs = stmt.executeQuery();
					rs.next();

				}
				result_int[i-1] = rs.getInt("id");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result_int;

	}

	// TODO do an API call, to get more Information about a Player!
	private void insertPlayerToDB(SC2Match match, int i) throws SQLException {
		String query;
		PreparedStatement stmt;

		// Insert new Player to DB
		query = "INSERT INTO `gamingbets`.`sc2_player` (`ingame_name`) VALUES ('" + match.getPlayer(i) + "');";
		stmt = con.prepareStatement(query);
		stmt.executeUpdate();
		
		System.out.println("Added Player to Database: "+match.getPlayer(i));
	}

	private ArrayList<SC2Tournament> getTournamentList() {

		String query;
		PreparedStatement stmt;
		ResultSet rs;
		ArrayList<SC2Tournament> list;

		list = new ArrayList<SC2Tournament>();

		try {
			query = "SELECT * FROM gamingbets.sc2_tournament where status < 2";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();

			while (rs.next()) {
				list.add(new SC2Tournament(rs.getString("link"), rs.getInt("idtournament"), rs.getString("name")));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
}

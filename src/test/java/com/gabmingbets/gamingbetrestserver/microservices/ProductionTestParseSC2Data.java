package com.gabmingbets.gamingbetrestserver.microservices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProductionTestParseSC2Data {

	public static void main(String[] args) {
		String player1 = "iAsonu";
		String player2 = "Harstem";
		String tournament = "Gold_Series_International_2016";
		
		ArrayList<SC2Match> list = GetSC2Data.getMatchesFromTournament(tournament);
		
		System.out.println("\n\n--------------------------------\nSC2Match Objects:\n\n");
		for (SC2Match sc2Match : list) {
			System.out.println(sc2Match.toString());
		}
		
		// StringBuilder result = new StringBuilder();
		// GetSC2Data.parseByTournament("Gold_Series_International_2016");
		// System.out.println(GetSC2Data.updateURL("Snute", "Harstem",
		// "Gold_Series_International_2016"));
		// GetSC2Data.parseByTournamentAndPlayer("Snute", "Harstem",
		// "Gold_Series_International_2016");
		// BufferedReader reader = null;
		// try {
		// reader = new BufferedReader(new FileReader("sc2apichache.txt"));
		// String line;
		// while ((line = reader.readLine()) != null) {
		// result.append(line);
		// }
		// reader.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		//
		// System.out.println("Read from file:\n"+result);
		//
		// System.out.println(GetSC2Data.parseResponseStringToJSONObject(result.toString()));

	}

}

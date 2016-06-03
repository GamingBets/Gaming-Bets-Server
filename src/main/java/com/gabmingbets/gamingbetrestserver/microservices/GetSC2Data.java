package com.gabmingbets.gamingbetrestserver.microservices;

import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import org.json.*;

public class GetSC2Data {

	public static String updateURL(String player1, String player2, String tournament) {
		String url;
		if (player1 == null) {
			player1 = "%2b";
		}
		if (player2 == null) {
			player2 = "%2b";
		}
		if (tournament == null) {
			tournament = "%2b";
		}
		url = "http://wiki.teamliquid.net/starcraft2/api.php?action=askargs&conditions=has_tournament::" + tournament
				+ "|has_player_left::" + player1 + "|has_player_right::" + player2
				+ "&printouts=has_player_left|has_player_right|has_tournament|has_player_left_score|has_player_right_score&parameters=offset%3D0|limit%3D100&format=json";
		return url;
	}

	public static String updateURL(String tournament) {
		String url;
		if (tournament == null) {
			tournament = "%2b";
		}
		url = "http://wiki.teamliquid.net/starcraft2/api.php?action=askargs&conditions=has_tournament::" + tournament
				+ "|has_player_left::%2b|has_player_right::%2b&printouts=has_player_left|has_player_right|has_tournament|has_player_left_score|has_player_right_score&parameters=offset%3D0|limit%3D100&format=json";
		return url;
	}

	public static String excutePost(String targetURL) {

		HttpURLConnection connection = null;
		StringBuilder response = null;

		try {
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
			connection.setRequestProperty("User-Agent", "GamingBetsServer, felixmorsbach@web.de");
			// connection.setRequestProperty("Content-Length",
			// Integer.toString(urlParameters.getBytes().length));
			// connection.setRequestProperty("Content-Language", "en-US");
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Accept-Encoding", "gzip");
			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			OutputStream wr = connection.getOutputStream();
			wr.write(targetURL.getBytes("UTF-8"));
			wr.close();

			// Get Response
			Reader reader = null;
			if ("gzip".equals(connection.getContentEncoding())) {
				reader = new InputStreamReader(new GZIPInputStream(connection.getInputStream()));
			} else {
				reader = new InputStreamReader(connection.getInputStream());
			}

			// Print out Response Code
			int status = connection.getResponseCode();
			System.out.println("Response Code: " + status);

			// Print out Header Fields

			// for (Entry<String, List<String>> header :
			// connection.getHeaderFields().entrySet()) {
			// System.out.println(header.getKey() + "=" + header.getValue());
			// }

			// GetResponse
			response = new StringBuilder();
			System.out.println("Output From \"executePost\":\n");
			while (true) {
				int ch = reader.read();
				if (ch == -1) {
					break;
				}
				response.append((char) ch);
				System.out.print((char) ch);
			}
			System.out.println();

		} catch (Exception e) {
			e.printStackTrace();
			return null;

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return response.toString();

	}

//	public static JSONObject parseResponseStringToJSONObject(String response) {
//		JSONObject object = new JSONObject(response);
//		object = object.getJSONObject("query");
//		object = object.getJSONObject("results");
//		String maps[] = JSONObject.getNames(object);
//		String result_object_name = "";
//
//		for (int i = 0; i < maps.length; i++) {
//			if (!maps[i].contains("Map")) {
//				result_object_name = maps[i];
//			}
//
//		}
//		object = object.getJSONObject(result_object_name);
//		object = object.getJSONObject("printouts");
//
//		JSONArray player1 = object.getJSONArray("has_player_right");
//		JSONArray player2 = object.getJSONArray("has_player_left");
//		JSONArray player1_score = object.getJSONArray("has_player_right_score");
//		JSONArray player2_score = object.getJSONArray("has_player_left_score");
//
//		JSONArray tournament = object.getJSONArray("has_tournament");
//		JSONObject tournament_text = tournament.getJSONObject(0);
//		String tournament_full_text = tournament_text.getString("fulltext");
//
//		SC2Match match = new SC2Match();
//		match.setTournament(tournament_full_text);
//
//		match.setPlayer1(player1.getString(0));
//		match.setPlayer2(player2.getString(0));
//
//		int score = 0;
//
//		score = player1_score.getInt(0) * 10;
//		score += player2_score.getInt(0);
//
//		match.setScore(score);
//
//		System.out.println(match.toString());
//
//		return object;
//	}

//	public static SC2Match parseByTournament(String tournament) {
//		System.out.println("Testing!");
//		System.out.println(excutePost(updateURL(null, null, tournament)));
//
//		return null;
//	}
//
//	public static SC2Match parseByTournamentAndPlayer(String player1, String player2, String tournament) {
		// JSONArray object =
		// parseResponseStringToJSONObject((excutePost(updateURL(player1,
		// player2, tournament))));
		// SC2Match match = new SC2Match();
		// System.out.println(object.get(1));
//		return null;
//
//	}

	
	
	
	// Call this Method with an URL of a Liquipedia Tournament, and it will
	// return you a list of all Matches!
	public static ArrayList<SC2Match> getMatchesFromTournament(String url) {

		ArrayList<SC2Match> list = new ArrayList<SC2Match>();

		// CutURLToTournamentIdentifier
		url = url.substring(38);

		// Send an HTTP POST Request to Liquipedia
		String httpResponse;
		httpResponse = excutePost(updateURL(url));
		
		// Extracts just the results
		JSONObject json_matches = parseResponseToResultsAsJSONObject(httpResponse);

		// Filters Objects which describe only one map out of the List and
		// invalid datasets
		json_matches = filterMatches(json_matches);

		// TODO Order By Matches!

		System.out.println("\n--------------------------\nOutput from \"getMatchesFromTournament\":\n");
		System.out.println(json_matches);
		System.out.println();

		// Creates a SC2Match Object for each JSONObject
		list = parseJSONMachtesToListOfSC2Machtes(json_matches);

		return list;
	}

	public static ArrayList<SC2Match> parseJSONMachtesToListOfSC2Machtes(JSONObject json_matches) {

		String maps[] = JSONObject.getNames(json_matches);
		ArrayList<SC2Match> list = new ArrayList<SC2Match>();
		SC2Match temp;

		for (int i = 0; i < maps.length; i++) {
			temp = parseJSONObjectToSC2Match(json_matches.getJSONObject(maps[i]));
			if (temp != null) {
				list.add(temp);
			}
		}

		return list;
	}

	private static JSONObject filterMatches(JSONObject json_matches) {

		String maps[] = JSONObject.getNames(json_matches);

		for (int i = 0; i < maps.length; i++) {
			if (maps[i].contains("Map")/* || maps[i].contains("TBD")*/) {
				json_matches.remove(maps[i]);
			}

		}
		return json_matches;

	}

	public static SC2Match parseJSONObjectToSC2Match(JSONObject each_object) {

		SC2Match match = new SC2Match();

		each_object = each_object.getJSONObject("printouts");

		JSONArray player1 = each_object.getJSONArray("has_player_left");
		JSONArray player2 = each_object.getJSONArray("has_player_right");
		JSONArray player1_score = each_object.getJSONArray("has_player_left_score");
		JSONArray player2_score = each_object.getJSONArray("has_player_right_score");
		JSONArray json_tournament = each_object.getJSONArray("has_tournament");

		JSONObject a = json_tournament.getJSONObject(0);
		String tournament = a.getString("fulltext");

		match.setTournament(tournament);

		if(player1.getString(0).equalsIgnoreCase("tbd") ||
				player2.getString(0).equalsIgnoreCase("tbd")){
			return null;
		}
		
		match.setPlayer1(player1.getString(0));
		match.setPlayer2(player2.getString(0));

		int score = 0;

		score = player1_score.getInt(0) * 10;
		score += player2_score.getInt(0);

		match.setScore(score);

		return match;
	}

	private static JSONObject parseResponseToResultsAsJSONObject(String httpResponse) {

		JSONObject object = new JSONObject(httpResponse);
		object = object.getJSONObject("query");
		object = object.getJSONObject("results");
		return object;
	}

}

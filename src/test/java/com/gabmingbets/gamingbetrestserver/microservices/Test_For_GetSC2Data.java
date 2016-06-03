package com.gabmingbets.gamingbetrestserver.microservices;

import static org.junit.Assert.*;

import org.json.JSONObject;
import org.junit.Test;


public class Test_For_GetSC2Data {
	
	static String player1 = "iAsonu";
	static String player2 = "Harstem";
	static String tournament = "2016_WCS_Spring_Circuit_Championship";
		
	@Test
	public void TestUpdateURL(){
		String actual, expected;
		actual = GetSC2Data.updateURL(tournament);
		expected = "http://wiki.teamliquid.net/starcraft2/api.php?action=askargs&conditions=has_tournament::2016_WCS_Spring_Circuit_Championship|has_player_left::%2b|has_player_right::%2b&printouts=has_player_left|has_player_right|has_tournament|has_player_left_score|has_player_right_score&parameters=offset%3D0|limit%3D100&format=json";
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void TestUpdateURL2(){
		String actual, expected;
		actual = GetSC2Data.updateURL(player1, player2, tournament);
		expected = "http://wiki.teamliquid.net/starcraft2/api.php?action=askargs&conditions=has_tournament::2016_WCS_Spring_Circuit_Championship|has_player_left::iAsonu|has_player_right::Harstem&printouts=has_player_left|has_player_right|has_tournament|has_player_left_score|has_player_right_score&parameters=offset%3D0|limit%3D100&format=json";
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestparseJSONObjectToSC2Match(){
		
		String source = "{\"2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\": {        \"printouts\": {          \"has_player_left\": [            \"puCK\"          ],          \"has_player_right\": [            \"MaSa\"          ],          \"has_tournament\": [            {              \"fulltext\": \"2016 WCS Spring Circuit Championship\",              \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship\",              \"namespace\": 0,              \"exists\": \"\"            }          ],          \"has_player_left_score\": [            2          ],          \"has_player_right_score\": [            3          ]        },        \"fulltext\": \"2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\",        \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship#puCK_vs_MaSa_at_May_14.2C_2016_14:30_in_Match_TBD\",        \"namespace\": 0,        \"exists\": \"\"      }}";		
		JSONObject obj = new JSONObject(source);
		obj = obj.getJSONObject("2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD");
		SC2Match actual = GetSC2Data.parseJSONObjectToSC2Match(obj);
		
		
		SC2Match expected = new SC2Match();
		expected.setPlayer1("puCK");
		expected.setPlayer2("MaSa");
		expected.setTournament("2016 WCS Spring Circuit Championship");
		expected.setScore(23);
		
		assertEquals(expected.getPlayer1(), actual.getPlayer1());
		assertEquals(expected.getPlayer2(), actual.getPlayer2());
		assertEquals(expected.getScore(), actual.getScore());
		assertEquals(expected.getTournament(), actual.getTournament());
		
	}
}

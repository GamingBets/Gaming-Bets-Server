package com.gabmingbets.gamingbetrestserver.microservices;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
	
	@Test
	public void TestFilterMatches(){
		
		String source_text = "{\"2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\": {        \"printouts\": {          \"has_player_left\": [            \"puCK\"          ],          \"has_player_right\": [            \"MaSa\"          ],          \"has_tournament\": [            {              \"fulltext\": \"2016 WCS Spring Circuit Championship\",              \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship\",              \"namespace\": 0,              \"exists\": \"\"            }          ],          \"has_player_left_score\": [            2          ],          \"has_player_right_score\": [            3          ]        },        \"fulltext\": \"2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\",        \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship#puCK_vs_MaSa_at_May_14.2C_2016_14:30_in_Match_TBDMap1\",        \"namespace\": 0,        \"exists\": \"\"      },\"2016 WCS MapSpring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\": {        \"printouts\": {          \"has_player_left\": [            \"puCK\"          ],          \"has_player_right\": [            \"MaSa\"          ],          \"has_tournament\": [            {              \"fulltext\": \"2016 WCS Spring Circuit Championship\",              \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship\",              \"namespace\": 0,              \"exists\": \"\"            }          ],          \"has_player_left_score\": [            2          ],          \"has_player_right_score\": [            3          ]        },        \"fulltext\": \"2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\",        \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship#puCK_vs_MaSa_at_May_14.2C_2016_14:30_in_Match_TBDMap1\",        \"namespace\": 0,        \"exists\": \"\"      }}";
		JSONObject source_obj = new JSONObject(source_text);
		
		
		int before = JSONObject.getNames(source_obj).length;
		JSONObject result = GetSC2Data.filterMatches(source_obj);
		int after = JSONObject.getNames(result).length;
		
		assertEquals(before-1, after);
		
	}
	
	@Test
	public void TestParseJSONMachtesToListOfSC2Machtes(){
		String source_text = "{\"2016 WCS Spring Circuit Championship#dummy1 vs dummy2 at May 14, 2016 14:30 in Match TBD\": {        \"printouts\": {          \"has_player_left\": [            \"dummy1\"          ],          \"has_player_right\": [            \"dummy2\"          ],          \"has_tournament\": [            {              \"fulltext\": \"2016 WCS Spring Circuit Championship\",              \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship\",              \"namespace\": 0,              \"exists\": \"\"            }          ],          \"has_player_left_score\": [            5          ],          \"has_player_right_score\": [           0          ]        },        \"fulltext\": \"2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\",        \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship#puCK_vs_MaSa_at_May_14.2C_2016_14:30_in_Match_TBD\",        \"namespace\": 0,        \"exists\": \"\"      },\"2016 WCS MapSpring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\": {        \"printouts\": {          \"has_player_left\": [            \"puCK\"          ],          \"has_player_right\": [            \"MaSa\"          ],          \"has_tournament\": [            {              \"fulltext\": \"2016 WCS Spring Circuit Championship\",              \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship\",              \"namespace\": 0,              \"exists\": \"\"            }          ],          \"has_player_left_score\": [            2          ],          \"has_player_right_score\": [            3          ]        },        \"fulltext\": \"2016 WCS Spring Circuit Championship#puCK vs MaSa at May 14, 2016 14:30 in Match TBD\",        \"fullurl\": \"http://wiki.teamliquid.net/starcraft2/2016_WCS_Spring_Circuit_Championship#puCK_vs_MaSa_at_May_14.2C_2016_14:30_in_Match_TBD\",        \"namespace\": 0,        \"exists\": \"\"      }}";
		JSONObject source_obj = new JSONObject(source_text);		
		
		
		
		ArrayList<SC2Match> result = GetSC2Data.parseJSONMachtesToListOfSC2Machtes(source_obj);
		
		assertEquals(2, result.size());
		
		//Order is not distinct
//		assertEquals("dummy1" , result.get(1).getPlayer1());
//		assertEquals("dummy2", result.get(1).getPlayer2());
//		assertEquals("puCK", result.get(0).getPlayer1());
//		assertEquals("MaSa", result.get(0).getPlayer2());
//		assertEquals(50, result.get(1).getScore());
//		assertEquals(23, result.get(0).getScore());
		
	}
	
	
	@Test
	public void TestParseResponseToResultsAsJSONObject(){
		
		JSONObject values = new JSONObject();
		values.put("key1", "value1");
		values.put("key2", "value2");
		
		JSONObject result = new JSONObject();
		result.put("results", values);
		
		JSONObject query = new JSONObject();
		query.put("query", result);
		
		JSONObject actual = GetSC2Data.parseResponseToResultsAsJSONObject(query.toString());
		
		assertEquals("value1", actual.getString("key1"));
		assertEquals("value2", actual.getString("key2"));
		
	}
	
}

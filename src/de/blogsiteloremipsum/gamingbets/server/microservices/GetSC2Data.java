package de.blogsiteloremipsum.gamingbets.server.microservices;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetSC2Data {
	
	
	private String url;
	
	
	
	public void updateURL(String player1, String player2, String tournament){
		if(player1 == null){
			player1 = "";
		}
		if(player2 == null){
			player2 = "";
		}
		if(tournament == null){
			tournament = "";
		}
		url = "http://wiki.teamliquid.net/starcraft2/api.php?action=askargs&conditions=has_tournament::"+tournament+"|has_player_left::"+player1+"|has_player_right::"+player2+"Hydra&printouts=has_player_left|has_player_right|has_tournament|has_player_left_score|has_player_right_score&parameters=offset%3D0|limit%3D100&format=json";
		
	}
	
	public static String excutePost(String targetURL, String urlParameters) {
		  HttpURLConnection connection = null;  
		  try {
		    //Create connection
		    URL url = new URL(targetURL);
		    connection = (HttpURLConnection)url.openConnection();
		    connection.setRequestMethod("POST");
		    connection.setRequestProperty("Content-Type", 
		        "application/x-www-form-urlencoded");

		    connection.setRequestProperty("Content-Length", 
		        Integer.toString(urlParameters.getBytes().length));
		    connection.setRequestProperty("Content-Language", "en-US");  

		    connection.setUseCaches(false);
		    connection.setDoOutput(true);

		    //Send request
		    DataOutputStream wr = new DataOutputStream (
		        connection.getOutputStream());
		    wr.writeBytes(urlParameters);
		    wr.close();

		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder(); // or StringBuffer if not Java 5+ 
		    String line;
		    while((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    return response.toString();
		  } catch (Exception e) {
		    e.printStackTrace();
		    return null;
		  } finally {
		    if(connection != null) {
		      connection.disconnect(); 
		    }
		  }
	}
}

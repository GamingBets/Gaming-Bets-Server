package de.blogsiteloremipsum.gamingbets.tests;

import de.blogsiteloremipsum.gamingbets.server.microservices.Create_Bet_Lol;
import de.blogsiteloremipsum.gamingbets.server.microservices.Create_Bet_SC2;
import de.blogsiteloremipsum.gamingbets.server.microservices.Evaluate_Bet_SC2;

public class ProductionTestMain {

	public static void main(String args[]) {
		Create_Bet_SC2 a = new Create_Bet_SC2();
//		a.run();
		Create_Bet_Lol b = new Create_Bet_Lol();
//		b.run();
		Evaluate_Bet_SC2 c = new Evaluate_Bet_SC2();
		c.run();
	}

}

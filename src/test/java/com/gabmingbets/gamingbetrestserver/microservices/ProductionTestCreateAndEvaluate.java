package com.gabmingbets.gamingbetrestserver.microservices;


public class ProductionTestCreateAndEvaluate {

	public static void main(String args[]) {
		Create_Bet_SC2 a = new Create_Bet_SC2();
//		a.run();
		Create_Bet_Lol b = new Create_Bet_Lol();
//		b.run();
		Evaluate_Bet_SC2 c = new Evaluate_Bet_SC2();
		c.run();
	}

}

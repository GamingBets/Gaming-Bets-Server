package com.gabmingbets.gamingbetrestserver.microservices;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	Test_For_Bet_Creation_Microservice_SC2.class, 
	Test_For_Bet_Creation_Microservice_Lol.class, 
	Test_For_Bet_Evaluation_SC2.class, 
	Test_For_Bet_Evaluation_Lol.class,
	Test_For_Adjust_Score_SC2.class,
	Test_For_SC2_Match.class,
	Test_For_GetSC2Data.class
	})

public class All_Tests {

}

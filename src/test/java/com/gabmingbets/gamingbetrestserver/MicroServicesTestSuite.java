package com.gabmingbets.gamingbetrestserver;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.gabmingbets.gamingbetrestserver.microservices.Test_For_Adjust_Score_SC2;
import com.gabmingbets.gamingbetrestserver.microservices.Test_For_Bet_Creation_Microservice_Lol;
import com.gabmingbets.gamingbetrestserver.microservices.Test_For_Bet_Creation_Microservice_SC2;
import com.gabmingbets.gamingbetrestserver.microservices.Test_For_Bet_Evaluation_Lol;
import com.gabmingbets.gamingbetrestserver.microservices.Test_For_Bet_Evaluation_SC2;
import com.gabmingbets.gamingbetrestserver.microservices.Test_For_GetSC2Data;
import com.gabmingbets.gamingbetrestserver.microservices.Test_For_SC2_Bet;
import com.gabmingbets.gamingbetrestserver.microservices.Test_For_SC2_Match;


@RunWith(Suite.class)
@SuiteClasses({
	Test_For_Adjust_Score_SC2.class,
	Test_For_Bet_Evaluation_Lol.class,
	Test_For_Bet_Evaluation_SC2.class,
	Test_For_Bet_Creation_Microservice_Lol.class,
	Test_For_Bet_Creation_Microservice_SC2.class,
	Test_For_GetSC2Data.class,
	Test_For_SC2_Match.class,
	Test_For_SC2_Bet.class
})
public class MicroServicesTestSuite {
	
	
	
	
}

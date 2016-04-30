package de.blogsiteloremipsum.gamingbets.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Test_For_Bet_Creation_Microservice_SC2.class, Test_For_Bet_Creation_Microservice_Lol.class, Test_For_Bet_Evaluation_SC2.class})

public class All_Tests {

}

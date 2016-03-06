package net.d80harri.coach.rest.exercise;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import net.d80harri.coach.rest.ServerRule;

public class ExerciseControllerIT {

	@ClassRule
	public static ServerRule serverRule = new ServerRule();
	
    @Before
    public void setUp() {
        // 9
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
    }
    
	@Test
	public void test() {
		RestAssured.when().get("/exercises").then().statusCode(HttpStatus.SC_OK)
				.body("data", Matchers.hasSize(0));
	}
}

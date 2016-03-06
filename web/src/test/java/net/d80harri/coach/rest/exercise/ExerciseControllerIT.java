package net.d80harri.coach.rest.exercise;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

public class ExerciseControllerIT {

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

package net.d80harri.coach.rest.exercise;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import net.d80harri.coach.rest.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class ExerciseControllerIT {

	 
    
    @Before
    public void setUp() {
        // 9
        RestAssured.port = 8080;
    }
    
	@Test
	public void test() {
		RestAssured.when().get("http://localhost:8080/exercises").then().statusCode(HttpStatus.SC_OK)
				.body("data", Matchers.hasSize(0));
	}
}

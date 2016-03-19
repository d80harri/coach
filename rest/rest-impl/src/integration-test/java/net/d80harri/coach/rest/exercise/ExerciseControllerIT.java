package net.d80harri.coach.rest.exercise;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import net.d80harri.coach.rest.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ExerciseControllerIT {

	@Value("${server.port}")
	private int serverPort;

	@Before
	public void setUp() {
		// 9
		RestAssured.port = serverPort;
		RestAssured.baseURI = "http://localhost";
	}

	@Test
	public void test() {
		RestAssured.when().get("/exercises").then().statusCode(HttpStatus.SC_OK).body("data", Matchers.hasSize(0));
	}
}

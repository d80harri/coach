package net.d80harri.coach.rest.exercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jayway.restassured.RestAssured;

import net.d80harri.coach.rest.Application;
import net.d80harri.coach.rest.RestSetup;
import net.d80harri.domain.core.DatabaseTestRule;
import net.d80harri.domain.core.DatabaseTestRule.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class, locations = "classpath:rest-it.application-ctx.xml")
@WebIntegrationTest("server.port:0")
public class ExerciseControllerIT {

	@Value("${local.server.port}")
	private int serverPort;

	@Autowired
	@Rule
	public DatabaseTestRule dbTestRule;
	
	private RestSetup restSetup = new RestSetup();

	@Before
	public void setUp() {
		// 9
		RestAssured.port = serverPort;
		RestAssured.baseURI = "http://localhost/api";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	@DatabaseSetup()
	public void testReadEmpty() {
		RestAssured.when().get("/exercises").then().statusCode(HttpStatus.SC_OK).body("data", Matchers.hasSize(0));
	}

	@Test
	@DatabaseSetup()
	public void testWriteReadFlow() throws IOException {
		restSetup.create(ExerciseController.class.getResourceAsStream("/test.json"));
		RestAssured.when().get("/flows").then().statusCode(HttpStatus.SC_OK).body("data", Matchers.hasSize(1));
		RestAssured.when().get("/exercises").then().statusCode(HttpStatus.SC_OK).body("data", Matchers.hasSize(5));
		RestAssured.when().get("/atomic-exercises").then().statusCode(HttpStatus.SC_OK).body("data", Matchers.hasSize(4));
	}

}

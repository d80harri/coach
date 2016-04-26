package net.d80harri.coach.rest.exercise;

import java.io.IOException;

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

import com.jayway.restassured.RestAssured;

import net.d80harri.coach.rest.Application;
import net.d80harri.coach.rest.RestSetup;
import net.d80harri.domain.core.DatabaseTestRule;
import net.d80harri.domain.core.DatabaseTestRule.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class, locations = "classpath:rest-it.application-ctx.xml")
@WebIntegrationTest("server.port:0")
public class FlowItemControllerIT {

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
		RestAssured.baseURI = "http://localhost/api/";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
	}

	@Test
	@DatabaseSetup()
	public void testFindOne() throws IOException {
		restSetup.create(ExerciseController.class.getResourceAsStream("/test.json"));
		// @formatter:off
		RestAssured.given()
			.queryParam("include[flowitems]", "flow")
		.when()
			.get("/flowitems/flowitem.hollow")
		.then()
			.statusCode(HttpStatus.SC_OK)
//			.body("data", Matchers.hasSize(1))
			.body("data.type", Matchers.equalTo("flowitems"))
			.body("data.id", Matchers.equalTo("flowitem.hollow"))
			.body("data.attributes.timing.value", Matchers.equalTo(10))
			.body("data.attributes.timing.unit", Matchers.equalTo("BREATH"))
			.body("data.relationships.exercise", Matchers.not(Matchers.empty()))
			.body("data.relationships.flow", Matchers.not(Matchers.empty()))
			.body("data.links.self", Matchers.equalTo("http://localhost:8080/api/flowitems/flowitem.hollow"))
			.body("included", Matchers.hasSize(1))
			.body("included[0].type", Matchers.equalTo("flows"))
			.body("included[0].id",  Matchers.equalTo("torso"))
			.body("included[0].attributes.name",  Matchers.equalTo("Torso Flow"))
			.body("included[0].attributes.description",  Matchers.equalTo("My test torso flow"));
		// @formatter:on
	}

}

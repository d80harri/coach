package net.d80harri.coach.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpStatus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.jayway.restassured.RestAssured;

public class RestSetup {

	public void create(InputStream stream) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readValue(stream, JsonNode.class);
		createAll(node);
	}

	private void createAll(JsonNode node) {
		List<JsonNode> fetchNodes = fetchNodes(node);
		for (JsonNode n : fetchNodes) {
			RestAssured.given().body(n).expect().statusCode(HttpStatus.SC_CREATED).when().post("/entities");
		}
	}

	private List<JsonNode> fetchNodes(JsonNode node) {
		List<JsonNode> result = new ArrayList<>();

		if (node.isArray()) {
			ArrayNode array = (ArrayNode) node;
			for (int i = 0; i < array.size(); i++) {
				result.add(array.get(i));
			}
		} else {
			result.add(node);
		}
		return result;
	}
}

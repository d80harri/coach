package net.d80harri.coach.rest.exercise;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "exercises")
public class ExerciseDto {
	@JsonApiId
    private String id;

    @JsonProperty
    private String name;
    
    @JsonProperty
    private String description;
    
    public String getId() {
		return id;
	}
    public void setId(String id) {
		this.id = id;
	}
    public String getName() {
		return name;
	}
    public void setName(String name) {
		this.name = name;
	}
    public String getDescription() {
		return description;
	}
    public void setDescription(String description) {
		this.description = description;
	}
}

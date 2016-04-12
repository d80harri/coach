package net.d80harri.coach.rest.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.katharsis.resource.annotations.JsonApiResource;
import net.d80harri.coach.rest.EntityDto;

@JsonApiResource(type = "exercises")
public abstract class ExerciseDto extends EntityDto {
	@JsonProperty("name")
    private String name;
    
    @JsonProperty
    private String description;
    
    public ExerciseDto() {
	}

    public ExerciseDto(String name) {
    	this.name = name;
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

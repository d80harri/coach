package net.d80harri.coach.rest.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;

@JsonApiResource(type = "folow.items")
public class FlowItemDto {
	@JsonApiId
    private String id;
	@JsonApiToOne
	private final AtomicExerciseDto exercise;
	@JsonProperty
	private final Timing timing;
	
	public FlowItemDto(AtomicExerciseDto exercise, Timing timing) {
		this.exercise = exercise;
		this.timing = timing;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public AtomicExerciseDto getExercise() {
		return exercise;
	}
	
	public Timing getTiming() {
		return timing;
	}

}

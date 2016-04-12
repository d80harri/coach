package net.d80harri.coach.rest.exercise.flow;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;
import net.d80harri.coach.domain.exercise.Timing;
import net.d80harri.coach.rest.EntityDto;
import net.d80harri.coach.rest.exercise.atomic.AtomicExerciseDto;

@JsonApiResource(type = "flowitems")
public class FlowItemDto extends EntityDto {
	@JsonApiToOne
	private AtomicExerciseDto exercise;
	@JsonApiToOne
	private FlowDto flow;
	@JsonProperty
	private Timing timing;

	public FlowItemDto() {
	}

	public FlowItemDto(AtomicExerciseDto exercise, Timing timing) {
		this.exercise = exercise;
		this.timing = timing;
	}

	public AtomicExerciseDto getExercise() {
		return exercise;
	}

	public void setExercise(AtomicExerciseDto exercise) {
		this.exercise = exercise;
	}

	public Timing getTiming() {
		return timing;
	}

	public void setTiming(Timing timing) {
		this.timing = timing;
	}

	public FlowDto getFlow() {
		return flow;
	}

	public void setFlow(FlowDto flow) {
		this.flow = flow;
	}

}

package net.d80harri.coach.rest.exercise;

import java.util.ArrayList;
import java.util.List;

import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "flows")
public class FlowDto extends ExerciseDto {
	private List<FlowItemDto> flowItems = new ArrayList<>();
	
	public FlowDto(String name) {
		super(name);
	}

	public List<FlowItemDto> getItems() {
		return this.flowItems;
	}

}

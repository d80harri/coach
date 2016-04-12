package net.d80harri.coach.rest.exercise.flow;

import java.util.ArrayList;
import java.util.List;

import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import net.d80harri.coach.rest.exercise.ExerciseDto;

@JsonApiResource(type = "flows")
public class FlowDto extends ExerciseDto {
	@JsonApiToMany
	private List<FlowItemDto> items = new ArrayList<>();
	
	public FlowDto() {
	}
	
	public FlowDto(String name) {
		super(name);
	}

	public List<FlowItemDto> getItems() {
		return this.items;
	}
	
	public void setItems(List<FlowItemDto> flowItems) {
		this.items = flowItems;
	}

}

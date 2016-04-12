package net.d80harri.coach.rest.exercise.flow;

import io.katharsis.repository.ResourceRepository;
import net.d80harri.coach.rest.DefaultRelationshipController;
import net.d80harri.coach.rest.exercise.ExerciseDto;

public class FlowItem2ExerciseController extends DefaultRelationshipController<FlowItemDto, ExerciseDto>{

	public FlowItem2ExerciseController(ResourceRepository<FlowItemDto, String> sourceRepository,
			ResourceRepository<ExerciseDto, String> repository) {
		super(sourceRepository, repository);
	}

}

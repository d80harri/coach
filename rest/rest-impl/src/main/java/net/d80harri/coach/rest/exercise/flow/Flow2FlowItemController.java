package net.d80harri.coach.rest.exercise.flow;

import io.katharsis.repository.ResourceRepository;
import net.d80harri.coach.domain.exercise.Flow;
import net.d80harri.coach.rest.DefaultRelationshipController;

public class Flow2FlowItemController extends DefaultRelationshipController<Flow, FlowItemDto>{

	public Flow2FlowItemController(ResourceRepository<Flow, String> sourceRepository,
			ResourceRepository<FlowItemDto, String> repository) {
		super(sourceRepository, repository);
	}

}

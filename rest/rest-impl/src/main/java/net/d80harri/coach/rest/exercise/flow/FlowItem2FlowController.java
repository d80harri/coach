package net.d80harri.coach.rest.exercise.flow;

import io.katharsis.repository.ResourceRepository;
import net.d80harri.coach.rest.DefaultRelationshipController;

public class FlowItem2FlowController extends DefaultRelationshipController<FlowItemDto, FlowDto>{

	public FlowItem2FlowController(ResourceRepository<FlowItemDto, String> sourceRepository,
			ResourceRepository<FlowDto, String> repository) {
		super(sourceRepository, repository);
	}

}

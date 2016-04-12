package net.d80harri.coach.rest.exercise.flow;

import net.d80harri.coach.domain.exercise.Flow;
import net.d80harri.coach.domain.repository.IRepository;
import net.d80harri.coach.rest.DefaultController;
import net.d80harri.coach.rest.RestCoachMapper;

public class FlowController extends DefaultController<Flow, FlowDto>{

	public FlowController(IRepository<Flow> repository,
			RestCoachMapper mapper) {
		super(Flow.class, FlowDto.class, repository, mapper);
	}

}

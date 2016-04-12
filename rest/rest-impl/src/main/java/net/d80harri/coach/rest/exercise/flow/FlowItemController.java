package net.d80harri.coach.rest.exercise.flow;

import io.katharsis.queryParams.QueryParams;
import net.d80harri.coach.domain.exercise.FlowItem;
import net.d80harri.coach.domain.repository.IRepository;
import net.d80harri.coach.rest.DefaultController;
import net.d80harri.coach.rest.RestCoachMapper;

public class FlowItemController extends DefaultController<FlowItem, FlowItemDto> {

	public FlowItemController(IRepository<FlowItem> repository,
			RestCoachMapper mapper) {
		super(FlowItem.class, FlowItemDto.class, repository, mapper);
	}
	
	@Override
	public Iterable<FlowItemDto> findAll(Iterable<String> ids, QueryParams queryParams) {
		Iterable<FlowItemDto> findAll = super.findAll(ids, queryParams);
		return findAll;
	}

}

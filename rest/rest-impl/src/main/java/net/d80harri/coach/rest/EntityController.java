package net.d80harri.coach.rest;

import net.d80harri.coach.domain.repository.Entity;
import net.d80harri.coach.domain.repository.IRepository;

public class EntityController extends DefaultController<Entity, EntityDto>{

	public EntityController(IRepository<Entity> repository,
			RestCoachMapper mapper) {
		super(Entity.class, EntityDto.class, repository, mapper);
	}

}
